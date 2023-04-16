package xyz.likailing.cloud.service.exp.service;

import xyz.likailing.cloud.service.exp.entity.NodeFile;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 12042
* @description 针对表【exp_node_file】的数据库操作Service
* @createDate 2023-04-16 08:42:35
*/
public interface NodeFileService extends IService<NodeFile> {

    List<NodeFile> getByNodeId(String nodeId);
}
