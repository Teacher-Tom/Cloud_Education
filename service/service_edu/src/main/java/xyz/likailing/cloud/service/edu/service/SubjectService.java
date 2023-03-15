package xyz.likailing.cloud.service.edu.service;

import xyz.likailing.cloud.service.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author likailing
 * @since 2023-01-31
 */
public interface SubjectService extends IService<Subject> {
    void batchImport(InputStream inputStream);
}
