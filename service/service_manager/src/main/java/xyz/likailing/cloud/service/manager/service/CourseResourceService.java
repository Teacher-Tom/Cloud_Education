package xyz.likailing.cloud.service.manager.service;

import xyz.likailing.cloud.service.base.model.File;
import xyz.likailing.cloud.service.manager.entity.CourseResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-03-20
 */
public interface CourseResourceService extends IService<CourseResource> {

    List<File> getPPTByTimetableId(String timetableId);

    List<CourseResource> getPPTResourceByTimetableId(String timetableId);

    List<String> getVideoUrlByTimetableId(String timetableId);

    List<CourseResource> getVideoResourceByTimetableId(String timetableId);

    List<File> getSharedFilesByTimetableId(String timetableId);

    List<CourseResource> getSharedFilesResourceByTimetableId(String timetableId);


}
