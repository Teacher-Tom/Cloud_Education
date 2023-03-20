package xyz.likailing.cloud.service.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.CourseHomework;
import xyz.likailing.cloud.service.manager.entity.CourseHomeworkSubmit;
import xyz.likailing.cloud.service.manager.entity.vo.HomeworkVO;
import xyz.likailing.cloud.service.manager.service.CourseHomeworkService;
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
    private CourseHomeworkSubmitService submitService;

    @ApiOperation("保存作业信息")
    @PostMapping("/save")
    public R saveHomework(@ApiParam("作业信息") HomeworkVO homeworkVO) {
        String id = homeworkService.saveHomework(homeworkVO);
        if(id != null) {
            return R.ok().data("homeworkId", id).message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("根据教师id获取作业列表")
    @GetMapping("/list-teacher")
    public R listByTeacherId(@ApiParam(value = "教师id", required = true) String teacherId) {
        List<CourseHomework> homeworks = homeworkService.listTeacherHomework(teacherId);
        return R.ok().data("homeworks", homeworks);
    }

    @ApiOperation("根据id获取作业详细信息")
    @GetMapping("/get")
    public R getHomework(@ApiParam(value = "作业id", required = true) String id) {
        HomeworkVO homework = homeworkService.getHomework(id);
        QueryWrapper<CourseHomeworkSubmit> wrapper = new QueryWrapper<>();
        wrapper.eq("homework_id", id);
        List<CourseHomeworkSubmit> submits = submitService.list(wrapper);
        HashSet<String> students = new HashSet<>();
        for (CourseHomeworkSubmit submit : submits) {
            String studentId = submit.getStudentId();
            students.add(studentId);
        }
        if(!ObjectUtils.isEmpty(homework)) {
            return R.ok().data("homework", homework).data("studentNumber", students.size()).data("submits", submits);
        }
        return R.error().message("数据不存在");
    }

}

