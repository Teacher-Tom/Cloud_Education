package xyz.likailing.cloud.service.manager.service;

import xyz.likailing.cloud.service.manager.entity.CourseDiscussion;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.manager.entity.vo.DiscussionVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-04-10
 */
public interface CourseDiscussionService extends IService<CourseDiscussion> {

    List<DiscussionVO> listAll(String resourceId);

    List<DiscussionVO> listPage(String resourceId, Integer page);
}
