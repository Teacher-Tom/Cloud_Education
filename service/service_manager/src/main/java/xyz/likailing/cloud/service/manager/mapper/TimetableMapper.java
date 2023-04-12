package xyz.likailing.cloud.service.manager.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableGetVO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author likailing
 * @since 2023-03-11
 */
@Mapper
public interface TimetableMapper extends BaseMapper<Timetable> {

    List<TimetableGetVO> selectTimetableList(String courseId);

    List<Timetable> selectExpiredTimetable(Date date);

    List<Timetable> selectTodayTimetable();

    int updateExpiredTimetable();

    int updateTodayTimetable();

    List<Timetable> selectSubTimetables(String subId);
}
