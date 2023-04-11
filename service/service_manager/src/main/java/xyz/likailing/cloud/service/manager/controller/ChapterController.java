package xyz.likailing.cloud.service.manager.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.Chapter;
import xyz.likailing.cloud.service.manager.entity.ChapterTimetable;
import xyz.likailing.cloud.service.manager.entity.SubChapter;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.entity.vo.ChapterVO;
import xyz.likailing.cloud.service.manager.entity.vo.ChildChapter;
import xyz.likailing.cloud.service.manager.service.ChapterService;
import xyz.likailing.cloud.service.manager.service.ChapterTimetableService;
import xyz.likailing.cloud.service.manager.service.SubChapterService;

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
public class ChapterController {

    @Autowired
    private ChapterService chapterService;
    @Autowired
    private SubChapterService subChapterService;
    @Autowired
    private ChapterTimetableService timetableService;

    @ApiOperation("根据课程id获取课程大纲")
    @GetMapping("/get/{courseId}")
    public R getCourseChapter(@ApiParam("课程id") @PathVariable String courseId) {
        List<ChapterVO> chapter = chapterService.getCourseChapter(courseId);
        return R.ok().data("chapters", chapter);
    }

    @ApiOperation("新建大纲，传入完整大纲，需要指定课程id，每个对应小节只需要传入id")
    @PostMapping("/save/{courseId}")
    public R save(@ApiParam("课程id") @PathVariable String courseId,
                  @ApiParam("大纲信息") @RequestBody List<ChapterVO> chapterSaveVOS) {
        Map<Integer, String> chapterIds = new HashMap<>();
        for (ChapterVO chapterSaveVO : chapterSaveVOS) {
            //章节信息
            Chapter chapter = chapterSaveVO.getChapter();
            if(!ObjectUtils.isEmpty(chapter)) {
                chapter.setCourseId(courseId);
                List<ChildChapter> children = chapterSaveVO.getChildren();
                boolean save = chapterService.save(chapter);
                if(save) {
                    String id = chapter.getId();
                    for (ChildChapter childChapter : children) {
                        //子章节信息
                        SubChapter child = childChapter.getSubChapter();
                        child.setChapterId(id);
                        child.setCourseId(courseId);
                        boolean saveSub = subChapterService.save(child);
                        if(saveSub) {
                            String subId = child.getId();
                            //对应的小节
                            List<Timetable> timetables = childChapter.getTimetables();
                            for (Timetable timetable : timetables) {
                                ChapterTimetable chapterTimetable = new ChapterTimetable();
                                chapterTimetable.setCourseId(courseId);
                                chapterTimetable.setChapterId(id);
                                chapterTimetable.setSubChapterId(subId);
                                chapterTimetable.setTimetableId(timetable.getId());
                                timetableService.save(chapterTimetable);
                            }
                        }
                    }
                    chapterIds.put(chapter.getNumber(), chapter.getId());
                }
            }
        }
        if(!chapterIds.isEmpty()) {
            return R.ok().data("chapterIds", chapterIds).message("保存成功");
        }
        return R.error().message("关键数据缺少，保存失败");
    }

}

