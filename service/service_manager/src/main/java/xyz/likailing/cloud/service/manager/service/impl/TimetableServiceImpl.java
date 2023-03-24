package xyz.likailing.cloud.service.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.util.ObjectUtils;
import xyz.likailing.cloud.common.base.util.RedisCache;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableVO;
import xyz.likailing.cloud.service.manager.mapper.TimetableMapper;
import xyz.likailing.cloud.service.manager.service.TimetableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Override
    public List<Timetable> listCourseTime(String courseId) {
        QueryWrapper<Timetable> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        return baseMapper.selectList(wrapper);
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

}
