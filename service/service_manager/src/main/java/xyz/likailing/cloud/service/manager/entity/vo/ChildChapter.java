package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;
import xyz.likailing.cloud.service.manager.entity.SubChapter;
import xyz.likailing.cloud.service.manager.entity.Timetable;

import java.io.Serializable;
import java.util.List;

@Data
public class ChildChapter implements Serializable {

    private static final long serialVersionUID=1L;

    private SubChapter subChapter;

    private List<Timetable> timetables;

}
