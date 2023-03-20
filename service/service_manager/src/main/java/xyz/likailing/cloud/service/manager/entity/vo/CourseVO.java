package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseId;

    private String courseName;

    private String teacherName;

    private String className;

    private Integer beginWeek;

    private Integer endWeek;

    private String coverUrl;
}
