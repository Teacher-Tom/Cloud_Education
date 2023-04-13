package xyz.likailing.cloud.service.manager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Teacher;
import xyz.likailing.cloud.service.manager.service.TeacherService;

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
@RequestMapping("/admin/manager/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("获取全部教师列表")
    @GetMapping("/list")
    public R listAll() {
        List<Teacher> teachers = teacherService.list();
        return R.ok().data("teachers", teachers);
    }

    @ApiOperation("新增教师用户信息")
    @PostMapping("/save")
    public R save(@ApiParam(value = "教师用户对象", required = true) @RequestBody Teacher teacher) {
        boolean save = teacherService.save(teacher);
        if(save) {
            return R.ok().data("teacherId", teacher.getId()).message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("更新教师用户信息")
    @PutMapping("/update")
    public R updateById(@ApiParam("教师用户对象") @RequestBody Teacher teacher) {
        boolean update = teacherService.updateById(teacher);
        if(update) {
            return R.ok().message("更新成功");
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据教师id查询用户信息")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam(value = "教师id", required = true) @PathVariable String id) {
        Teacher teacher = teacherService.getById(id);
        if(!ObjectUtils.isEmpty(teacher)) {
            return R.ok().data("teacher", teacher);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据教师id删除用户信息")
    @DeleteMapping("/remove/{id}")
    public R removeById(@ApiParam(value = "教师id", required = true) @PathVariable String id) {
        boolean remove = teacherService.removeTeacherById(id); //需要同时删除教师与课程的关联信息
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

//    @ApiOperation("教师分页列表")
//    @GetMapping("/list/{page}/{limit}")
//    public R listPage(@ApiParam(value = "页号", required = true) @PathVariable Long page,
//                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
//                      @ApiParam("查询对象") TeacherQueryVO teacherQueryVo) {
//        Page<Teacher> pageParam = new Page<>(page, limit);
//        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);
//        List<Teacher> records = pageModel.getRecords();
//        long total = pageModel.getTotal();
//        return R.ok().data("total", total).data("rows", records);
//    }

}

