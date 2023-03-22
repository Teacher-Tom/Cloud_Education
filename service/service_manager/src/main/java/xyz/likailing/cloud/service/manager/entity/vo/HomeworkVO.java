package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;
import xyz.likailing.cloud.service.manager.entity.CourseHomework;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkContext;

import java.io.Serializable;
import java.util.List;

@Data
public class HomeworkVO implements Serializable {

    private static final long serialVersionUID=1L;

    private CourseHomework courseHomework;

    private List<CourseHomeworkContext> contexts;
}
