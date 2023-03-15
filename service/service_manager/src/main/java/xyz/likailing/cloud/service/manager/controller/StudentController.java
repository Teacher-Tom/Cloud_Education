package xyz.likailing.cloud.service.manager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Student;
import xyz.likailing.cloud.service.manager.entity.vo.StudentVO;
import xyz.likailing.cloud.service.manager.service.StudentService;

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
@RequestMapping("/admin/manager/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("获取全部学生列表")
    @GetMapping("/list")
    public R listAll() {
        List<StudentVO> students = studentService.listStudents();
        return R.ok().data("students", students);
    }

    @ApiOperation("获取班级全部学生列表")
    @GetMapping("/list-class/{classId}")
    public R listClass(@ApiParam("班级id") @PathVariable String classId) {
        List<Student> students = studentService.listClassStudents(classId);
        return R.ok().data("students", students);
    }

    @ApiOperation("新增学生用户信息")
    @PostMapping("/save")
    public R save(@ApiParam(value = "学生用户对象", required = true) @RequestBody Student student) {
        boolean save = studentService.save(student);
        if(save) {
            return R.ok().data("studentId", student.getId()).message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("更新学生用户信息")
    @PutMapping("/update")
    public R updateById(@ApiParam("学生用户对象") @RequestBody Student student) {
        boolean update = studentService.updateById(student);
        if(update) {
            return R.ok().message("更新成功");
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据学生id查询用户信息")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam(value = "学生id", required = true) @PathVariable String id) {
        Student student = studentService.getById(id);
        if(!ObjectUtils.isEmpty(student)) {
            return R.ok().data("student", student);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据学生id删除用户信息")
    @DeleteMapping("/remove/{id}")
    public R removeById(@ApiParam(value = "学生id", required = true) @PathVariable String id) {
        boolean remove = studentService.removeById(id);
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

//    @ApiOperation("学生分页列表")
//    @GetMapping("/list/{page}/{limit}")
//    public R listPage(@ApiParam(value = "页号", required = true) @PathVariable Long page,
//                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
//                      @ApiParam("查询对象") StudentQueryVO studentQueryVo) {
//        Page<Student> pageParam = new Page<>(page, limit);
//        IPage<Student> pageModel = studentService.selectPage(pageParam, studentQueryVo);
//        List<Student> records = pageModel.getRecords();
//        long total = pageModel.getTotal();
//        return R.ok().data("total", total).data("rows", records);
//    }

}

