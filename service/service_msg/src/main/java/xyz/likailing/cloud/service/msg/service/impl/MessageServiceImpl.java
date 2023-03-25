package xyz.likailing.cloud.service.msg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.aspectj.weaver.tools.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.msg.entity.ManagerTeacher;
import xyz.likailing.cloud.service.msg.entity.Message;
import xyz.likailing.cloud.service.msg.entity.MessageUser;
import xyz.likailing.cloud.service.msg.mapper.*;
import xyz.likailing.cloud.service.msg.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author 12042
* @description 针对表【message】的数据库操作Service实现
* @createDate 2023-03-25 14:18:59
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

    @Autowired
    ManagerTeacherMapper teacherMapper;
    @Autowired
    ManagerCourseMapper courseMapper;

    @Autowired
    ManagerStudentMapper studentMapper;

    @Autowired
    MessageUserMapper messageUserMapper;
    @Override
    public void sendMessage(String courseId, String teacherId, String title, String content) {
        if (StringUtils.isEmpty(courseId) ||StringUtils.isEmpty(teacherId) ||StringUtils.isEmpty(title)
                || StringUtils.isEmpty(content)){
            throw new CloudException(ResultCodeEnum.PARAM_ERROR);
        }
        //查询教师名
        QueryWrapper<ManagerTeacher> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",teacherId);
        ManagerTeacher managerTeacher = teacherMapper.selectOne(queryWrapper);
        //将通知存入库
        Message message = new Message();
        message.setCourseId(courseId);
        message.setTeacherId(teacherId);
        message.setAuthor(managerTeacher.getName());
        message.setTitle(title);
        message.setContent(content);
        baseMapper.insert(message);
        //查询课程关联班级
        List<String> classIds = courseMapper.selectClassIdByCourseId(courseId);
        //查询班级关联学生
        for (String classId : classIds) {
            //查询学生的用户id
            List<String> userIds = studentMapper.selectUserIdByClassId(classId);
            //填入关联表
            for (String userId : userIds) {
                MessageUser messageUser = new MessageUser();
                messageUser.setMsgId(message.getId());
                messageUser.setUserId(userId);
                messageUser.setHasRead(false);
                messageUserMapper.insert(messageUser);
            }
        }
    }

    @Override
    public Message checkMessage(String msgId) {
        Message message = baseMapper.selectById(msgId);
        MessageUser messageUser = new MessageUser();
        //设置为已读
        messageUser.setHasRead(true);
        messageUserMapper.updateById(messageUser);
        return message;
    }

    @Override
    public List<Message> listMessageByUserId(String userId) {
        List<Message> messages = messageUserMapper.selectAllMessagesByUserId(userId);
        return messages;
    }
}




