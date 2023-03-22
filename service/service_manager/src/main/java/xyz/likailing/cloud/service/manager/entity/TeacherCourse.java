package xyz.likailing.cloud.service.manager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import xyz.likailing.cloud.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author likailing
 * @since 2023-03-11
 */
@Data
@Accessors(chain = true)
@TableName("manager_teacher_course")
@ApiModel(value="TeacherCourse对象", description="")
public class TeacherCourse implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.AUTO)
    private String id;

    private String teacherId;

    private String courseId;


}
