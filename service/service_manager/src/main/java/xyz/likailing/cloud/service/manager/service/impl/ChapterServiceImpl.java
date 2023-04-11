package xyz.likailing.cloud.service.manager.service.impl;

import xyz.likailing.cloud.service.manager.entity.Chapter;
import xyz.likailing.cloud.service.manager.entity.vo.ChapterVO;
import xyz.likailing.cloud.service.manager.mapper.ChapterMapper;
import xyz.likailing.cloud.service.manager.service.ChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-03-14
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Override
    public List<ChapterVO> getCourseChapter(String courseId) {
        return baseMapper.selectCourseChapter(courseId);
    }
}
