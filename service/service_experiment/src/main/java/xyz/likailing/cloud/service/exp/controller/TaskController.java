package xyz.likailing.cloud.service.exp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.exp.entity.Task;

/**
 * @Author 12042
 * @create 2023/4/11 16:10
 */
@Slf4j
@RestController
@RequestMapping("/exp/task")
@CrossOrigin
@Api(description = "课程实验任务操作")
public class TaskController {
    @ApiOperation("保存节点任务")
    @PostMapping("/node")
    public R saveNodeTask(@RequestBody Task task){
        return null;
    }
    @ApiOperation("修改节点任务")
    @PutMapping("/node/{taskId}")
    public R updateNodeTask(@PathVariable String taskId){
        return null;
    }
    @ApiOperation("删除节点任务")
    @DeleteMapping("/node/{taskId}")
    public R deleteNodeTask(@PathVariable String taskId){
        return null;
    }

    @ApiOperation("查询某个任务")
    @GetMapping("/node/{taskId}")
    public R getNodeTask(@PathVariable String taskId){
        return null;
    }
    @ApiOperation("学生提交任务作业")
    @PostMapping("/submit")
    public R saveSubmitTask(@RequestParam String studentId,
                        @RequestParam String taskId,
                        @RequestParam String content,
                        @RequestParam(value = "file",required = false) @RequestPart MultipartFile file){
        return null;
    }

    @ApiOperation("学生修改任务作业")
    @PutMapping("/submit/{studentId}/{taskId}")
    public R updateSubmitTask(@RequestParam String studentId,
                            @RequestParam String taskId,
                            @RequestParam String content,
                            @RequestParam(value = "file",required = false) @RequestPart MultipartFile file){
        return null;
    }

    @ApiOperation("查看学生任务情况")
    @GetMapping("/submit/{studentId}/{taskId}")
    public R getSubmitTask(@PathVariable String studentId, @PathVariable String taskId){
        return null;
    }
    @ApiOperation("老师批改打分")
    @PostMapping("/submit/{studentId}/{taskId}")
    public R scoreSubmitTask(@PathVariable String studentId,
                             @PathVariable String taskId,
                             @RequestParam Integer score,
                             @RequestParam String review){
        return null;
    }




}
