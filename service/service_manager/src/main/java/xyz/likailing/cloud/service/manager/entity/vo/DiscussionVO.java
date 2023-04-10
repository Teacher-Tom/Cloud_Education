package xyz.likailing.cloud.service.manager.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import xyz.likailing.cloud.service.manager.entity.CourseDiscussionReply;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class DiscussionVO implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    private String resourceId;

    private String userId;

    private String userName;

    private String content;

    private Integer page;

    private Date sendTime;

    private Integer likes;

    private List<CourseDiscussionReply> replies;
}
