package xyz.likailing.cloud.service.mapper;

import xyz.likailing.cloud.service.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-05-13
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    Integer registerCount(String day);
}
