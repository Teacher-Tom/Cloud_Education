package xyz.likailing.cloud.service.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkSubmit;

import java.util.List;

public interface CourseHomeworkSubmitService extends IService<CourseHomeworkSubmit> {

    boolean saveBatchSubmits(String homeworkId, String studentId, List<CourseHomeworkSubmit> submits);

    List<CourseHomeworkSubmit> getByHomeworkId(String homeworkId);
}
