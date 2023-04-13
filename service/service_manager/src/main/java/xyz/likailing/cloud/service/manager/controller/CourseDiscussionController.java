package xyz.likailing.cloud.service.manager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.CourseDiscussion;
import xyz.likailing.cloud.service.manager.entity.CourseDiscussionReply;
import xyz.likailing.cloud.service.manager.entity.Student;
import xyz.likailing.cloud.service.manager.entity.User;
import xyz.likailing.cloud.service.manager.entity.vo.DiscussionVO;
import xyz.likailing.cloud.service.manager.service.CourseDiscussionReplyService;
import xyz.likailing.cloud.service.manager.service.CourseDiscussionService;
import xyz.likailing.cloud.service.manager.service.UserService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author derek
 * @since 2023-04-10
 */
@RestController
@RequestMapping("/manager/course-discussion")
@CrossOrigin
public class CourseDiscussionController {

    @Autowired
    private CourseDiscussionService discussionService;
    @Autowired
    private CourseDiscussionReplyService replyService;
    @Autowired
    private UserService userService;

    @ApiOperation("获取某个资源讨论区的全部评论列表")
    @GetMapping("/list-all/{resourceId}")
    public R listAll(@ApiParam("资源id") @PathVariable String resourceId) {
        List<DiscussionVO> discussions = discussionService.listAll(resourceId);
        return R.ok().data("discussions", discussions);
    }

    @ApiOperation("获取某个资源讨论区的某一页的评论列表")
    @GetMapping("/list-page/{resourceId}/{page}")
    public R listPage(@ApiParam("资源id") @PathVariable String resourceId, @ApiParam("页号") @PathVariable Integer page) {
        List<DiscussionVO> discussions = discussionService.listPage(resourceId, page);
        return R.ok().data("discussions", discussions);
    }

    @ApiOperation("用户对资源的某一页发表讨论")
    @PostMapping("/issue")
    public R issue(@ApiParam(value = "讨论对象", required = true) @RequestBody CourseDiscussion courseDiscussion) {
        courseDiscussion.setSendTime(new Date());
        courseDiscussion.setLikes(0);
        boolean save = discussionService.save(courseDiscussion);
        if(save) {
            return R.ok().data("discussionId", courseDiscussion.getId()).message("发布成功");
        }
        return R.error().message("发布失败");
    }

    @ApiOperation("用户对某一个讨论进行回复")
    @PostMapping("/reply")
    public R reply(@ApiParam("回复对象") @RequestBody CourseDiscussionReply discussionReply) {
        discussionReply.setSendTime(new Date());
        discussionReply.setLikes(0);
        User user = userService.getById(discussionReply.getUserId());
        if(ObjectUtils.isEmpty(user)) {
            return R.error().message("用户不存在");
        }
        discussionReply.setUsername(user.getUsername());
        boolean save = replyService.save(discussionReply);
        if(save) {
            return R.ok().data("replyId", discussionReply.getId()).message("发布成功");
        }
        return R.error().message("发布失败");
    }

    @ApiOperation("用户对讨论点赞")
    @Transactional
    @PutMapping("/like-issue/{discussionId}")
    public R likeIssue(@ApiParam(value = "讨论id", required = true) @PathVariable String discussionId) {
        CourseDiscussion discussion = discussionService.getById(discussionId);
        if(ObjectUtils.isEmpty(discussion)) {
            return R.error().message("该讨论不存在");
        }
        discussion.setLikes(discussion.getLikes() + 1);
        boolean update = discussionService.updateById(discussion);
        if(update) {
            return R.ok().message("点赞成功");
        }
        return R.error().message("点赞失败");
    }

    @ApiOperation("用户对讨论取消点赞")
    @Transactional
    @DeleteMapping("/dislike-issue/{discussionId}")
    public R dislikeIssue(@ApiParam(value = "讨论id", required = true) @PathVariable String discussionId) {
        CourseDiscussion discussion = discussionService.getById(discussionId);
        if(ObjectUtils.isEmpty(discussion)) {
            return R.error().message("该讨论不存在");
        }
        discussion.setLikes(Math.max(discussion.getLikes() - 1, 0));
        boolean update = discussionService.updateById(discussion);
        if(update) {
            return R.ok().message("取消成功");
        }
        return R.error().message("取消失败");
    }

    @ApiOperation("用户对某一个回复点赞")
    @Transactional
    @PutMapping("/like-reply/{replyId}")
    public R likeReply(@ApiParam(value = "回复id", required = true) @PathVariable String replyId) {
        CourseDiscussionReply reply = replyService.getById(replyId);
        if(ObjectUtils.isEmpty(reply)) {
            return R.error().message("该回复不存在");
        }
        reply.setLikes(reply.getLikes() + 1);
        boolean update = replyService.updateById(reply);
        if(update) {
            return R.ok().message("点赞成功");
        }
        return R.error().message("点赞失败");
    }

    @ApiOperation("用户对某一个回复取消点赞")
    @Transactional
    @DeleteMapping("/dislike-reply/{replyId}")
    public R dislikeReply(@ApiParam(value = "回复id", required = true) @PathVariable String replyId) {
        CourseDiscussionReply reply = replyService.getById(replyId);
        if(ObjectUtils.isEmpty(reply)) {
            return R.error().message("该回复不存在");
        }
        reply.setLikes(Math.max(reply.getLikes() - 1, 0));
        boolean update = replyService.updateById(reply);
        if(update) {
            return R.ok().message("取消成功");
        }
        return R.error().message("取消失败");
    }

//    @ApiOperation("根据id查询讨论")
//    @GetMapping("/get-discussion/{id}")
//    public R getDiscussionById(@ApiParam(value = "讨论id", required = true) @PathVariable String id) {
//        DiscussionVO discussion = discussionService.getDiscussionById(id);
//        if(!ObjectUtils.isEmpty(discussion)) {
//            return R.ok().data("discussion", discussion);
//        }
//        return R.error().message("数据不存在");
//    }

    @ApiOperation("根据id删除讨论")
    @DeleteMapping("/remove-discussion/{discussionId}")
    public R removeDiscussionById(@ApiParam(value = "讨论id", required = true) @PathVariable String discussionId) {
        boolean remove = discussionService.removeById(discussionId);
        //删除所有回复
        QueryWrapper<CourseDiscussionReply> replyQueryWrapper = new QueryWrapper<>();
        replyQueryWrapper.eq("discussion_id", discussionId);
        replyService.remove(replyQueryWrapper);
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }

    @ApiOperation("根据id删除回复")
    @DeleteMapping("/remove-reply/{replyId}")
    public R removeReplyById(@ApiParam(value = "回复id", required = true) @PathVariable String replyId) {
        boolean remove = replyService.removeById(replyId);
        if(remove) {
            return R.ok().message("删除成功");
        }
        return R.error().message("数据不存在");
    }
}

