package xyz.likailing.cloud.service.manager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableGetVO;
import xyz.likailing.cloud.service.manager.feign.AllsService;
import xyz.likailing.cloud.service.manager.mapper.TimetableMapper;
import xyz.likailing.cloud.service.manager.service.TimetableService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
@Slf4j
@RestController
@RequestMapping("/admin/manager/timetable")
public class TimetableController {

    @Autowired
    AllsService allsService;

    @Autowired
    private TimetableService timetableService;
    @Autowired
    private TimetableMapper timetableMapper;

    @ApiOperation("根据课程id获取时序信息")
    @GetMapping("/course-time/{courseId}")
    public R getTime(@ApiParam(value = "课程id", required = true) @PathVariable String courseId) {
        List<TimetableGetVO> courseTime = timetableService.listCourseTime(courseId);
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

    @Scheduled(cron = "0 0 0 * * * ?") //每天0点执行一次
    public void expire() {
        Date today = new Date();
        List<Timetable> expiredList = timetableMapper.selectExpiredTimetable(today);
        List<Timetable> todayList = timetableMapper.selectTodayTimetable(today);
        if (expiredList.isEmpty()) {
            log.info("没有需要更新的信息");
            return;
        }
        //遍历更新过期时序
        for (Timetable timetable : expiredList) {
            timetable.setStatus(2);
            boolean update = timetableService.updateById(timetable);
            if (update) {
                log.info("{} : 更新成功，已过期", timetable.getId());
            } else {
                log.info("{} : 更新失败", timetable.getId());
            }
        }
        for (Timetable timetable : todayList) {
            timetable.setStatus(1);
            boolean update = timetableService.updateById(timetable);
            if (update) {
                log.info("{} : 更新成功", timetable.getId());
            } else {
                log.info("{} : 更新失败", timetable.getId());
            }
        }
    }


}

