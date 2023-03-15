package xyz.likailing.cloud.service.manager.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("学生或教师的课程查询对象")
public class CourseQueryVO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty("学生或教师id")
    private String id;

    @ApiModelProperty("学年")
    private Integer year;

    @ApiModelProperty("学期")
    private Integer term;
}
