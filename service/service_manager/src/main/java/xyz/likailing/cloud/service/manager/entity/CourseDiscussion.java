package xyz.likailing.cloud.service.manager.entity;

import xyz.likailing.cloud.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author derek
 * @since 2023-04-10
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CourseDiscussion对象", description="")
public class CourseDiscussion implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String resourceId;

    private String userId;

    private String content;

    private Integer page;

    private Date sendTime;

    private Integer likes;


}
