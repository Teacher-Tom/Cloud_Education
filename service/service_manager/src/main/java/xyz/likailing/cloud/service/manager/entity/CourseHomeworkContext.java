package xyz.likailing.cloud.service.manager.entity;

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
 * @author derek
 * @since 2023-03-20
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CourseHomeworkContext对象", description="作业的每一题的内容")
public class CourseHomeworkContext implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String homeworkId;

    private String context;

    private String refAnswer;

    private String pictureUrl;


}
