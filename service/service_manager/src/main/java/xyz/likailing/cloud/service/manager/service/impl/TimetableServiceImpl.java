package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;



import xyz.likailing.cloud.common.base.util.RedisCache;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableVO;
import xyz.likailing.cloud.service.manager.mapper.TimetableMapper;
import xyz.likailing.cloud.service.manager.service.TimetableService;
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
 * @since 2023-03-11
 */
@Service
public class TimetableServiceImpl extends ServiceImpl<TimetableMapper, Timetable> implements TimetableService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<Timetable> listCourseTime(String courseId) {
        QueryWrapper<Timetable> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Timetable> tempList(TimetableVO timetableVO) {
        Integer beginWeek = timetableVO.getBeginWeek();
        Integer endWeek = timetableVO.getEndWeek();
        List<Timetable> timetables = redisCache.getCacheObject("tempSchList");
        if(timetables == null) {
            timetables = new ArrayList<>();
        }
        for (int i = beginWeek; i <= endWeek; i++) {
            Timetable timetable = new Timetable();
            BeanUtils.copyProperties(timetableVO, timetable);
            timetable.setWeek(i);
            timetables.add(timetable);
        }
        redisCache.setCacheObject("tempSchList", timetables);
        return timetables;
    }

    @Override
    public boolean saveTempList() {
        List<Timetable> timetables = redisCache.getCacheObject("tempSchList");
        if(timetables == null) {
            return false;
        }
        int insert = 0;
        for (Timetable timetable : timetables) {
            insert += baseMapper.insert(timetable);
        }
        return (insert > 0);
    }

    @Override
    public boolean removeTempElement(Timetable timetable) {
        List<Timetable> timetables = redisCache.getCacheObject("tempSchList");
        if(timetables == null) {
            return false;
        }
        boolean remove = timetables.remove(timetable);
        redisCache.setCacheList("tempSchList", timetables);
        return remove;
    }
}
