package xyz.likailing.cloud.service.manager.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("管理员的课程查询对象")
public class AdminCourseQueryVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String courseId;

    private String courseName;

    private String department;

    private Integer year;

    private Integer term;
}
