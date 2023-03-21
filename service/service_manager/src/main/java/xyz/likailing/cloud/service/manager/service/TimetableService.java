package xyz.likailing.cloud.service.manager.service;

import xyz.likailing.cloud.service.manager.entity.Timetable;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.manager.entity.vo.TimetableVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-03-11
 */
public interface TimetableService extends IService<Timetable> {

    List<Timetable> listCourseTime(String courseId);

    List<Timetable> tempList(TimetableVO timetableVO);

    boolean saveTempList();

    boolean removeTempElement(Timetable timetable);
}
