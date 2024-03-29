package xyz.likailing.cloud.service.manager.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import xyz.likailing.cloud.service.base.model.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author derek
 * @since 2023-03-14
 */
@Data
@Accessors(chain = true)
@TableName("manager_chapter_timetable")
@ApiModel(value="ChapterTimetable对象", description="")
public class ChapterTimetable implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "课程id")
    private String courseId;

    @ApiModelProperty(value = "章节id")
    private String chapterId;

    @ApiModelProperty(value = "章节小节id")
    private String subChapterId;

    @ApiModelProperty(value = "时间表小节id")
    private String timetableId;


}
