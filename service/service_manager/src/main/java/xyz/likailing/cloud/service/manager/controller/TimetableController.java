package xyz.likailing.cloud.service.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.feign.AllsService;
import xyz.likailing.cloud.service.manager.service.TimetableService;

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
@RequestMapping("/admin/manager/timetable")
public class TimetableController {

    @Autowired
    private TimetableService timetableService;

    @Autowired
    private AllsService allsService;

    @ApiOperation("根据课程id获取时序信息")
    @GetMapping("/course-time/{courseId}")
    public R getTime(@ApiParam(value = "课程id", required = true) @PathVariable String courseId) {
        List<Timetable> courseTime = timetableService.listCourseTime(courseId);
        if(!ObjectUtils.isEmpty(courseTime)) {
            return R.ok().data("time", courseTime);
        }
        return R.error().message("课程不存在");
    }

    @ApiOperation("新增时序信息")
    @PostMapping("/save")
    public R save(@ApiParam(value = "课程时序信息", required = true) @RequestBody Timetable timetable) {
        boolean save = timetableService.save(timetable);
        if(save) {
            R.ok().data("timetableId", timetable.getId()).message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("更新时序信息")
    @PutMapping("/update")
    public R updateById(@ApiParam("课程信息") @RequestBody Timetable timetable) {
        boolean update = timetableService.updateById(timetable);
        if(update) {
            return R.ok().message("更新成功");
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id查询时序信息")
    @GetMapping("/get/{id}")
    public R getById(@ApiParam(value = "时序id", required = true) @PathVariable String id) {
        Timetable timetable = timetableService.getById(id);
        if(!ObjectUtils.isEmpty(timetable)) {
            return R.ok().data("timetable", timetable);
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id删除时序信息")
    @DeleteMapping("/remove/{id}")
    public R removeById(@ApiParam(value = "时序id", required = true) @PathVariable String id) {
        boolean remove = timetableService.removeById(id);
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id上传小节资源")
    @PostMapping("/upload/{courseId}/{timetableId}")
    public R uploadById(MultipartFile file, @PathVariable String courseId, @PathVariable String timetableId){
        // memId即课程id
        String catalogue = "/root/timetable";
        R r = allsService.upload(file, catalogue, courseId);
        return r;
    }



}

