package xyz.likailing.cloud.service.edu.service.impl;

import xyz.likailing.cloud.service.edu.entity.Video;
import xyz.likailing.cloud.service.edu.mapper.VideoMapper;
import xyz.likailing.cloud.service.edu.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author likailing
 * @since 2023-01-31
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
