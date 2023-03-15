package xyz.likailing.cloud.service.edu.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import xyz.likailing.cloud.service.edu.entity.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import xyz.likailing.cloud.service.edu.entity.vo.CourseVo;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author likailing
 * @since 2023-01-31
 */
public interface CourseMapper extends BaseMapper<Course> {
    @Select("SELECT \n" +
            "\tc.id,\n" +
            "\tc.`title`,\n" +
            "\tt.`name` AS teacherName,\n" +
            "\tc.`lesson_num` AS lessonNum,\n" +
            "\tc.`price`,\n" +
            "\tc.`cover`,\n" +
            "\tc.`buy_count` AS buyCount,\n" +
            "\tc.`view_count` AS viewCount,\n" +
            "\tc.`status`,\n" +
            "\tc.`gmt_create` AS gmtCreate,\n" +
            "\ts2.`title` AS subjectTitle,\n" +
            "\ts1.`title` AS subjectParentTitel\n" +
            "FROM edu_course c\n" +
            "LEFT JOIN edu_teacher t ON c.`teacher_id` = t.`id`\n" +
            "LEFT JOIN edu_subject s1 ON c.`subject_parent_id` = s1.`id`\n" +
            "LEFT JOIN edu_subject s2 ON c.`subject_id` = s2.`id`")
    List<CourseVo> selectPageByCourseQueryVo(@Param("courseVoPage") Page<CourseVo> courseVoPage);

}
