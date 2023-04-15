package xyz.likailing.cloud.service.manager.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.ChapterTimetable;
import xyz.likailing.cloud.service.manager.entity.SubChapter;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableGetVO;
import xyz.likailing.cloud.service.manager.feign.AllsService;
import xyz.likailing.cloud.service.manager.mapper.TimetableMapper;
import xyz.likailing.cloud.service.manager.service.ChapterTimetableService;
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
@Slf4j
@RestController
@RequestMapping("/admin/manager/timetable")
@CrossOrigin
public class TimetableController {

    @Autowired
    AllsService allsService;

    @Autowired
    private TimetableService timetableService;
    @Autowired
    private ChapterTimetableService chapterTimetableService;
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

    @ApiOperation("为时序添加对应的二级章节")
    @PostMapping("/add-chapter/{id}")
    public R addChapter(@ApiParam("小节id") @PathVariable String id, @ApiParam("二级章节id") @RequestBody List<String> subs) {
        Timetable timetable = timetableService.getById(id);
        if(ObjectUtils.isEmpty(timetable)) {
            return R.error().message("该小节不存在");
        }
        boolean add = timetableService.addChapter(id, subs);
        if(add) {
            return R.ok().message("添加成功");
        }
        return R.error().message("添加失败");
    }

    @ApiOperation("根据id获取时序对应的二级章节")
    @GetMapping("/get-chapter/{id}")
    public R getChapter(@ApiParam("小节id") @PathVariable String id) {
        List<SubChapter> subs = timetableService.getChapter(id);
        return R.ok().data("subchapters", subs);
    }

    @ApiOperation("为时序删除对应的二级章节")
    @DeleteMapping("/delete-chapter/{id}/{subId}")
    public R deleteChapter(@ApiParam("小节id") @PathVariable String id, @ApiParam("二级章节id") @PathVariable String subId) {
        Timetable timetable = timetableService.getById(id);
        if(ObjectUtils.isEmpty(timetable)) {
            return R.error().message("该小节不存在");
        }
        QueryWrapper<ChapterTimetable> wrapper = new QueryWrapper<>();
        wrapper.eq("timetable_id", id).eq("sub_chapter_id", subId);
        boolean remove = chapterTimetableService.remove(wrapper);
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    @ApiOperation("根据id上传小节资源")
    @PostMapping("/upload/{courseId}/{timetableId}")
    public R uploadById(MultipartFile file, @PathVariable String courseId, @PathVariable String timetableId){
        // memId即课程id
        String catalogue = "/root/timetable";
        R r = allsService.upload(file, catalogue, courseId);
        return r;
    }

    @Scheduled(cron = "0 1 0 * * ?") //每天 0点 1分执行一次
    public void expire() {
        //更新过期的时序
        int updateExpiredTimetable = timetableMapper.updateExpiredTimetable();
        if(updateExpiredTimetable >= 1) {
            log.info("更新过期时序成功");
        } else {
            log.info("没有过期更新");
        }

        //更新当天的时序
        int updateTodayTimetable = timetableMapper.updateTodayTimetable();
        if(updateTodayTimetable >= 1) {
            log.info("更新当天时序成功");
        } else {
            log.info("没有当天更新");
        }

//        Date now = new Date();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(now);
//        calendar.set(Calendar.MILLISECOND, 0);
//        calendar.set(Calendar.SECOND, 0);
//        Date today = calendar.getTime();
//        List<Timetable> expiredList = timetableMapper.selectExpiredTimetable(today);
//        if (expiredList.isEmpty()) {
//            log.info("没有需要更新的信息");
//            return;
//        }
//        //遍历更新过期时序
//        for (Timetable timetable : expiredList) {
//            timetable.setStatus(2);
//            boolean update = timetableService.updateById(timetable);
//            if (update) {
//                log.info("{} : 更新成功，已过期", timetable.getId());
//            } else {
//                log.info("{} : 更新失败", timetable.getId());
//            }
//        }
//        List<Timetable> todayList = timetableMapper.selectTodayTimetable();
//        for (Timetable timetable : todayList) {
//            timetable.setStatus(1);
//            boolean update = timetableService.updateById(timetable);
//            if (update) {
//                log.info("{} : 更新成功", timetable.getId());
//            } else {
//                log.info("{} : 更新失败", timetable.getId());
//            }
//        }
    }


}

