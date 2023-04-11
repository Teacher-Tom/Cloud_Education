package xyz.likailing.cloud.service.exp.entity.vo;

import lombok.Data;
import xyz.likailing.cloud.service.exp.entity.Branch;
import xyz.likailing.cloud.service.exp.entity.Node;
import xyz.likailing.cloud.service.exp.entity.NodeDetail;
import xyz.likailing.cloud.service.exp.entity.Task;

import java.util.List;

/**
 * @Author 12042
 * @create 2023/4/10 16:00
 */
@Data
public class NodeInfoVo {
    private Node node;
    private List<Task> taskList;
    private List<Branch> branchList;
}
