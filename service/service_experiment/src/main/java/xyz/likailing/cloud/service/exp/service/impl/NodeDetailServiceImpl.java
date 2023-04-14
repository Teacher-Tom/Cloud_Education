package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.exp.entity.NodeDetail;
import xyz.likailing.cloud.service.exp.service.NodeDetailService;
import xyz.likailing.cloud.service.exp.mapper.NodeDetailMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author 12042
* @description 针对表【exp_node_detail】的数据库操作Service实现
* @createDate 2023-03-28 15:09:48
*/
@Service
public class NodeDetailServiceImpl extends ServiceImpl<NodeDetailMapper, NodeDetail>
    implements NodeDetailService{


    @Override
    public boolean updateDifficulty(String nodeId, String studentId, Integer difficulty) {
        int i = baseMapper.updateDifficultyByNodeIdAndStudentId(difficulty, nodeId, studentId);
        return true;
    }

    @Override
    public NodeDetail getByNodeIdAndStudentId(String nodeId, String studentId) {
        List<NodeDetail> nodeDetails = baseMapper.getByNodeIdAndStudentId(nodeId, studentId);
        if (nodeDetails.size() > 1){
            throw new CloudException("重复的节点学生信息",20001);
        } else if (nodeDetails.size() == 0) {
            NodeDetail nodeDetail = new NodeDetail();
            nodeDetail.setNodeId(nodeId);
            nodeDetail.setStudentId(studentId);
            nodeDetail.setHasFinish(false);
            baseMapper.insert(nodeDetail);
            return nodeDetail;
        }
        return nodeDetails.get(0);
    }

    @Override
    public Integer updateFinish(String nodeId, String studentId) {
        QueryWrapper<NodeDetail> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("node_id",nodeId);
        queryWrapper.eq("student_id",studentId);
        NodeDetail nodeDetail = new NodeDetail();
        nodeDetail.setHasFinish(true);
        nodeDetail.setFinishTime(new Date());
        int update = baseMapper.update(nodeDetail, queryWrapper);
        return update;
    }

    @Override
    public Double calculateFinishRate(String nodeId) {
        // 查询该节点的所有学生信息
        int finish = baseMapper.countByHasFinishAndNodeId(true, nodeId);
        int total = baseMapper.countByNodeId(nodeId);
        return ((double)finish)/total;
    }
}




