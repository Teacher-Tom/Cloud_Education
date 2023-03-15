package xyz.likailing.cloud.service.manager.service;

import xyz.likailing.cloud.service.manager.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
public interface TeacherService extends IService<Teacher> {

    boolean removeTeacherById(String id);
}
