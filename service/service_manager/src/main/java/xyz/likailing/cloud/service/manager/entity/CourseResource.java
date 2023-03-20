package xyz.likailing.cloud.service.manager.entity;

import xyz.likailing.cloud.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="CourseResource对象", description="")
public class CourseResource extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "云盘文件id")
    private String fileId;

    private String timetableId;

    @ApiModelProperty(value = "是否为全局资源（即小节资源和全局资源）")
    @TableField("is_global")
    private Boolean global;

    private String courseId;


}
