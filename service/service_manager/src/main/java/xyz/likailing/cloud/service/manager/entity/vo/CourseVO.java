package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;
import xyz.likailing.cloud.service.manager.entity.Class;
import xyz.likailing.cloud.service.manager.entity.Teacher;

import java.io.Serializable;
import java.util.List;

@Data
public class CourseVO implements Serializable {

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

    private String coverUrl;

    private List<Teacher> teachers;

    private List<Class> classes;
}
