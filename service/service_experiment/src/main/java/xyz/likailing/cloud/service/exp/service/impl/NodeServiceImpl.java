package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.exp.entity.Node;
import xyz.likailing.cloud.service.exp.entity.vo.NodeInfoVo;
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
        QueryWrapper<Node> queryWrapper = new QueryWrapper<>();
        return null;
    }
}




