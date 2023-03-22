package xyz.likailing.cloud.service.manager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @ApiOperation("添加临时时序信息列表")
    @PostMapping("/temp-list/{userId}")
    public R scheduleTemp(@ApiParam("当前操作的用户id") @PathVariable String userId,
                          @ApiParam(value = "排课的批量时序信息", required = true) TimetableVO timetableVO) {
        List<Timetable> tempList = timetableService.tempList(userId, timetableVO);
        return R.ok().data("tempList", tempList);
    }

    @ApiOperation("存储临时时序信息")
    @GetMapping("/save-list/{userId}")
    public R scheduleSave(@ApiParam("当前操作的用户id") @PathVariable String userId) {
        boolean save = timetableService.saveTempList(userId);
        if(save) {
            return R.ok().message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("删除一个临时时序信息")
    @GetMapping("/remove-one/{userId}")
    public R scheduleRemove(@ApiParam("当前操作的用户id") @PathVariable String userId,
                            @ApiParam(value = "时序对象", required = true) @RequestBody Timetable timetable) {
        boolean remove = timetableService.removeTempElement(userId, timetable);
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

}
