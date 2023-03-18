package xyz.likailing.cloud.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.common.base.util.FormUtils;
import xyz.likailing.cloud.common.base.util.JwtInfo;
import xyz.likailing.cloud.common.base.util.JwtUtils;
import xyz.likailing.cloud.common.base.util.MD5;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.entity.User;
import xyz.likailing.cloud.service.entity.vo.LoginVo;
import xyz.likailing.cloud.service.entity.vo.RegisterVo;
import xyz.likailing.cloud.service.service.UserService;
import xyz.likailing.cloud.service.mapper.UserMapper;
import org.springframework.stereotype.Service;



/**
* @author 12042
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-03-17 22:43:33
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String username = registerVo.getUsername();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        int role = registerVo.getRole();
        //校验参数
        if(StringUtils.isEmpty(nickname)
                || StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)
                //|| !StringUtils.isEmpty(code)
                || role<0 || role >2
        ){
            throw new CloudException(ResultCodeEnum.PARAM_ERROR);
        }
        //校验验证码

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        Integer count = baseMapper.selectCount(queryWrapper);
        if(count>0){
            throw new CloudException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }

        User user = new User();
        user.setNickname(nickname);
        user.setUsername(username);
        user.setPassword(MD5.encrypt(password));
        user.setAvatar("https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg");
        user.setRole(role);
        baseMapper.insert(user);

    }

    @Override
    public String login(LoginVo loginVo) {
        //校验：参数合法，
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        if(StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)){
            throw new CloudException(ResultCodeEnum.PARAM_ERROR);
        }

        //用户名存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = baseMapper.selectOne(queryWrapper);
        if(user==null){
            throw new CloudException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }
        //密码正确
        if(!MD5.encrypt(password).equals(user.getPassword())){
            throw new CloudException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        //登录：生成token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(user.getId());
        jwtInfo.setUserName(user.getUsername());
        jwtInfo.setNickName(user.getNickname());
        jwtInfo.setAvatar(user.getAvatar());
        String jwtToken = JwtUtils.getJwtToken(jwtInfo,1000);
        return jwtToken;
    }
}




