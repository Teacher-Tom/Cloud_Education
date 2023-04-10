package xyz.likailing.cloud.service.manager.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.manager.entity.CourseDiscussion;
import xyz.likailing.cloud.service.manager.entity.CourseDiscussionReply;
import xyz.likailing.cloud.service.manager.entity.Student;
import xyz.likailing.cloud.service.manager.entity.vo.DiscussionVO;
import xyz.likailing.cloud.service.manager.service.CourseDiscussionReplyService;
import xyz.likailing.cloud.service.manager.service.CourseDiscussionService;

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
public class CourseDiscussionController {

    @Autowired
    private CourseDiscussionService discussionService;
    @Autowired
    private CourseDiscussionReplyService replyService;

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
        boolean save = discussionService.save(courseDiscussion);
        if(save) {
            return R.ok().data("discussionId", courseDiscussion.getId()).message("发布成功");
        }
        return R.error().message("发布失败");
    }

    @ApiOperation("用户对某一个讨论进行回复")
    @PutMapping("/reply")
    public R reply(@ApiParam("学生用户对象") @RequestBody CourseDiscussionReply discussionReply) {
        boolean save = replyService.save(discussionReply);
        if(save) {
            return R.ok().data("discussionId", discussionReply.getId()).message("发布成功");
        }
        return R.error().message("发布失败");
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

