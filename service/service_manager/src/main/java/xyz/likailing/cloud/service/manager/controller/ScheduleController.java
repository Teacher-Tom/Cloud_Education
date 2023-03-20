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

    private List<Timetable> tempList;

    @Autowired
    private TimetableService timetableService;

    @ApiOperation("添加临时时序信息列表")
    @PostMapping("/temp-list")
    public R scheduleTemp(@ApiParam(value = "排课的批量时序信息", required = true) TimetableVO timetableVO) {
        tempList = timetableService.tempList(timetableVO);
        return R.ok().data("tempList", tempList);
    }

    @ApiOperation("存储临时时序信息")
    @GetMapping("/save-list")
    public R scheduleSave() {
        boolean save = timetableService.saveTempList(tempList);
        if(save) {
            return R.ok().message("保存成功");
        }
        return R.error().message("保存失败");
    }

    @ApiOperation("删除一个临时时序信息")
    @GetMapping("/remove-one")
    public R scheduleRemove(@ApiParam(value = "时序对象", required = true) Timetable timetable) {
        if(tempList.contains(timetable)) {
            tempList.remove(timetable);
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

}
