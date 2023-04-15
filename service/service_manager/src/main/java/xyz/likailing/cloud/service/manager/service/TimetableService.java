package xyz.likailing.cloud.service.manager.service;

import xyz.likailing.cloud.service.manager.entity.SubChapter;
import xyz.likailing.cloud.service.manager.entity.Timetable;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableGetVO;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableVO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
public interface TimetableService extends IService<Timetable> {

    List<TimetableGetVO> listCourseTime(String courseId);

//    Set<Timetable> getTempList(String userId, TimetableVO timetableVO);
//
//    boolean saveUserTempList(String userId);
//
//    boolean removeTempElement(String userId, Timetable timetable);

    boolean saveTempList(List<Timetable> allList);

    List<Timetable> getSubTimetables(String subId);

    boolean addChapter(String id, List<String> subs);

    List<SubChapter> getChapter(String id);
}
