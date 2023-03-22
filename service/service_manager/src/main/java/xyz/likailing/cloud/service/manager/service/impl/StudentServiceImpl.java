package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import xyz.likailing.cloud.service.manager.entity.Student;
import xyz.likailing.cloud.service.manager.entity.vo.StudentVO;
import xyz.likailing.cloud.service.manager.mapper.StudentMapper;
import xyz.likailing.cloud.service.manager.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public List<Student> listClassStudents(String classId) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.eq("class_id", classId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<StudentVO> listStudents() {
        //获取全部有班级信息的学生
        List<StudentVO> students = baseMapper.selectWithoutClass();
        //查询无班级信息的学生，手动设置班级名
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("class_id", "nul");
        List<Student> studentsNul = baseMapper.selectList(studentQueryWrapper);
        for (Student student : studentsNul) {
            StudentVO studentVO = new StudentVO();
            BeanUtils.copyProperties(student, studentVO);
            studentVO.setClassName("nul");
            students.add(studentVO);
        }
        return students;
    }
}
