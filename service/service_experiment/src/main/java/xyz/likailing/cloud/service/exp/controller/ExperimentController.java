package xyz.likailing.cloud.service.exp.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.exp.entity.Experiment;
import xyz.likailing.cloud.service.exp.service.ExperimentService;
import xyz.likailing.cloud.service.exp.service.ScoreService;

import java.util.List;

/**
 * @Author 12042
 * @create 2023/3/28 15:17
 */
@Slf4j
@RestController
@RequestMapping("/exp/course")
@CrossOrigin
@Api(description = "课程实验列表操作")
public class ExperimentController {

    @Autowired
    private ExperimentService experimentService;

    @Autowired
    private ScoreService scoreService;

    @ApiOperation("根据课程id查询所有实验/大作业")
    @GetMapping("/list-exp/{courseId}")
    public R getAllExpByCourseId(@PathVariable String courseId){
        List<Experiment> experimentList = experimentService.listByCourseId(courseId);
        return R.ok().data("list",experimentList);
    }

    @ApiOperation("创建新的实验/大作业")
    @PostMapping("/create-exp")
    public R createExp(@RequestBody Experiment experiment){
        boolean save = experimentService.save(experiment);
        if (save){
            return R.ok().message("创建成功").data("experiment",experiment);
        }else {
            return R.error().message("创建失败");
        }
    }

    @ApiOperation("删除某个实验")
    @DeleteMapping("/delete-exp/{expId}")
    public R deleteExpById(@PathVariable String expId){
        boolean b = experimentService.removeById(expId);
        if (b) {
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    @ApiOperation("修改实验基本信息")
    @PutMapping("/update-exp/{expId}")
    public R updateExpById(@PathVariable String expId,@RequestBody Experiment experiment){
        experiment.setId(expId);
        boolean b = experimentService.updateById(experiment);
        if (b) {
            return R.ok().message("更新成功");
        }else {
            return R.error().message("更新失败");
        }
    }
    @ApiOperation("根据用户id列出所有实验")
    @GetMapping("/list-all/{userId}")
    public R listAllExperiments(@PathVariable String userId){
        List<Experiment> experimentList = experimentService.listAllExpsByUserId(userId);
        return R.ok().data("list",experimentList);
    }
    @ApiOperation("查询实验成绩(整个实验)")
    @GetMapping("/score/{studenId}/{expId}")
    public R getCourseScore(@PathVariable String studenId,@PathVariable String expId){
        int score = scoreService.getByStudentIdAndExpId(studenId,expId);
        return R.ok().data("score",score);
    }
    @ApiOperation("给实验打分")
    @PostMapping("/score/{studentId}/{expId}")
    public R setCourseScore(@PathVariable String studenId,@PathVariable String expId,@RequestParam Integer score){
        Boolean save = scoreService.saveScoreByStudentIdAndExpId(studenId,expId,score);
        if (save){
            return R.ok().message("打分成功");
        }
        return R.error().message("打分失败");
    }




}
