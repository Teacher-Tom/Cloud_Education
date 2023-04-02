package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.likailing.cloud.service.exp.entity.Branch;
import xyz.likailing.cloud.service.exp.entity.Line;
import xyz.likailing.cloud.service.exp.entity.Node;
import xyz.likailing.cloud.service.exp.entity.vo.BranchVo;
import xyz.likailing.cloud.service.exp.mapper.LineMapper;
import xyz.likailing.cloud.service.exp.mapper.NodeMapper;
import xyz.likailing.cloud.service.exp.service.BranchService;
import xyz.likailing.cloud.service.exp.mapper.BranchMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 12042
* @description 针对表【exp_branch】的数据库操作Service实现
* @createDate 2023-04-02 23:19:25
*/
@Service
public class BranchServiceImpl extends ServiceImpl<BranchMapper, Branch>
    implements BranchService{
    @Autowired
    private NodeMapper nodeMapper;

    @Autowired
    private LineMapper lineMapper;
    @Override
    public BranchVo getBranchAndLinkedNodesBySourceId(String nodeId) {
        BranchVo branchVo = new BranchVo();
        List<Branch> branches = baseMapper.getAllBySourceIdOrderByToId(nodeId);
        QueryWrapper<Node> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",nodeId);
        Node sourceNode = nodeMapper.selectOne(queryWrapper);
        List<Line> lines = lineMapper.getAllByFromNodeIdOrderByToNodeId(nodeId);
        List<String> linkedNodeIds = new ArrayList<>();
        for (Branch branch : branches) {
            String toId = branch.getToId();
            linkedNodeIds.add(toId);
        }
        List<Node> linkedNodes = nodeMapper.selectBatchIds(linkedNodeIds);
        branchVo.setSource(sourceNode);
        branchVo.setLinkedNodes(linkedNodes);
        branchVo.setLines(lines);
        branchVo.setBranches(branches);
        return branchVo;
    }
}




