package xyz.likailing.cloud.service.service;


import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.entity.UserDir;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-06-11
 */
public interface UserDirService extends IService<UserDir> {

    UserDir getUserDir(String id);

    int setUserDir(UserDir userDir);

    boolean deleteStruct(String memid, String url);
}
