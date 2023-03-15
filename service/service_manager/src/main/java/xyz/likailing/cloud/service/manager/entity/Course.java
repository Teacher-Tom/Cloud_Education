package xyz.likailing.cloud.service.manager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
 * @author likailing
 * @since 2023-03-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("manager_course")
@ApiModel(value = "Course对象", description = "")
public class Course extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer creditHour;

    private Integer creditNum;

    private Boolean type;

    private Integer year;

    private String department;

    private Integer term;

    private Integer beginWeek;

    private Integer endWeek;

    private String coverUrl;


}
