package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkStudent;
import xyz.likailing.cloud.service.manager.mapper.CourseHomeworkStudentMapper;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkStudentService;

@Service
public class CourseHomeworkStudentServiceImpl extends ServiceImpl<CourseHomeworkStudentMapper, CourseHomeworkStudent> implements CourseHomeworkStudentService {
}
