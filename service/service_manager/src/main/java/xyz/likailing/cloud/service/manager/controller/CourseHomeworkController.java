package xyz.likailing.cloud.service.manager.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.base.model.File;
import xyz.likailing.cloud.service.manager.entity.*;
import xyz.likailing.cloud.service.manager.entity.vo.HomeworkCorrectVO;
import xyz.likailing.cloud.service.manager.entity.vo.MessageVo;
import xyz.likailing.cloud.service.manager.entity.vo.StudentHomeworkVO;
import xyz.likailing.cloud.service.manager.entity.vo.TeacherHomeworkVO;
import xyz.likailing.cloud.service.manager.feign.AllsService;
import xyz.likailing.cloud.service.manager.feign.MsgService;
import xyz.likailing.cloud.service.manager.mapper.TeacherMapper;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkContextService;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkService;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkStudentService;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkSubmitService;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
@CrossOrigin
public class CourseHomeworkController {

    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private CourseHomeworkService homeworkService;
    @Autowired
    private CourseHomeworkContextService contextService;
    @Autowired
    private CourseHomeworkStudentService homeworkStudentService;
    @Autowired
    private CourseHomeworkSubmitService submitService;

    @Autowired
    private MsgService msgService;

    @Autowired
    private AllsService allsService;

    /* 教师 */
    @ApiOperation("查询某实验节点的作业基本信息")
    @GetMapping("/get-hmwk-id/{nodeId}/{studentId}")
    public R getHomeworkIdByNodeId(@PathVariable String nodeId, @PathVariable String studentId){
            CourseHomework homework = homeworkService.getByNodeId(nodeId);
            return R.ok().data("homework",homework);
    }

    @ApiOperation("保存作业信息，需要提交作业基本信息与每一小题的题目内容，不包含附件，发送作业通知")
    @PostMapping("/save")
    public R saveHomework(@ApiParam("作业基本信息，如果是实验任务需要填入node_id") CourseHomework homework,
                          @ApiParam("作业内容信息") @RequestBody List<CourseHomeworkContext> contexts) {
        String id = homeworkService.saveHomework(homework, contexts);
        if (id != null) {
            // 发送作业通知
            Teacher teacher = teacherMapper.selectById(homework.getTeacherId());
            if (teacher == null){
                throw new CloudException("没有查询到该id的老师",20001);
            }
            MessageVo messageVo = new MessageVo();
            messageVo.setCourseId(homework.getCourseId());
            messageVo.setTeacherUserId(teacher.getUserId());
            messageVo.setTitle("作业通知");
            String content = "老师发布了一则新的作业:"+homework.getName();
            messageVo.setContent(content);
            msgService.sendMessage(messageVo);
            return R.ok().data("homeworkId", id).message("保存成功，作业通知已发送");
        }
        return R.error().message("保存失败");
    }


    @ApiOperation("根据教师id获取该教师所教的全部课程的全部作业列表")
    @GetMapping("/list-teacher/{teacherId}")
    public R listByTeacherId(@ApiParam(value = "教师id", required = true) @PathVariable String teacherId) {
        List<TeacherHomeworkVO> homeworks = homeworkService.listTeacherHomework(teacherId);
        return R.ok().data("homeworks", homeworks);
    }

    @ApiOperation("根据作业id获取作业完成的学生信息列表，只能查询提交了的学生，未提交查询不到")
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

    @ApiOperation("批改学生提交的作业，上传得分和评语，需要指定作业和学生的id")
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

    @ApiOperation("提交附件，返回文件信息")
    @PostMapping("/submit/file")
    public R uploadFile(@ApiParam("上传附件，可选") @RequestParam(value = "file",required = false) @RequestPart MultipartFile file){
        if(file != null) {
            String memid = "1";
            String catalogue = "/root/homework";
            R upload = allsService.upload(file, catalogue, memid);
            Map<String, Object> data = upload.getData();
            File file1 = JSON.parseObject(JSON.toJSONString(data.get("userInfo")), File.class);
            R r = allsService.addFile(file1);
            file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")), File.class);
            return R.ok().data("file",file1);
        }else {
            return R.error().message("文件为空");
        }
    }

    @ApiOperation("学生提交作业的文本内容，不包含附件")
    @PostMapping("/submit/{studentId}/{homeworkId}")
    public R submit(@ApiParam(value = "学生id", required = true) @PathVariable String studentId,
                    @ApiParam(value = "作业id", required = true) @PathVariable String homeworkId,
                    @ApiParam("每个小题的内容,上传附件前请先调用提交附件接口得到file_id") @RequestBody List<CourseHomeworkSubmit> submits
                    ) {
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

