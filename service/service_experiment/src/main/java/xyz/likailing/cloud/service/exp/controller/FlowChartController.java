package xyz.likailing.cloud.service.exp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.exp.entity.Branch;
import xyz.likailing.cloud.service.exp.entity.Line;
import xyz.likailing.cloud.service.exp.entity.Node;
import xyz.likailing.cloud.service.exp.entity.vo.BranchVo;
import xyz.likailing.cloud.service.exp.service.BranchService;
import xyz.likailing.cloud.service.exp.service.LineService;
import xyz.likailing.cloud.service.exp.service.NodeService;

import java.util.List;
import java.util.Objects;

/**
 * @Author 12042
 * @create 2023/3/28 15:58
 */
@Slf4j
@RestController
@RequestMapping("/exp/flowchart")
@CrossOrigin
@Api(description = "流程图操作")
public class FlowChartController {
    @Autowired
    private NodeService nodeService;

    @Autowired
    private LineService lineService;

    @Autowired
    private BranchService branchService;

    @ApiOperation("添加节点")
    @PostMapping("/node")
    public R addNode(@RequestBody Node node){
        boolean save = nodeService.save(node);
        if (save){
            return R.ok().message("增加成功").data("node",node);
        }else {
            return R.error().message("增加失败");
        }
    }

    @ApiOperation("获取节点信息")
    @GetMapping("/node/{nodeId}")
    public R getNode(@PathVariable String nodeId){
        Node node = nodeService.getById(nodeId);
        return R.ok().data("node",node);
    }

    @ApiOperation("删除某个节点")
    @DeleteMapping("/node/{nodeId}")
    public R deleteNode(@PathVariable String nodeId){
        boolean b = nodeService.removeById(nodeId);
        if (b){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }
    @ApiOperation("更新节点")
    @PutMapping("/node/{nodeId}")
    public R updateNode(@PathVariable String nodeId,@RequestBody Node node){
        if (Objects.isNull(node)){
            throw new CloudException(ResultCodeEnum.PARAM_ERROR);
        }
        node.setId(nodeId);
        boolean b = nodeService.updateById(node);
        if (b){
            return R.ok().message("更新成功");
        }else {
            return R.error().message("更新失败");
        }
    }

    @ApiOperation("增加连线")
    @PostMapping("/line")
    public R addLine(@RequestBody Line line){
        boolean save = lineService.save(line);
        if (save){
            return R.ok().data("line",line);
        }else {
            return R.error().message("增加连线失败");
        }
    }

    @ApiOperation("查询连线")
    @GetMapping("/line/{lineId}")
    public R getLine(@PathVariable String lineId){
        Line line = lineService.getById(lineId);
        return R.ok().data("line",line);
    }
    @ApiOperation("删除连线")
    @DeleteMapping("/line/{lineId}")
    public R deleteLine(@PathVariable String lineId){
        boolean b = lineService.removeById(lineId);
        if (b){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("更新连线")
    @PutMapping("/line/{lineId}")
    public R updateLine(@PathVariable String lineId,@RequestBody Line line){
        if (Objects.isNull(line)){
            throw new CloudException(ResultCodeEnum.PARAM_ERROR);
        }
        line.setId(lineId);
        boolean b = lineService.updateById(line);
        if (b){
            return R.ok().message("更新成功");
        }else {
            return R.error().message("更新失败");
        }
    }

    @ApiOperation("查询某个实验的所有节点")
    @GetMapping("/node/list/{expId}")
    public R getAllNodesByExpId(@PathVariable String expId){
        List<Node> nodes = nodeService.getAllNodesByExpId(expId);
        return R.ok().data("list",nodes);
    }

    @ApiOperation("查询某个实验的所有连线")
    @GetMapping("/line/list/{expId}")
    public R getAllLinesByExpId(@PathVariable String expId){
        List<Line> lines = lineService.getAllLinesByExpId(expId);
        return R.ok().data("list",lines);
    }
    @ApiOperation("查询分支节点及其关联的所有节点和连线")
    @GetMapping("/node/branch/{nodeId}")
    public R getBranchNodeWithLinkedNodes(@PathVariable String nodeId){
        Node branchNode = nodeService.getById(nodeId);
        if (branchNode.getType() != 3){
            throw new CloudException("不是分支节点",20001);
        }
        BranchVo branchVo = branchService.getBranchAndLinkedNodesBySourceId(nodeId);
        R branchInfo = R.ok().data("branchInfo", branchVo);
        return branchInfo;
    }

}
