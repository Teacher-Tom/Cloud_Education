package xyz.likailing.cloud.service.manager.mapper;

import xyz.likailing.cloud.service.manager.entity.Chapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.likailing.cloud.service.manager.entity.vo.ChapterVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author derek
 * @since 2023-03-14
 */
public interface ChapterMapper extends BaseMapper<Chapter> {

    List<ChapterVO> selectCourseChapter(String courseId);
}
