package xyz.likailing.cloud.service.manager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import xyz.likailing.cloud.service.base.model.BaseEntity;

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
@ApiModel(value="CourseHomeworkSubmit对象", description="")
public class CourseHomeworkSubmit implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String homeworkId;

    private String contextId;

    private String studentId;

    private String submitAnswer;

    private Integer score;
}
