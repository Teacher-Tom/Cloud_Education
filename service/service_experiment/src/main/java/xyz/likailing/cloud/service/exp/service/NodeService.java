package xyz.likailing.cloud.service.exp.service;

import xyz.likailing.cloud.service.exp.entity.Node;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.exp.entity.vo.NodeInfoVo;

import java.util.List;

/**
* @author 12042
* @description 针对表【exp_node】的数据库操作Service
* @createDate 2023-03-28 17:18:41
*/
public interface NodeService extends IService<Node> {

    List<Node> getAllNodesByExpId(String expId);

    NodeInfoVo getNodeInfoByNodeId(String nodeId);
}
