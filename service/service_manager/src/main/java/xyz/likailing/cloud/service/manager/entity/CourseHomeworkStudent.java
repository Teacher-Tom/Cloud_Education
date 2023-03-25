package xyz.likailing.cloud.service.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value="CourseHomeworkStudent对象", description="作业与学生关系表")
public class CourseHomeworkStudent implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String studentId;

    private String homeworkId;

    private Integer score;

    private String remark;

    @TableField("is_corrected")
    private Boolean corrected;


}
