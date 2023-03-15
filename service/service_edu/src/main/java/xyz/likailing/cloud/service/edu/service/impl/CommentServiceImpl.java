package xyz.likailing.cloud.service.edu.service.impl;

import xyz.likailing.cloud.service.edu.entity.Comment;
import xyz.likailing.cloud.service.edu.mapper.CommentMapper;
import xyz.likailing.cloud.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author likailing
 * @since 2023-01-31
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
