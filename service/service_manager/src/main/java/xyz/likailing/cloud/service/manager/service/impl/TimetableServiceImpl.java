package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.util.ObjectUtils;
import xyz.likailing.cloud.common.base.util.RedisCache;
import xyz.likailing.cloud.service.manager.entity.ChapterTimetable;
import xyz.likailing.cloud.service.manager.entity.SubChapter;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableGetVO;
import xyz.likailing.cloud.service.manager.mapper.ChapterTimetableMapper;
import xyz.likailing.cloud.service.manager.mapper.SubChapterMapper;
import xyz.likailing.cloud.service.manager.mapper.TimetableMapper;
import xyz.likailing.cloud.service.manager.service.TimetableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
@Service
public class TimetableServiceImpl extends ServiceImpl<TimetableMapper, Timetable> implements TimetableService {

    @Autowired
    private RedisCache redisCache;

    private final String key = "tempSchList";

    @Autowired
    private ChapterTimetableMapper chapterTimetableMapper;
    @Autowired
    private SubChapterMapper subChapterMapper;

    @Override
    public List<TimetableGetVO> listCourseTime(String courseId) {
        return baseMapper.selectTimetableList(courseId);
    }

//    @Override
//    public Set<Timetable> getTempList(String userId, TimetableVO timetableVO) {
//        Integer beginWeek = timetableVO.getBeginWeek();
//        Integer endWeek = timetableVO.getEndWeek();
//
//        Set<Timetable> timetables = redisCache.getCacheSet(key + userId);
//        if(ObjectUtils.isEmpty(timetables)) {
//            timetables = new HashSet<>();
//        }
//        for (int i = beginWeek; i <= endWeek; i++) {
//            Timetable timetable = new Timetable();
//            BeanUtils.copyProperties(timetableVO, timetable);
//            timetable.setWeek(i);
//            timetables.add(timetable);
//        }
//        redisCache.setCacheSet(key + userId, timetables);
//        return timetables;
//    }
//
//    @Override
//    public boolean saveUserTempList(String userId) {
//        Set<Timetable> timetables = redisCache.getCacheSet(key + userId);
//        redisCache.deleteObject(key + userId); //存储到数据库后删除缓存数据
//        if(ObjectUtils.isEmpty(timetables)) {
//            return false;
//        }
//        int insert = 0;
//        for (Timetable timetable : timetables) {
//            insert += baseMapper.insert(timetable);
//        }
//        return (insert > 0);
//    }
//
//    @Override
//    public boolean removeTempElement(String userId, Timetable timetable) {
//        Set<Timetable> timetables = redisCache.getCacheSet(key + userId);
//        if(ObjectUtils.isEmpty(timetables)) {
//            return false;
//        }
//        boolean remove = timetables.remove(timetable);
//        redisCache.setCacheSet(key + userId, timetables);
//        return remove;
//    }

    @Override
    public boolean saveTempList(List<Timetable> allList) {
        int insert = 0;
        for (Timetable timetable : allList) {
            insert = baseMapper.insert(timetable);
        }
        return (insert > 0);
    }

    @Override
    public List<Timetable> getSubTimetables(String subId) {
        return baseMapper.selectSubTimetables(subId);
    }

    @Override
    public boolean addChapter(String id, List<String> subs) {
        Timetable timetable = baseMapper.selectById(id);
        String courseId = timetable.getCourseId();
        int insert = 0;
        for (String sub : subs) {
            SubChapter subChapter = subChapterMapper.selectById(sub);
            if(ObjectUtils.isEmpty(subChapter)) continue;

            ChapterTimetable chapterTimetable = new ChapterTimetable();
            chapterTimetable.setSubChapterId(sub);
            chapterTimetable.setTimetableId(id);
            chapterTimetable.setCourseId(courseId);
            chapterTimetable.setChapterId(subChapter.getChapterId());
            insert += chapterTimetableMapper.insert(chapterTimetable);
        }
        return (insert >= 1);
    }

    @Override
    public List<SubChapter> getChapter(String id) {
        return baseMapper.selectChapter(id);
    }

}
