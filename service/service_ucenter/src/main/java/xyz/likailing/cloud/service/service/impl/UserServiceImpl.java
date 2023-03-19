package xyz.likailing.cloud.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.common.base.util.*;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.entity.LoginUserDetails;
import xyz.likailing.cloud.service.entity.User;
import xyz.likailing.cloud.service.entity.UserRole;
import xyz.likailing.cloud.service.entity.vo.LoginVo;
import xyz.likailing.cloud.service.entity.vo.RegisterVo;
import xyz.likailing.cloud.service.mapper.AuthorityMapper;
import xyz.likailing.cloud.service.mapper.RoleMapper;
import xyz.likailing.cloud.service.mapper.UserRoleMapper;
import xyz.likailing.cloud.service.service.UserService;
import xyz.likailing.cloud.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.*;


/**
* @author 12042
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-17 22:43:33
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService, UserDetailsService {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private AuthorityMapper authorityMapper;
    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String username = registerVo.getUsername();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        String role = registerVo.getRole();
        //校验参数
        if(StringUtils.isEmpty(nickname)
                || StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)
                //|| !StringUtils.isEmpty(code)
                || StringUtils.isEmpty(role)
        ){
            throw new CloudException(ResultCodeEnum.PARAM_ERROR);
        }
        //校验验证码
        //用户是否已注册
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count>0){
            throw new CloudException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }
        //保存数据到库中
        User user = new User();
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setAvatar("https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg");
        user.setRole(role);
        baseMapper.insert(user);
        //保存用户-角色关系
        String role_id;
        switch (role){
            case "student":
                role_id = "2";
                break;
            case "teacher":
                role_id = "3";
                break;
            case "admin":
                role_id = "4";
                break;
            default:
                role_id = "1";
        }
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role_id);
        userRoleMapper.insert(userRole);

    }

    @Override
    public String login(LoginVo loginVo) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginVo.getUsername(),loginVo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new CloudException(ResultCodeEnum.LOGIN_ERROR);
        }
        LoginUserDetails loginUserDetails = (LoginUserDetails)authenticate.getPrincipal();
        User user = loginUserDetails.getUser();
        //校验：参数合法，
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        if(StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)){
            throw new CloudException(ResultCodeEnum.PARAM_ERROR);
        }

        //密码正确
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new CloudException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        //登录：生成token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(user.getId());
        jwtInfo.setUserName(user.getUsername());
        jwtInfo.setNickName(user.getNickname());
        jwtInfo.setAvatar(user.getAvatar());
        jwtInfo.setRole(user.getRole());
        String jwtToken = JwtUtils.getJwtToken(jwtInfo,3600);
        //存入redis
        redisCache.setCacheObject("login:"+user.getId(),loginUserDetails);
        return jwtToken;
    }

    @Override
    public void logout() {
        //获取contextHolder中的id
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails principal = (LoginUserDetails) token.getPrincipal();
        String id = principal.getUser().getId();
        //删除redis的值
        redisCache.deleteObject("login:"+id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //用户名存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = baseMapper.selectOne(queryWrapper);
        if(user==null){
            throw new CloudException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }
        String password = user.getPassword();
        List<String> auth = authorityMapper.selectParamsByUserId(user.getId());
        LoginUserDetails loginUserDetails = new LoginUserDetails(user,auth);
        return loginUserDetails;
    }

}




