package xyz.likailing.cloud.service.manager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Chapter;
import xyz.likailing.cloud.service.manager.entity.SubChapter;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.entity.vo.ChapterVO;
import xyz.likailing.cloud.service.manager.entity.vo.ChildChapter;
import xyz.likailing.cloud.service.manager.service.ChapterService;
import xyz.likailing.cloud.service.manager.service.SubChapterService;
import xyz.likailing.cloud.service.manager.service.TimetableService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author derek
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/manager/chapter")
@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;
    @Autowired
    private SubChapterService subChapterService;
    @Autowired
    private TimetableService timetableService;

    @ApiOperation("根据课程id获取课程大纲")
    @GetMapping("/get/{courseId}")
    public R getCourseChapter(@ApiParam("课程id") @PathVariable String courseId) {
        List<ChapterVO> chapters = chapterService.getCourseChapter(courseId);
        for (ChapterVO chapterVO : chapters) {
            String chapterId = chapterVO.getChapter().getId();
            List<ChildChapter> children = new ArrayList<>();
            //查询子章节信息
            QueryWrapper<SubChapter> subChapterQueryWrapper = new QueryWrapper<>();
            subChapterQueryWrapper.eq("chapter_id", chapterId).eq("course_id", courseId);
            List<SubChapter> subChapters = subChapterService.list(subChapterQueryWrapper);
            for (SubChapter subChapter : subChapters) {
                ChildChapter childChapter = new ChildChapter();
                childChapter.setSubChapter(subChapter);
                List<Timetable> timetables = timetableService.getSubTimetables(subChapter.getId());
                childChapter.setTimetables(timetables);
                children.add(childChapter);
            }
            chapterVO.setChildren(children);
        }
        return R.ok().data("chapters", chapters);
    }

    @ApiOperation("新建大纲，传入完整大纲，需要指定课程id，每个对应小节只需要传入id")
    @PostMapping("/save/{courseId}")
    public R save(@ApiParam("课程id") @PathVariable String courseId,
                  @ApiParam("大纲信息") @RequestBody List<ChapterVO> chapterSaveVOS) {
        //先删除该课程原有大纲
        chapterService.removeCourseChapter(courseId);
        //保存大纲信息
        Map<String, String> chapterIds = new HashMap<>();
        for (ChapterVO chapterSaveVO : chapterSaveVOS) {
            //章节信息
            Chapter chapter = chapterSaveVO.getChapter();
            if(!ObjectUtils.isEmpty(chapter)) {
                chapter.setCourseId(courseId);
                boolean save = chapterService.save(chapter);
                if(save) {
                    String id = chapter.getId();
                    List<ChildChapter> children = chapterSaveVO.getChildren();
                    for (ChildChapter childChapter : children) {
                        //子章节信息
                        subChapterService.saveChildChapter(childChapter, courseId, id);
                    }
                    chapterIds.put(chapter.getName(), chapter.getId());
                }
            }
        }
        if(!chapterIds.isEmpty()) {
            return R.ok().data("chapterIds", chapterIds).message("保存成功");
        }
        return R.error().message("关键数据缺少，保存失败");
    }

    @ApiOperation("根据id更新章节名")
    @PutMapping("/update-chapter/{id}/{name}")
    public R updateChapter(@ApiParam("章节id") @PathVariable String id, @ApiParam("章节名") @PathVariable String name) {
        Chapter chapter = chapterService.getById(id);
        if(ObjectUtils.isEmpty(chapter)) {
            return R.error().message("章节不存在");
        }
        chapter.setName(name);
        boolean update = chapterService.updateById(chapter);
        if(update) {
            return R.ok().message("更新成功");
        }
        return R.error().message("更新失败");
    }

    @ApiOperation("根据id更新二级章节名")
    @PutMapping("/update-sub/{id}/{name}")
    public R updateSub(@ApiParam("二级章节id") @PathVariable String id, @ApiParam("二级章节名") @PathVariable String name) {
        SubChapter subChapter = subChapterService.getById(id);
        if(ObjectUtils.isEmpty(subChapter)) {
            return R.error().message("二级章节不存在");
        }
        subChapter.setName(name);
        boolean update = subChapterService.updateById(subChapter);
        if(update) {
            return R.ok().message("更新成功");
        }
        return R.error().message("更新失败");
    }

    @ApiOperation("根据id删除单个章节")
    @DeleteMapping("/remove-chapter/{chapterId}")
    public R removeChapter(@ApiParam("章节id") @PathVariable String chapterId) {
        boolean remove = chapterService.removeById(chapterId);
        if(remove) {
            QueryWrapper<SubChapter> wrapper = new QueryWrapper<>();
            wrapper.eq("chapter_id", chapterId);
            subChapterService.remove(wrapper);
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id删除单个二级章节")
    @DeleteMapping("/remove-sub/{subId}")
    public R removeSub(@ApiParam("子章节id") @PathVariable String subId) {
        boolean remove = subChapterService.removeById(subId);
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

}

