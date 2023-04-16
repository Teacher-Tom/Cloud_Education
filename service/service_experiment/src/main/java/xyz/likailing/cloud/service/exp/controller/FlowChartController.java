package xyz.likailing.cloud.service.exp.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.base.model.File;
import xyz.likailing.cloud.service.exp.entity.*;
import xyz.likailing.cloud.service.exp.entity.vo.BranchVo;
import xyz.likailing.cloud.service.exp.entity.vo.NodeInfoVo;
import xyz.likailing.cloud.service.exp.feign.AllsService;
import xyz.likailing.cloud.service.exp.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private NodeDetailService nodeDetailService;

    @Autowired
    private AllsService allsService;

    @Autowired
    private NodeFileService nodeFileService;

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
    /*@ApiOperation("存储节点列表")
    @PostMapping("/node/save/list")
    public R saveNodeList(@RequestBody List<Node> nodes){
        return null;
    }

    @ApiOperation("存储连线列表")
    @PostMapping("/line/save/list")
    public R saveLineList(@RequestBody List<Line> lines){
        return null;
    }*/
    @ApiOperation("初始化实验节点的所有学生完成情况，包括所有参加这个课程的学生")
    @GetMapping("/node/init/{nodeId}")
    public R initNodeDetail(@PathVariable String nodeId){
        if (StringUtils.isEmpty(nodeId)){
            throw new CloudException("节点id为空",20001);
        }
        int count = nodeDetailService.initNodeDetail(nodeId);
        return R.ok().message("初始化了"+count+"个学生的信息");
    }

    @ApiOperation("查询某个节点的所有信息、分支、任务")
    @GetMapping("/node/info/{nodeId}")
    public R getAllInfoByNodeId(@PathVariable String nodeId){
        NodeInfoVo nodeInfoVo = nodeService.getNodeInfoByNodeId(nodeId);
        return R.ok().data("nodeInfo",nodeInfoVo);
    }
    @ApiOperation("保存各学生的节点信息，当该节点任务完成后，设置has_finish为true")
    @PostMapping("/node/detail")
    public R saveNodeDetail(@RequestBody NodeDetail nodeDetail){
        boolean save = nodeDetailService.save(nodeDetail);
        if (save){
            return R.ok().message("保存成功").data("nodeDetail",nodeDetail);
        }else {
            return R.error().message("保存失败");
        }
    }
    @ApiOperation("学生评价难度")
    @PutMapping("/node/detail/diff/{nodeId}/{studentId}")
    public R updateNodeDetailDifficulty(@PathVariable String nodeId, @PathVariable String studentId, @RequestParam Integer difficulty){
        nodeDetailService.updateDifficulty(nodeId,studentId,difficulty);
        return R.ok().message("评价成功");
    }

    @ApiOperation("当提交任务后，学生完成节点，设置has_finish为true，设置完成时间")
    @PutMapping("/node/detail/finish/{nodeId}/{studentId}")
    public R updateNodeDetailFinish(@PathVariable String nodeId, @PathVariable String studentId){
        Integer i = nodeDetailService.updateFinish(nodeId,studentId);
        return R.ok().message("任务完成");
    }

    @ApiOperation("获取学生的节点信息，若没有查到则创建一个")
    @GetMapping("/node/detail/{nodeId}/{studentId}")
    public R getNodeDetail(@PathVariable String nodeId, @PathVariable String studentId){
        NodeDetail nodeDetail = nodeDetailService.getByNodeIdAndStudentId(nodeId,studentId);
        return R.ok().data("nodeDetail",nodeDetail);
    }

    @ApiOperation("计算某节点的完成率")
    @GetMapping("/node/finish-rate/{nodeId}")
    public R calculateFinishingRateByNodeId(@PathVariable String nodeId){
        Double rate = nodeDetailService.calculateFinishRate(nodeId);
        return R.ok().data("finish_rate",rate);
    }
    @ApiOperation("计算某节点平均难度评价与评价人数")
    @GetMapping("/node/diff-avg/{nodeId}")
    public R calculateAverageDifficultyByNodeId(@PathVariable String nodeId){
        Double avg = nodeDetailService.calculateAvgDifficulty(nodeId);
        Integer count = nodeDetailService.countRateNumber(nodeId);
        return R.ok().data("avg",avg).data("count",count);
    }

    @ApiOperation("上传节点资源")
    @PostMapping("/node/resource/upload/{courseId}/{nodeId}")
    public R uploadNodeResource(@PathVariable String courseId, @PathVariable String nodeId, @RequestParam(value = "file") @RequestPart MultipartFile file){
        String memid = courseId;
        String catalogue = "/root";
        R upload = allsService.upload(file, catalogue, memid);
        Map<String, Object> data = upload.getData();
        if (data.containsKey("file_list")) {
            List<File> files = JSON.parseArray(JSON.toJSONString(data.get("file_list")), File.class);
            List<NodeFile> nodeFiles = new ArrayList<>();
            for (File file1 : files) {
                R r = allsService.addFile(file1);
                file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")), File.class);
                NodeFile nodeFile = new NodeFile();
                nodeFile.setFileId(file1.getId());
                nodeFile.setNodeId(nodeId);
                boolean save = nodeFileService.save(nodeFile);
                nodeFiles.add(nodeFile);
            }
            return R.ok().data("file_list",files).data("nodeFile",nodeFiles);
        } else if (data.containsKey("file")) {
            File file1 = JSON.parseObject(JSON.toJSONString(data.get("file")),File.class);
            file1.setMemId(memid);
            file1.setFDir(catalogue);
            R r = allsService.addFile(file1);
            file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")),File.class);
            NodeFile nodeFile = new NodeFile();
            nodeFile.setFileId(file1.getId());
            nodeFile.setNodeId(nodeId);
            boolean save = nodeFileService.save(nodeFile);
            if (save){
                return R.ok().data("file",file1).data("nodeFile",nodeFile);
            }
        }
        return R.error().message("上传文件失败");
    }

    @ApiOperation("查询节点资源")
    @GetMapping("/node/resource/{nodeId}")
    public R getNodeFile(@PathVariable String nodeId){
        List<NodeFile> nodeFiles = nodeFileService.getByNodeId(nodeId);
        List<File> file_list = new ArrayList<>();
        for (NodeFile nodeFile : nodeFiles) {
            R data = allsService.getfileInfo(nodeFile.getFileId());
            List<File> files = JSON.parseArray(JSON.toJSONString(data.getData().get("file")), File.class);
            if(files.size()>0){
                file_list.add(files.get(0));
            }
        }
        return R.ok().data("files",file_list);
    }


}
