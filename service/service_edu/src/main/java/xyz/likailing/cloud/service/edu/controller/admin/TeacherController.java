package xyz.likailing.cloud.service.edu.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.edu.entity.Teacher;
import xyz.likailing.cloud.service.edu.entity.vo.TeacherQueryVo;
import xyz.likailing.cloud.service.edu.feign.OssFileService;
import xyz.likailing.cloud.service.edu.service.TeacherService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author likailing
 * @since 2023-01-31
 */
@CrossOrigin
@Api(description = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
@Slf4j
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private OssFileService ossFileService;

    @GetMapping("list")
    @ApiOperation("获取所有老师")
    public R listAll(){
        List<Teacher> list = teacherService.list();
        return R.ok().data("items",list);
    }
    @DeleteMapping("remove/{id}")
    @ApiOperation("根据id删除老师")
    public R removeById(@PathVariable  String id){
        //删除讲师头像
        boolean b = teacherService.removeAvatarById(id);
        //删除讲师
        boolean remove = teacherService.removeById(id);
        if(remove){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("数据不存在");
        }
    }

    @DeleteMapping("batch-remove/{id}")
    @ApiOperation("根据id列表删除老师")
    public R removeRows(@RequestBody  List<String> idList){
        boolean remove = teacherService.removeByIds(idList);
        if(remove){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("数据不存在");
        }
    }

    @GetMapping("list/{page}/{limit}")
    @ApiOperation("老师分页列表")
    public R listPage(@ApiParam(required = true) Long page, @ApiParam(required = true) Long limit, TeacherQueryVo teacherQueryVo){
        Page<Teacher> pageParam = new Page<>(page, limit);
        IPage<Teacher> pageModel = teacherService.selectPage(pageParam, teacherQueryVo);
        List<Teacher> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    @ApiOperation("新增讲师")
    @PostMapping("save")
    public R save(@ApiParam("讲师对象") @RequestBody Teacher teacher){
        teacherService.save(teacher);
        return R.ok().message("保存成功");
    }

    @ApiOperation("更新讲师")
    @PutMapping("update")
    public R updateById(@ApiParam("讲师对象") @RequestBody Teacher teacher){
        boolean result = teacherService.updateById(teacher);
        if(result){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id获取讲师信息")
    @GetMapping("get/{id}")
    public R getById(@ApiParam("讲师对象") @PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        if(teacher != null){
            return R.ok().data("item", teacher);
        }else{
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据关键字获取讲师姓名")
    @GetMapping("list/name/{key}")
    public R selectNameListByKey(@ApiParam(value = "关键字",required = true) @PathVariable String key){
        List<Map<String,Object>> list = teacherService.selectNameList(key);

        return R.ok().data("nameList", list);

    }
    @ApiOperation(value = "测试服务调用")
    @GetMapping("test")
    public R test(){
        ossFileService.test();
        log.info("edu执行成功");
        return R.ok();
    }

    @ApiOperation("测试并发")
    @GetMapping("test_concurrent")
    public R testConcurrent(){
        log.info("test_concurrent");
        return R.ok();
    }

    @GetMapping("/messege1")
    public String message1(){
        return "messege1";
    }

    @GetMapping("/messege2")
    public String message2(){
        return "messege2";
    }


}

