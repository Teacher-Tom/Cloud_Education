package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminCourseVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String courseId;

    private String courseName;

    private Integer creditHour;

    private Integer creditNum;

    private Boolean type;

    private Integer year;

    private Integer term;

    private Integer beginWeek;

    private Integer endWeek;

    private String department;

    private String teacherName;

    private String className;

    private String coverUrl;
}
