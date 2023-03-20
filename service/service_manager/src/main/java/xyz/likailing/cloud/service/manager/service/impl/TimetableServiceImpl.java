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

    @Override
    public List<Timetable> listCourseTime(String courseId) {
        QueryWrapper<Timetable> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<Timetable> tempList(TimetableVO timetableVO) {
        if(ObjectUtils.isEmpty(timetableVO)) return null;

        ArrayList<Timetable> timetables = new ArrayList<>();
        Integer beginWeek = timetableVO.getBeginWeek();
        Integer endWeek = timetableVO.getEndWeek();
        for (int i = beginWeek; i <= endWeek; i++) {
            Timetable timetable = new Timetable();
            BeanUtils.copyProperties(timetableVO, timetable);
            timetable.setWeek(i);
            timetables.add(timetable);
        }
        return timetables;
    }

    @Override
    public boolean saveTempList(List<Timetable> tempList) {
        int insert = 0;
        for (Timetable timetable : tempList) {
            insert += baseMapper.insert(timetable);
        }
        return (insert > 0);
    }

}
