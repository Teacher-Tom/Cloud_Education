package xyz.likailing.cloud.service.exp.service;

import xyz.likailing.cloud.service.exp.entity.NodeDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 12042
* @description 针对表【exp_node_detail】的数据库操作Service
* @createDate 2023-03-28 15:09:48
*/
public interface NodeDetailService extends IService<NodeDetail> {


    boolean updateDifficulty(String nodeId, String studentId, Integer difficulty);

    NodeDetail getByNodeIdAndStudentId(String nodeId, String studentId);

    Integer updateFinish(String nodeId, String studentId);

    Double calculateFinishRate(String nodeId);
}
