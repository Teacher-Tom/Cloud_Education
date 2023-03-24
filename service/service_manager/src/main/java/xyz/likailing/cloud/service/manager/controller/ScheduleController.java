package xyz.likailing.cloud.service.manager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableVO;
import xyz.likailing.cloud.service.manager.service.TimetableService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

//    @ApiOperation("添加临时时序信息列表")
//    @GetMapping("/temp-list/{userId}")
//    public R scheduleTemp(@ApiParam("当前操作的用户id") @PathVariable String userId,
//                          @ApiParam(value = "排课的批量时序信息", required = true) TimetableVO timetableVO) {
//        Set<Timetable> tempList = timetableService.getTempList(userId, timetableVO);
//        return R.ok().data("tempList", tempList);
//    }
//
//    @ApiOperation("存储临时时序信息")
//    @PostMapping("/save-list/{userId}")
//    public R scheduleSave(@ApiParam("当前操作的用户id") @PathVariable String userId) {
//        boolean save = timetableService.saveUserTempList(userId);
//        if(save) {
//            return R.ok().message("保存成功");
//        }
//        return R.error().message("保存失败");
//    }
//
//    @ApiOperation("删除一个临时时序信息")
//    @DeleteMapping("/remove-one/{userId}")
//    public R scheduleRemove(@ApiParam("当前操作的用户id") @PathVariable String userId,
//                            @ApiParam(value = "时序对象", required = true) @RequestBody Timetable timetable) {
//        boolean remove = timetableService.removeTempElement(userId, timetable);
//        if(remove) {
//            return R.ok().message("删除成功");
//        }
//        return R.error().message("数据不存在");
//    }

    @ApiOperation("获取批量添加信息对应的临时时序信息列表，多次添加时不会返回之前已经返回过的信息，只有这一次对应的列表")
    @GetMapping("/temp-list")
    public R scheduleTemp(@ApiParam(value = "排课的批量时序信息", required = true) TimetableVO timetableVO) {
        List<Timetable> tempList = new ArrayList<>();
        Integer beginWeek = timetableVO.getBeginWeek();
        Integer endWeek = timetableVO.getEndWeek();
        for (int i = beginWeek; i <= endWeek; i++) {
            Timetable timetable = new Timetable();
            BeanUtils.copyProperties(timetableVO, timetable);
            timetable.setWeek(i);
            tempList.add(timetable);
        }
        return R.ok().data("tempList", tempList);
    }

    @ApiOperation("存储临时时序信息，将多次返回的所有临时列表（前端已经单个增删过）的内容合并为一个列表，一起传入")
    @PostMapping("/save-list")
    public R scheduleSave(@ApiParam("多次提交的所有时序信息") @RequestBody List<Timetable> allList) {
        boolean save = timetableService.saveTempList(allList);
        if(save) {
            return R.ok().message("保存成功");
        }
        return R.error().message("保存失败");
    }

}
