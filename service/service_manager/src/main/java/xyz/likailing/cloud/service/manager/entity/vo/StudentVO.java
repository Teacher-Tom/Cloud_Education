package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String name;

    private Boolean gender;

    private String major;

    private String department;

    private String className;

    private String userId;
}
