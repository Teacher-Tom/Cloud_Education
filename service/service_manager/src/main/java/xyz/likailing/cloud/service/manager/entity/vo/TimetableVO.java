package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TimetableVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String courseId;

    private Integer beginWeek;

    private Integer endWeek;

    private Integer dayOfWeek;

    private Integer beginIndex;

    private Integer endIndex;

    private String location;

    private String teacherId;
}
