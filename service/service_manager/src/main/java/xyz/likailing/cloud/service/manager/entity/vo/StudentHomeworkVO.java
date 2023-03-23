package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class StudentHomeworkVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String homeworkId;

    private String name;

    private Date beginTime;

    private Date endTime;

    private Boolean outdated;

    private Boolean marked;

    private Integer score;

    private String courseName;

    private String timetableId;

    private String teacherName;

}
