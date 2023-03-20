package xyz.likailing.cloud.service.manager.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import xyz.likailing.cloud.service.base.model.BaseEntity;

/**
 * <p>
 *
 * </p>
 *
 * @author derek
 * @since 2023-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="CourseHomeworkContext对象", description="")
public class CourseHomeworkSubmit extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String homeworkId;

    private String contextId;

    private String studentId;

    private String submitAnswer;
}
