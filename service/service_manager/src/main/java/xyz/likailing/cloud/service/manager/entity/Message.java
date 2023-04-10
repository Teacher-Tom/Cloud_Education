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
 * @since 2023-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="Message对象", description="")
public class Message extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String courseId;

    private String teacherId;

    private String title;

    private String content;

    private String author;


}
