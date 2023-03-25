package xyz.likailing.cloud.service.manager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @author likailing
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("manager_timetable")
@ApiModel(value="Timetable对象", description="")
public class Timetable extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String courseId;

    private Date date;

    private Integer week;

    private Integer dayOfWeek;

    private Integer beginIndex;

    private Integer endIndex;

    private String location;

    private String teacherId;

    @TableField("is_outdated")
    private Boolean outdated;


}
