package xyz.likailing.cloud.service.service;

import xyz.likailing.cloud.service.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.entity.vo.LoginVo;
import xyz.likailing.cloud.service.entity.vo.RegisterVo;

/**
* @author 12042
* @description 针对表【user】的数据库操作Service
* @createDate 2023-03-17 22:43:33
*/
public interface UserService extends IService<User> {
    public void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    void logout();
}
