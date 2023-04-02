package xyz.likailing.cloud.service.exp.entity.vo;

import lombok.Data;
import xyz.likailing.cloud.service.exp.entity.Branch;
import xyz.likailing.cloud.service.exp.entity.Line;
import xyz.likailing.cloud.service.exp.entity.Node;

import java.util.List;

/**
 * @Author 12042
 * @create 2023/4/2 23:20
 */
@Data
public class BranchVo {
    private List<Branch> branches;
    private Node source;
    private List<Node> linkedNodes;
    private List<Line> lines;
}
