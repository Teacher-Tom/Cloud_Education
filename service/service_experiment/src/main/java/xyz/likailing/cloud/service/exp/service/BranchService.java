package xyz.likailing.cloud.service.exp.service;

import xyz.likailing.cloud.service.exp.entity.Branch;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.exp.entity.vo.BranchVo;

/**
* @author 12042
* @description 针对表【exp_branch】的数据库操作Service
* @createDate 2023-04-02 23:19:25
*/
public interface BranchService extends IService<Branch> {

    BranchVo getBranchAndLinkedNodesBySourceId(String nodeId);
}
