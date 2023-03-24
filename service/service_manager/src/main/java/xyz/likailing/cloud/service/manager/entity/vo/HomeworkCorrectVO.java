package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class HomeworkCorrectVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String studentId;

    private String homeworkId;

    private Integer score;

    private String remark;
}
