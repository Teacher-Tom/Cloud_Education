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
 * 每条讨论下附带的图片、文件、链接资源
 * </p>
 *
 * @author derek
 * @since 2023-04-10
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CourseDiscussionResource对象", description="每条讨论下附带的图片、文件、链接资源")
public class CourseDiscussionResource implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String discussionId;

    private String resourceId;


}
