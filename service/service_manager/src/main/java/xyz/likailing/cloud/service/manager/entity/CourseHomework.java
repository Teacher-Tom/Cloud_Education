package xyz.likailing.cloud.service.manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import org.springframework.format.annotation.DateTimeFormat;
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
 * @since 2023-03-20
 */
@Data
@Accessors(chain = true)
@ApiModel(value="CourseHomework对象", description="作业基本信息")
public class CourseHomework implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String name;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @TableField("is_outdated")
    private Boolean outdated;

    private String review;

    private String courseId;

    private String teacherId;

    private String timetableId;

    private Boolean isGlobal;

    private String nodeId;


}
