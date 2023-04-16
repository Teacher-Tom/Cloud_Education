package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.exp.entity.NodeFile;
import xyz.likailing.cloud.service.exp.service.NodeFileService;
import xyz.likailing.cloud.service.exp.mapper.NodeFileMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 12042
* @description 针对表【exp_node_file】的数据库操作Service实现
* @createDate 2023-04-16 08:42:35
*/
@Service
public class NodeFileServiceImpl extends ServiceImpl<NodeFileMapper, NodeFile>
    implements NodeFileService{

    @Override
    public List<NodeFile> getByNodeId(String nodeId) {
        QueryWrapper<NodeFile> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("node_id",nodeId);
        List<NodeFile> nodeFiles = baseMapper.selectList(queryWrapper);
        return nodeFiles;
    }
}




