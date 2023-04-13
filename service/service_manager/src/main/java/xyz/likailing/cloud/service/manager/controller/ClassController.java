package xyz.likailing.cloud.service.manager.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Class;
import xyz.likailing.cloud.service.manager.service.ClassService;

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
@RequestMapping("/admin/manager/class")
@CrossOrigin
public class ClassController {

    @Autowired
    private ClassService classService;

    @ApiOperation("获取班级列表")
    @GetMapping("/list")
    public R listAll() {
        List<Class> classes = classService.list();
        return R.ok().data("classes", classes);
    }

    @ApiOperation("新增班级信息")
    @PostMapping("/save")
    public R save(@ApiParam(value = "班级信息", required = true) @RequestBody Class cl) {
        boolean save = classService.save(cl);
        if(save) {
            return R.ok().data("classId", cl.getId()).message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("更新班级信息")
    @PutMapping("/update")
    public R updateById(@ApiParam("班级信息") @RequestBody Class cl) {
        boolean update = classService.updateById(cl);
        if(update) {
            return R.ok().message("更新成功");
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id查询班级信息")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam(value = "班级id", required = true) @PathVariable String id) {
        Class cl = classService.getById(id);
        if(!ObjectUtils.isEmpty(cl)) {
            return R.ok().data("class", cl);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id删除班级信息")
    @DeleteMapping("/remove/{id}")
    public R removeById(@ApiParam(value = "班级id", required = true) @PathVariable String id) {
        boolean remove = classService.removeClassById(id);
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

}

