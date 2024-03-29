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
 * @author derek
 * @since 2023-03-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("manager_chapter")
@ApiModel(value="Chapter对象", description="")
public class Chapter extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "章节名")
    private String name;

    @ApiModelProperty(value = "章节号")
    private Integer number;


}
