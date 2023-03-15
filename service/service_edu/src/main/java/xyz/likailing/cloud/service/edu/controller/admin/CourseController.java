package xyz.likailing.cloud.service.edu.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.edu.entity.Teacher;
import xyz.likailing.cloud.service.edu.entity.form.CourseInfoForm;
import xyz.likailing.cloud.service.edu.entity.vo.CourseQueryVo;
import xyz.likailing.cloud.service.edu.entity.vo.CourseVo;
import xyz.likailing.cloud.service.edu.entity.vo.TeacherQueryVo;
import xyz.likailing.cloud.service.edu.service.CourseService;

import java.util.List;

/**
 * @Author 12042
 * @create 2023/3/10 18:32
 */
@CrossOrigin
@Api(description = "课程管理")
@RestController
@RequestMapping("/admin/edu/course")
@Slf4j
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation("新增课程")
    @PostMapping("save-course-info")
    @ApiImplicitParam(name = "courseInfoForm")
    public R saveCourseInfo(@RequestBody  CourseInfoForm courseInfoForm){
        String courseId = courseService.saveCourseInfo(courseInfoForm);
        return R.ok().data("courseId",courseId).message("保存成功");
    }

    @ApiOperation("根据ID查询课程")
    @GetMapping("course-info/{id}")
    public R getById(
            @ApiParam(value = "课程id",required = true)
            @PathVariable String id
    ){
        CourseInfoForm courseInfoForm = courseService.getCourseInfoById(id);
        if(courseInfoForm != null){
            return R.ok().data("item",courseInfoForm);
        }else {
            return R.ok().message("数据不存在");
        }
    }

    @GetMapping("list/{page}/{limit}")
    @ApiOperation("课程分页列表")
    public R listPage(@ApiParam(required = true) Long page, @ApiParam(required = true) Long limit, CourseQueryVo courseQueryVo){
        IPage<CourseVo> pageModel = courseService.selectPage(page, limit, courseQueryVo);
        List<CourseVo> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("更新课程")
    @PostMapping("update-course-info")
    @ApiImplicitParam(name = "courseInfoForm")
    public R updateCourseInfo(@RequestBody  CourseInfoForm courseInfoForm){
        courseService.updateCourseInfoById(courseInfoForm);
        return R.ok().message("更新成功");
    }



}
