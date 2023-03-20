package xyz.likailing.cloud.service.mapper;

import xyz.likailing.cloud.service.entity.Authority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 12042
* @description 针对表【authority】的数据库操作Mapper
* @createDate 2023-03-19 11:58:18
* @Entity xyz.likailing.cloud.service.entity.Authority
*/
public interface AuthorityMapper extends BaseMapper<Authority> {
    List<String> selectParamsByUserId(String userid);
}




