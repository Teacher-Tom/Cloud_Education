package xyz.likailing.cloud.service.manager.service.impl;

import xyz.likailing.cloud.service.manager.entity.CourseDiscussion;
import xyz.likailing.cloud.service.manager.entity.vo.DiscussionVO;
import xyz.likailing.cloud.service.manager.mapper.CourseDiscussionMapper;
import xyz.likailing.cloud.service.manager.service.CourseDiscussionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-04-10
 */
@Service
public class CourseDiscussionServiceImpl extends ServiceImpl<CourseDiscussionMapper, CourseDiscussion> implements CourseDiscussionService {

    @Override
    public List<DiscussionVO> listAll(String resourceId) {
        return baseMapper.selectAll(resourceId);
    }

    @Override
    public List<DiscussionVO> listPage(String resourceId, Integer page) {
        return baseMapper.selectPageAll(resourceId, page);
    }
}
