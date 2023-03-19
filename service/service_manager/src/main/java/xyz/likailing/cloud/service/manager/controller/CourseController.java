package xyz.likailing.cloud.service.manager.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Course;
import xyz.likailing.cloud.service.manager.entity.vo.AdminCourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.AdminCourseVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseQueryVO;
import xyz.likailing.cloud.service.manager.entity.vo.CourseVO;
import xyz.likailing.cloud.service.manager.service.CourseService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
@RestController
@RequestMapping("/admin/manager/course")
@PreAuthorize("hasAuthority('auth_course')")
@CrossOrigin
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ApiOperation("获取对应学生的学年学期的全部课程列表")
    @GetMapping("/list-student")
    public R listStudent(@ApiParam("查询对象") CourseQueryVO CourseQueryVO) {
        List<CourseVO> courses = courseService.listYearTermStudent(CourseQueryVO);
        return R.ok().data("courses", courses);
    }

    @ApiOperation("获取对应教师的学年学期的全部课程列表")
    @GetMapping("/list-teacher")
    public R listTeacher(@ApiParam("查询对象") CourseQueryVO CourseQueryVO) {
        List<CourseVO> courses = courseService.listYearTermTeacher(CourseQueryVO);
        return R.ok().data("courses", courses);
    }

    @ApiOperation("管理员获取详细课程信息列表")
    @GetMapping("/list")
    public R listAll() {
        List<AdminCourseVO> courses = courseService.listAll();
        return R.ok().data("courses", courses);
    }

    @ApiOperation("管理员获取详细课程信息分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(@ApiParam(value = "页号", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                      @ApiParam("查询对象") AdminCourseQueryVO adminCourseQueryVO) {
        IPage<AdminCourseVO> courses = courseService.listPage(page, limit, adminCourseQueryVO);
        List<AdminCourseVO> records = courses.getRecords();
        long total = courses.getTotal();
        return R.ok().data("rows", records).data("total", total);
    }

    @ApiOperation("新增课程信息，包含教师、班级信息")
    @PostMapping("/save")
    public R save(@ApiParam(value = "课程信息", required = true) @RequestBody Course course,
                  @ApiParam(value = "教师id", required = true) String teacherId,
                  @ApiParam(value = "班级id", required = true) String classId) {
        boolean save = courseService.saveCourse(course, teacherId, classId);
        if(save) {
            R.ok().data("courseId", course.getId()).message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("更新课程信息")
    @PutMapping("/update")
    public R updateById(@ApiParam("课程信息") @RequestBody Course course) {
        boolean update = courseService.updateById(course);
        if(update) {
            return R.ok().message("更新成功");
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id查询课程信息")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam(value = "课程id", required = true) @PathVariable String id) {
        Course course = courseService.getById(id);
        if(!ObjectUtils.isEmpty(course)) {
            return R.ok().data("course", course);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id删除课程信息")
    @DeleteMapping("/remove/{id}")
    public R removeById(@ApiParam(value = "课程id", required = true) @PathVariable String id) {
        boolean remove = courseService.removeCourseById(id); //自定义删除，删除课程信息的同时，相关联的表中信息也要删除
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

}

