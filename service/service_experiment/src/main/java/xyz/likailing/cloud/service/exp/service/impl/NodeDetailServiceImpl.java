package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.exp.entity.Experiment;
import xyz.likailing.cloud.service.exp.entity.Node;
import xyz.likailing.cloud.service.exp.entity.NodeDetail;
import xyz.likailing.cloud.service.exp.mapper.ExperimentMapper;
import xyz.likailing.cloud.service.exp.mapper.NodeMapper;
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

    @Autowired
    NodeMapper nodeMapper;
    @Autowired
    ExperimentMapper experimentMapper;
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

    @Override
    public Double calculateAvgDifficulty(String nodeId) {
        Float avg = baseMapper.selectDifficultyByNodeIdAndDifficultyGreaterThan(nodeId, 0);
        if (avg == null){
            return 0.0;
        }
        return new Double(avg);
    }

    @Override
    public Integer countRateNumber(String nodeId) {
        int count = baseMapper.countByDifficultyIsNotNullAndNodeIdAndDifficultyGreaterThan(nodeId, 0);
        return count;
    }

    @Override
    public int initNodeDetail(String nodeId) {
        // 查询本实验包含的所有学生
        Node node = nodeMapper.selectById(nodeId);
        if (node == null){
            throw new CloudException("没有该节点id",20001);
        }
        String experimentId = node.getExperimentId();
        Experiment experiment = experimentMapper.selectById(experimentId);
        if (experiment == null){
            throw new CloudException("实验不存在",20001);
        }
        String courseId = experiment.getCourseId();
        List<String> studentIds = baseMapper.selectStudentIdByCourseId(courseId);
        // 初始化学生信息
        for (String studentId : studentIds) {
            NodeDetail nodeDetail = new NodeDetail();
            nodeDetail.setNodeId(nodeId);
            nodeDetail.setStudentId(studentId);
            nodeDetail.setHasFinish(false);
            baseMapper.insert(nodeDetail);
        }
        return studentIds.size();
    }
}




