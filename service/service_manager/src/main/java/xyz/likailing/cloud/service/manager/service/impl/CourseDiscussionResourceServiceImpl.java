package xyz.likailing.cloud.service.manager.service.impl;

import xyz.likailing.cloud.service.manager.entity.CourseDiscussionResource;
import xyz.likailing.cloud.service.manager.mapper.CourseDiscussionResourceMapper;
import xyz.likailing.cloud.service.manager.service.CourseDiscussionResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 每条讨论下附带的图片、文件、链接资源 服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-04-10
 */
@Service
public class CourseDiscussionResourceServiceImpl extends ServiceImpl<CourseDiscussionResourceMapper, CourseDiscussionResource> implements CourseDiscussionResourceService {

}
