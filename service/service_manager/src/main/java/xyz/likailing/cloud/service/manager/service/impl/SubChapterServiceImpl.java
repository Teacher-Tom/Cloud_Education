package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.likailing.cloud.service.manager.entity.ChapterTimetable;
import xyz.likailing.cloud.service.manager.entity.SubChapter;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.entity.vo.ChildChapter;
import xyz.likailing.cloud.service.manager.mapper.ChapterTimetableMapper;
import xyz.likailing.cloud.service.manager.mapper.SubChapterMapper;
import xyz.likailing.cloud.service.manager.service.SubChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-03-14
 */
@Service
public class SubChapterServiceImpl extends ServiceImpl<SubChapterMapper, SubChapter> implements SubChapterService {

    @Autowired
    private ChapterTimetableMapper timetableMapper;

    @Override
    public void saveChildChapter(ChildChapter childChapter, String courseId, String id) {
        SubChapter subChapter = childChapter.getSubChapter();
        subChapter.setChapterId(id);
        subChapter.setCourseId(courseId);
        int saveSub = baseMapper.insert(subChapter);
        if(saveSub >= 1) {
            String subId = subChapter.getId();
            //对应的小节
            List<Timetable> timetables = childChapter.getTimetables();
            for (Timetable timetable : timetables) {
                ChapterTimetable chapterTimetable = new ChapterTimetable();
                chapterTimetable.setCourseId(courseId);
                chapterTimetable.setChapterId(id);
                chapterTimetable.setSubChapterId(subId);
                chapterTimetable.setTimetableId(timetable.getId());
                timetableMapper.insert(chapterTimetable);
            }
        }
    }

}
