package xyz.likailing.cloud.service.manager.service;

import xyz.likailing.cloud.service.manager.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
public interface ClassService extends IService<Class> {

    boolean removeClassById(String id);
}
