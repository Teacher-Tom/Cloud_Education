package xyz.likailing.cloud.service.manager.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class HomeworkVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String name;

    private Date beginTime;

    private Date endTime;

    private Boolean outdated;

    private Boolean marked;

    private Integer score;

    private String review;

    private String courseId;

    private String timetableId;

    private String isGlobal;

    private String context;

    private String refAnswer;

    private String pictureUrl;
}
