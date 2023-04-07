package xyz.likailing.cloud.service.msg.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.common.base.util.JwtInfo;
import xyz.likailing.cloud.common.base.util.JwtUtils;
import xyz.likailing.cloud.service.msg.entity.Message;
import xyz.likailing.cloud.service.msg.entity.vo.MessageVo;
import xyz.likailing.cloud.service.msg.mapper.MessageMapper;
import xyz.likailing.cloud.service.msg.mapper.MessageUserMapper;
import xyz.likailing.cloud.service.msg.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author 12042
 * @create 2023/3/25 14:19
 */
@RestController
@RequestMapping("/msg")
@CrossOrigin
public class MsgController {
    @Autowired
    MessageService messageService;
    @Autowired
    MessageUserMapper messageUserMapper;

    @Autowired
    MessageMapper messageMapper;



    @ApiOperation("向课程全员发送自定义通知")
    @PostMapping("send")
    public R sendMessage(@RequestBody MessageVo msgVo){
        messageService.sendMessage(msgVo.getCourseId(),msgVo.getTeacherUserId(),msgVo.getTitle(), msgVo.getContent());
        return R.ok().message("通知发送成功!");
    }
    @ApiOperation("根据id查看某条消息")
    @GetMapping("check/{msgId}")
    public R readMessage(@PathVariable String msgId){
        Message message = messageService.checkMessage(msgId);
        return R.ok().data("msg",message);
    }
    @ApiOperation("根据用户id列出所有消息")
    @GetMapping("list/user")
    public R listMessageByUserId(HttpServletRequest request){
        JwtInfo jwtInfo = JwtUtils.getUserIdByJwtToken(request);
        String userId = jwtInfo.getId();
        List<Message> messageList = messageService.listMessageByUserId(userId);
        List<Boolean> hasRead = messageUserMapper.selectHasReadByUserId(userId);
        return R.ok().data("list",messageList).data("hasReads",hasRead);
    }
    @ApiOperation("根据课程id列出所有消息")
    @GetMapping("list/msg/{courseId}")
    public R listMessageByCourseId(@PathVariable String courseId){
        List<Message> messages = messageMapper.selectAllByCourseIdOrderByGmtCreateDesc(courseId);
        return R.ok().data("list",messages);
    }

}
