package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.exp.entity.Node;
import xyz.likailing.cloud.service.exp.service.NodeService;
import xyz.likailing.cloud.service.exp.mapper.NodeMapper;
import org.springframework.stereotype.Service;

/**
* @author 12042
* @description 针对表【exp_node】的数据库操作Service实现
* @createDate 2023-03-28 15:09:48
*/
@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node>
    implements NodeService{

}




