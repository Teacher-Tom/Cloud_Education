package xyz.likailing.cloud.service.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.CourseHomework;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkContext;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkStudent;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkSubmit;
import xyz.likailing.cloud.service.manager.entity.vo.HomeworkVO;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkContextService;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkService;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkStudentService;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkSubmitService;

import java.util.HashSet;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author derek
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/manager/course-homework")
public class CourseHomeworkController {

    @Autowired
    private CourseHomeworkService homeworkService;
    @Autowired
    private CourseHomeworkContextService contextService;
    @Autowired
    private CourseHomeworkStudentService homeworkStudentService;
    @Autowired
    private CourseHomeworkSubmitService submitService;

    @ApiOperation("保存作业信息")
    @PostMapping("/save")
    public R saveHomework(@ApiParam("作业基本信息") CourseHomework homework,
                          @ApiParam("作业内容信息") @RequestBody List<CourseHomeworkContext> contexts) {
        String id = homeworkService.saveHomework(homework, contexts);
        if(id != null) {
            return R.ok().data("homeworkId", id).message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("根据教师id获取作业列表")
    @GetMapping("/list-teacher/{teacherId}")
    public R listByTeacherId(@ApiParam(value = "教师id", required = true) @PathVariable String teacherId) {
        List<CourseHomework> homeworks = homeworkService.listTeacherHomework(teacherId);
        return R.ok().data("homeworks", homeworks);
    }

    @ApiOperation("根据id获取作业详细信息")
    @GetMapping("/get-info/{id}")
    public R getHomework(@ApiParam(value = "作业id", required = true) @PathVariable String id) {
        //作业基本信息
        CourseHomework homework = homeworkService.getById(id);
        //作业详细内容
        QueryWrapper<CourseHomeworkContext> contextQueryWrapper = new QueryWrapper<>();
        contextQueryWrapper.eq("homework_id", id);
        List<CourseHomeworkContext> contexts = contextService.list(contextQueryWrapper);
        //学生提交情况
        QueryWrapper<CourseHomeworkStudent> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("homework_id", id);
        List<CourseHomeworkStudent> students = homeworkStudentService.list(studentQueryWrapper);
        if(!ObjectUtils.isEmpty(homework)) {
            return R.ok()
                    .data("homework", homework)
                    .data("contexts", contexts)
                    .data("studentNumber", students.size())
                    .data("students", students);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id获取某学生的作业提交信息")
    @GetMapping("/get-submit/{id}/{studentId}")
    public R getStudentSubmit(@ApiParam(value = "作业id", required = true) @PathVariable String id,
                              @ApiParam(value = "学生id", required = true) @PathVariable String studentId) {
        //作业基本信息
        CourseHomework homework = homeworkService.getById(id);
        //作业详细内容
        QueryWrapper<CourseHomeworkContext> contextQueryWrapper = new QueryWrapper<>();
        contextQueryWrapper.eq("homework_id", id);
        List<CourseHomeworkContext> contexts = contextService.list(contextQueryWrapper);
        //学生提交内容
        QueryWrapper<CourseHomeworkSubmit> submitQueryWrapper = new QueryWrapper<>();
        submitQueryWrapper.eq("homework_id", id).eq("student_id", studentId);
        List<CourseHomeworkSubmit> submits = submitService.list(submitQueryWrapper);
        if(!ObjectUtils.isEmpty(homework)) {
            return R.ok()
                    .data("homework", homework)
                    .data("contexts", contexts)
                    .data("submits", submits);
        }
        return R.error().message("数据不存在");
    }

}

