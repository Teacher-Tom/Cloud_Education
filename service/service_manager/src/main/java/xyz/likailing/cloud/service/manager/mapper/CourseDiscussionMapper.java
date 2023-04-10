package xyz.likailing.cloud.service.manager.mapper;

import xyz.likailing.cloud.service.manager.entity.CourseDiscussion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.likailing.cloud.service.manager.entity.vo.DiscussionVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author derek
 * @since 2023-04-10
 */
public interface CourseDiscussionMapper extends BaseMapper<CourseDiscussion> {

    List<DiscussionVO> selectAll(String resourceId);

    List<DiscussionVO> selectPageAll(String resourceId, Integer page);
}
