package xyz.likailing.cloud.service.manager.service;

import xyz.likailing.cloud.service.manager.entity.SubChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import xyz.likailing.cloud.service.manager.entity.vo.ChildChapter;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author derek
 * @since 2023-03-14
 */
public interface SubChapterService extends IService<SubChapter> {

    void saveChildChapter(ChildChapter childChapter, String courseId, String id);
}
