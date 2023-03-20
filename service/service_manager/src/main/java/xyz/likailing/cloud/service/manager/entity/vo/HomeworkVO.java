package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkContext;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class HomeworkVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String name;

    private Date beginTime;

    private Date endTime;

    private Boolean outdated;

    private Boolean marked;

    private Integer score;

    private String review;

    private String courseId;

    private String timetableId;

    private String isGlobal;

    private List<CourseHomeworkContext> contexts;
}
