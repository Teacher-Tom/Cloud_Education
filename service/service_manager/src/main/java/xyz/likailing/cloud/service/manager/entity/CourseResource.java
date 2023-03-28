package xyz.likailing.cloud.service.manager.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import xyz.likailing.cloud.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="CourseResource对象", description="")
public class CourseResource implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "云盘文件id",required = true)
    private String fileId;

    @ApiModelProperty(value = "所属小节资源id，在全局资源时不需要",required = false)
    private String timetableId;

    @ApiModelProperty(value = "是否为全局资源（即小节资源和全局资源）",required = true)
    @TableField("is_global")
    private Boolean global;

    @ApiModelProperty(value = "资源类型：0pdf,1视频，2共享，3其他",required = true)

    private Integer type;

    private String courseId;

    @ApiModelProperty(value = "创建时间",required = false)
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    public CourseResource() {
        this.type = 3;
    }
}
