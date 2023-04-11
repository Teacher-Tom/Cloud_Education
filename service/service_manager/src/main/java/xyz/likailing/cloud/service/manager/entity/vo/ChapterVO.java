package xyz.likailing.cloud.service.manager.entity.vo;

import lombok.Data;
import xyz.likailing.cloud.service.manager.entity.Chapter;

import java.io.Serializable;
import java.util.List;

@Data
public class ChapterVO implements Serializable {

    private static final long serialVersionUID=1L;

    private Chapter chapter;

    private List<ChildChapter> children;

}
