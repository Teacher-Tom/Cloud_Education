package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.likailing.cloud.service.exp.entity.Branch;
import xyz.likailing.cloud.service.exp.entity.Node;
import xyz.likailing.cloud.service.exp.entity.NodeDetail;
import xyz.likailing.cloud.service.exp.entity.Task;
import xyz.likailing.cloud.service.exp.entity.vo.NodeInfoVo;
import xyz.likailing.cloud.service.exp.mapper.BranchMapper;
import xyz.likailing.cloud.service.exp.mapper.NodeDetailMapper;
import xyz.likailing.cloud.service.exp.mapper.TaskMapper;
import xyz.likailing.cloud.service.exp.service.NodeService;
import xyz.likailing.cloud.service.exp.mapper.NodeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 12042
* @description 针对表【exp_node】的数据库操作Service实现
* @createDate 2023-03-28 17:18:41
*/
@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node>
    implements NodeService{
    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Override
    public List<Node> getAllNodesByExpId(String expId) {
        QueryWrapper<Node> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("experiment_id",expId);
        List<Node> nodes = baseMapper.selectList(queryWrapper);
        return nodes;
    }

    @Override
    public NodeInfoVo getNodeInfoByNodeId(String nodeId) {
        NodeInfoVo nodeInfoVo = new NodeInfoVo();
        QueryWrapper<Node> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id",nodeId);
        Node node = baseMapper.selectOne(queryWrapper1);
        QueryWrapper<Branch> queryWrapper3 = new QueryWrapper<>();
        queryWrapper3.eq("source_id",nodeId);
        List<Branch> branches = branchMapper.selectList(queryWrapper3);
        QueryWrapper<Task> queryWrapper4 = new QueryWrapper<>();
        queryWrapper4.eq("node_id",nodeId);
        List<Task> tasks = taskMapper.selectList(queryWrapper4);
        nodeInfoVo.setNode(node);
        nodeInfoVo.setTaskList(tasks);
        nodeInfoVo.setBranchList(branches);
        return nodeInfoVo;
    }
}




