package xyz.likailing.cloud.service.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Course;
import xyz.likailing.cloud.service.manager.entity.CourseDescription;
import xyz.likailing.cloud.service.manager.entity.vo.*;
import xyz.likailing.cloud.service.manager.feign.AllsService;
import xyz.likailing.cloud.service.manager.service.CourseDescriptionService;
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
public class CourseController {

    @Autowired
    AllsService allsService;

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseDescriptionService descriptionService;

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
        List<CourseVO> courses = courseService.listAll();
        return R.ok().data("courses", courses);
    }

    @ApiOperation("管理员获取详细课程信息分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(@ApiParam(value = "页号", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                      @ApiParam("查询对象") AdminCourseQueryVO adminCourseQueryVO) {
        IPage<CourseVO> courses = courseService.listPage(page, limit, adminCourseQueryVO);
        List<CourseVO> records = courses.getRecords();
        long total = courses.getTotal();
        return R.ok().data("rows", records).data("total", total);
    }

    @ApiOperation("新增课程信息，包含教师、班级信息")
    @PostMapping("/save")
    public R save(@ApiParam(value = "课程信息", required = true) @RequestBody CourseSaveVO courseSaveVO) {
        Course course = courseSaveVO.getCourse();
        boolean save = courseService.saveCourse(course, courseSaveVO.getTeacherIds(), courseSaveVO.getClassIds());
        if(save) {
            allsService.createCourseAccount(course.getId(),course.getName(), course.getCoverUrl());
            return R.ok().data("courseId", course.getId()).message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("更新课程信息")
    @PutMapping("/update")
    public R updateById(@ApiParam("课程信息") @RequestBody CourseSaveVO courseSaveVO) {
        Course course = courseSaveVO.getCourse();
        boolean update = courseService.updateCourse(course, courseSaveVO.getTeacherIds(), courseSaveVO.getClassIds());
        if(update) {
            return R.ok().message("更新成功");
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id查询课程信息")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam(value = "课程id", required = true) @PathVariable String id) {
        CourseVO course = courseService.getCourseById(id);
        if(!ObjectUtils.isEmpty(course)) {
            return R.ok().data("course", course);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据课程id查询课程描述")
    @GetMapping("/get-desc/{id}")
    public R getDescriptionById(@ApiParam(value = "课程id", required = true) @PathVariable String id) {
        QueryWrapper<CourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        CourseDescription description = descriptionService.getOne(wrapper);
        if(!ObjectUtils.isEmpty(description)) {
            return R.ok().data("description", description);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("新增课程描述信息")
    @PostMapping("/add-desc")
    public R addDesc(@ApiParam(value = "课程描述", required = true) @RequestBody CourseDescription courseDescription) {
        QueryWrapper<CourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseDescription.getCourseId());
        CourseDescription description = descriptionService.getOne(wrapper);
        //该课程描述已存在，进行覆盖
        if(!ObjectUtils.isEmpty(description)) {
            description.setDescription(courseDescription.getDescription());
            boolean update = descriptionService.updateById(description);
            if(update) {
                return R.ok().data("descId", description.getId()).message("该课程描述已存在，成功覆盖");
            }
            return R.error().message("该课程描述已存在，覆盖失败");
        }
        boolean save = descriptionService.save(courseDescription);
        if(save) {
            return R.ok().data("descId", courseDescription.getId()).message("保存成功");
        }
        return R.error().message("保存失败");
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

