package xyz.likailing.cloud.service.manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import xyz.likailing.cloud.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="CourseHomework对象", description="")
public class CourseHomework extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String name;

    private Date beginTime;

    private Date endTime;

    @TableField("is_outdated")
    private Boolean outdated;

    @TableField("is_marked")
    private Boolean marked;

    private Integer score;

    private String review;

    private String courseId;

    private String timetableId;

    private String isGlobal;


}
