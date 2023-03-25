package xyz.likailing.cloud.service.manager.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TimetableGetVO implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;

    private String courseName;

    private Date date;

    private Integer week;

    private Integer dayOfWeek;

    private Integer beginIndex;

    private Integer endIndex;

    private String location;

    private String teacherName;

    @TableField("is_outdated")
    private Boolean outdated;
}
