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
@ApiModel(value="MessageUser对象", description="")
public class MessageUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    private String userId;

    private String msgId;

    @ApiModelProperty(value = "已读")
    private Boolean hasRead;


}
