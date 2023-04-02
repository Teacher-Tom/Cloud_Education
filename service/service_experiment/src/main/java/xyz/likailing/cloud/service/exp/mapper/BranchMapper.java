package xyz.likailing.cloud.service.exp.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import xyz.likailing.cloud.service.exp.entity.Branch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author 12042
* @description 针对表【exp_branch】的数据库操作Mapper
* @createDate 2023-04-02 23:19:25
* @Entity xyz.likailing.cloud.service.exp.entity.Branch
*/
public interface BranchMapper extends BaseMapper<Branch> {
    List<Branch> getAllBySourceIdOrderByToId(@Param("sourceId") String sourceId);
}




