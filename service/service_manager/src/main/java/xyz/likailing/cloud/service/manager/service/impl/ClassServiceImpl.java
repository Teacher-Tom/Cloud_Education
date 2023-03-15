package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.likailing.cloud.service.manager.entity.Class;
import xyz.likailing.cloud.service.manager.entity.ClassCourse;
import xyz.likailing.cloud.service.manager.entity.Student;
import xyz.likailing.cloud.service.manager.mapper.ClassCourseMapper;
import xyz.likailing.cloud.service.manager.mapper.ClassMapper;
import xyz.likailing.cloud.service.manager.mapper.StudentMapper;
import xyz.likailing.cloud.service.manager.service.ClassCourseService;
import xyz.likailing.cloud.service.manager.service.ClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Autowired
    private ClassCourseMapper classCourseMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public boolean removeClassById(String id) {
        int delete = baseMapper.deleteById(id);

        QueryWrapper<ClassCourse> classCourseQueryWrapper = new QueryWrapper<>();
        classCourseQueryWrapper.eq("class_id", id);
        classCourseMapper.delete(classCourseQueryWrapper);

        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("class_id", id);
        List<Student> students = studentMapper.selectList(studentQueryWrapper);
        for (Student student : students) {
            student.setClassId("nul");
            studentMapper.updateById(student);
        }

        return (delete >= 1);
    }
}
