package xyz.likailing.cloud.service.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.CourseHomework;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkContext;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkStudent;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkSubmit;
import xyz.likailing.cloud.service.manager.entity.vo.HomeworkCorrectVO;
import xyz.likailing.cloud.service.manager.entity.vo.StudentHomeworkVO;
import xyz.likailing.cloud.service.manager.entity.vo.TeacherHomeworkVO;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkContextService;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkService;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkStudentService;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkSubmitService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author derek
 * @since 2023-03-20
 */
@Slf4j
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

    /* 教师 */

    @ApiOperation("保存作业信息")
    @PostMapping("/save")
    public R saveHomework(@ApiParam("作业基本信息") CourseHomework homework,
                          @ApiParam("作业内容信息") @RequestBody List<CourseHomeworkContext> contexts) {
        String id = homeworkService.saveHomework(homework, contexts);
        if (id != null) {
            return R.ok().data("homeworkId", id).message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("根据教师id获取作业列表")
    @GetMapping("/list-teacher/{teacherId}")
    public R listByTeacherId(@ApiParam(value = "教师id", required = true) @PathVariable String teacherId) {
        List<TeacherHomeworkVO> homeworks = homeworkService.listTeacherHomework(teacherId);
        return R.ok().data("homeworks", homeworks);
    }

    @ApiOperation("根据id获取作业完成学生信息，只能查询提交了的学生，未提交查询不到")
    @GetMapping("/get-marked/{id}")
    public R getHomework(@ApiParam(value = "作业id", required = true) @PathVariable String id) {
        //作业基本信息
        CourseHomework homework = homeworkService.getById(id);
        //学生提交情况
        QueryWrapper<CourseHomeworkStudent> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("homework_id", id);
        List<CourseHomeworkStudent> students = homeworkStudentService.list(studentQueryWrapper);

        if (!ObjectUtils.isEmpty(homework)) {
            return R.ok()
                    .data("homework", homework)
                    .data("studentNumber", students.size())
                    .data("markedStudents", students);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("批改学生作业，上传得分和评语")
    @PostMapping("/correct")
    public R correct(@ApiParam("批改信息") @RequestBody HomeworkCorrectVO correctVO) {
        QueryWrapper<CourseHomeworkStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("homework_id", correctVO.getHomeworkId()).eq("student_id", correctVO.getStudentId());
        CourseHomeworkStudent homeworkStudent = homeworkStudentService.getOne(wrapper);
        if (!ObjectUtils.isEmpty(homeworkStudent)) {
            //更新得分和评语
            homeworkStudent.setScore(correctVO.getScore());
            homeworkStudent.setRemark(correctVO.getRemark());
            homeworkStudent.setCorrected(true); //标记已批改
            boolean update = homeworkStudentService.updateById(homeworkStudent);
            if (update) {
                return R.ok().message("批改成功");
            }
        }
        return R.error().message("数据不存在");
    }

    /* 学生 */

    @ApiOperation("根据学生id获取作业列表")
    @GetMapping("/list-student/{studentId}")
    public R listByStudentId(@ApiParam(value = "学生id", required = true) @PathVariable String studentId) {
        List<StudentHomeworkVO> homeworks = homeworkService.listStudentHomework(studentId);
        return R.ok().data("homeworks", homeworks);
    }

    @ApiOperation("学生提交作业的文本内容，不包含附件")
    @PostMapping("/submit/{studentId}/{homeworkId}")
    public R submit(@ApiParam(value = "学生id", required = true) @PathVariable String studentId,
                    @ApiParam(value = "作业id", required = true) @PathVariable String homeworkId,
                    @ApiParam("每个小题的内容") @RequestBody List<CourseHomeworkSubmit> submits) {
        //检查是否已批改
        QueryWrapper<CourseHomeworkStudent> wrapper = new QueryWrapper<>();
        wrapper.eq("homework_id", homeworkId).eq("student_id", studentId);
        CourseHomeworkStudent homeworkStudent = homeworkStudentService.getOne(wrapper);
        if (!ObjectUtils.isEmpty(homeworkStudent) && homeworkStudent.getCorrected()) {
            return R.error().message("已批改，不能提交");
        }
        //记录作业提交的内容，未批改时可以重复提交
        boolean saveBatch = submitService.saveBatchSubmits(homeworkId, studentId, submits);
        if (saveBatch) {
            if (ObjectUtils.isEmpty(homeworkStudent)) {
                //在作业完成情况表中记录
                homeworkStudent = new CourseHomeworkStudent();
                homeworkStudent.setStudentId(studentId);
                homeworkStudent.setHomeworkId(homeworkId);
                boolean save = homeworkStudentService.save(homeworkStudent);
                if (save) {
                    return R.ok().message("提交成功");
                }
            } else {
                return R.ok().message("提交成功");
            }
        }
        return R.error().message("提交失败");
    }

    /* 共用 */

    @ApiOperation("根据id获取作业所有题目内容信息")
    @GetMapping("/get-context/{id}")
    public R getContext(@ApiParam(value = "作业id", required = true) @PathVariable String id) {
        //作业基本信息
        CourseHomework homework = homeworkService.getById(id);
        //作业详细内容
        QueryWrapper<CourseHomeworkContext> contextQueryWrapper = new QueryWrapper<>();
        contextQueryWrapper.eq("homework_id", id);
        List<CourseHomeworkContext> contexts = contextService.list(contextQueryWrapper);
        //TODO 作业附件

        if (!ObjectUtils.isEmpty(homework)) {
            return R.ok()
                    .data("homework", homework)
                    .data("contexts", contexts);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据学生和作业id获取作业提交信息，包括作业内容、作业是否提交、提交的答案、以及评分和参考答案等")
    @GetMapping("/get-submit/{id}/{studentId}")
    public R getStudentSubmit(@ApiParam(value = "作业id", required = true) @PathVariable String id,
                              @ApiParam(value = "学生id", required = true) @PathVariable String studentId) {
        //作业基本信息
        StudentHomeworkVO homework = homeworkService.getStudentHomework(studentId, id);
        //作业详细内容
        QueryWrapper<CourseHomeworkContext> contextQueryWrapper = new QueryWrapper<>();
        contextQueryWrapper.eq("homework_id", id);
        List<CourseHomeworkContext> contexts = contextService.list(contextQueryWrapper);
        //学生提交内容
        QueryWrapper<CourseHomeworkSubmit> submitQueryWrapper = new QueryWrapper<>();
        submitQueryWrapper.eq("homework_id", id).eq("student_id", studentId);
        List<CourseHomeworkSubmit> submits = submitService.list(submitQueryWrapper);
        //TODO 作业附件
        //TODO 学生提交的附件

        if (!ObjectUtils.isEmpty(homework)) {
            return R.ok()
                    .data("homework", homework)
                    .data("contexts", contexts)
                    .data("submits", submits);
        }
        return R.error().message("数据不存在");
    }

    /* 过期 */

    @Scheduled(fixedRate = 60000) //距离项目启动每1分钟执行一次
    public void expire() {
        //首先拿到所有的过期且outdated=0的条目
        List<CourseHomework> homeworkList = homeworkService.listExpiredHomework();
        if (homeworkList.isEmpty()) {
            log.info("没有需要更新的过期信息");
            return;
        }
        //遍历更新
        for (CourseHomework homework : homeworkList) {
            homework.setOutdated(true);
            boolean update = homeworkService.updateById(homework);
            if (update) {
                log.info("{} : 更新成功，已过期", homework.getId());
            } else {
                log.info("{} : 更新失败", homework.getId());
            }
        }
    }

}

