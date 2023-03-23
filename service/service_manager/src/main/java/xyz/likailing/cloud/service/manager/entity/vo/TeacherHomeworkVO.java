package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TeacherHomeworkVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String homeworkId;

    private String name;

    private Date beginTime;

    private Date endTime;

    private Boolean outdated;

    private String courseId;

    private String timetableId;

    private String classId;

    private String className;

}
