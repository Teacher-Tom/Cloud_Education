package xyz.likailing.cloud.service.manager.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import xyz.likailing.cloud.service.manager.entity.SubChapter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    private String teacherId;

    private String teacherName;

    private Integer status;

    private List<SubChapter> subChapters;
}
