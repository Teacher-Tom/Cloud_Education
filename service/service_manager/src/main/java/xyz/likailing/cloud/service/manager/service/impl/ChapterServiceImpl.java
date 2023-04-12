package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.likailing.cloud.service.manager.entity.Chapter;
import xyz.likailing.cloud.service.manager.entity.ChapterTimetable;
import xyz.likailing.cloud.service.manager.entity.SubChapter;
import xyz.likailing.cloud.service.manager.entity.vo.ChapterVO;
import xyz.likailing.cloud.service.manager.mapper.ChapterMapper;
import xyz.likailing.cloud.service.manager.mapper.ChapterTimetableMapper;
import xyz.likailing.cloud.service.manager.mapper.SubChapterMapper;
import xyz.likailing.cloud.service.manager.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private SubChapterMapper subChapterMapper;
    @Autowired
    private ChapterTimetableMapper timetableMapper;

    @Override
    public List<ChapterVO> getCourseChapter(String courseId) {
        return baseMapper.selectCourseChapter(courseId);
    }

    @Override
    public void removeCourseChapter(String courseId) {
        QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<Chapter> chapters = baseMapper.selectList(chapterQueryWrapper);
        for (Chapter chapter : chapters) {
            QueryWrapper<SubChapter> subChapterQueryWrapper = new QueryWrapper<>();
            subChapterQueryWrapper.eq("chapter_id", chapter.getId());
            List<SubChapter> subChapters = subChapterMapper.selectList(subChapterQueryWrapper);
            for (SubChapter subChapter : subChapters) {
                QueryWrapper<ChapterTimetable> chapterTimetableQueryWrapper = new QueryWrapper<>();
                chapterTimetableQueryWrapper.eq("sub_chapter_id", subChapter.getId());
                timetableMapper.delete(chapterTimetableQueryWrapper);
            }
            subChapterMapper.delete(subChapterQueryWrapper);
        }
        int delete = baseMapper.delete(chapterQueryWrapper);
    }
}
