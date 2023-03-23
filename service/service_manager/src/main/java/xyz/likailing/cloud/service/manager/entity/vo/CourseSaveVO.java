package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;
import xyz.likailing.cloud.service.manager.entity.Course;

import java.io.Serializable;
import java.util.List;

@Data
public class CourseSaveVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Course course;

    private List<String> teacherIds;

    private List<String> classIds;
}
