package xyz.likailing.cloud.service.manager.service;

import xyz.likailing.cloud.service.manager.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.manager.entity.vo.StudentVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
public interface StudentService extends IService<Student> {

    List<Student> listClassStudents(String classId);

    List<StudentVO> listStudents();
}
