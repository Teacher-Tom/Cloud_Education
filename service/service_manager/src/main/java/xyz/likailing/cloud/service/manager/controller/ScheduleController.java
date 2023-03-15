package xyz.likailing.cloud.service.manager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableVO;
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
@RequestMapping("/admin/manager/schedule")
public class ScheduleController {

    @Autowired
    private TimetableService timetableService;

    @ApiOperation("获取临时时序信息列表")
    @PostMapping("/temp-list")
    public R scheduleTemp(@ApiParam(value = "排课信息", required = true) TimetableVO timetableVO) {
        List<Timetable> tempList = timetableService.tempList(timetableVO);
        return R.ok().data("tempList", tempList);
    }

    @ApiOperation("存储课程时序信息")
    @GetMapping("/save-list")
    public R scheduleSave() {
        boolean save = timetableService.saveTempList();
        if(save) {
            return R.ok().message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("删除课程时序信息")
    @GetMapping("/remove-element")
    public R scheduleRemove(@ApiParam(value = "时序对象", required = true) Timetable timetable) {
        boolean remove = timetableService.removeTempElement(timetable);
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }

}
