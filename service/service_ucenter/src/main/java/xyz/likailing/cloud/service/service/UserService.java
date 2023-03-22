package xyz.likailing.cloud.service.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import xyz.likailing.cloud.service.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.entity.vo.LoginVo;
import xyz.likailing.cloud.service.entity.vo.RegisterVo;

/**
* @author 12042
* @description 针对表【user】的数据库操作Service
* @createDate 2023-03-22 10:31:21
*/
public interface UserService extends IService<User>, UserDetailsService {
    public void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    void logout();
}
