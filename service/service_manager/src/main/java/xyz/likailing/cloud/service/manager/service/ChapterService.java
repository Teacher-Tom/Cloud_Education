package xyz.likailing.cloud.service.manager.service;

import xyz.likailing.cloud.service.manager.entity.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.manager.entity.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-03-14
 */
public interface ChapterService extends IService<Chapter> {

    List<ChapterVO> getCourseChapter(String courseId);
}
