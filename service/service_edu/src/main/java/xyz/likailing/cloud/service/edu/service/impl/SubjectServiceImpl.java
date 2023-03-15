package xyz.likailing.cloud.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import xyz.likailing.cloud.service.edu.entity.Subject;
import xyz.likailing.cloud.service.edu.entity.excel.ExcelSubjectData;
import xyz.likailing.cloud.service.edu.listener.ExcelSubjectDataListener;
import xyz.likailing.cloud.service.edu.mapper.SubjectMapper;
import xyz.likailing.cloud.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author likailing
 * @since 2023-01-31
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public void batchImport(InputStream inputStream) {

        EasyExcel.read(inputStream, ExcelSubjectData.class,new ExcelSubjectDataListener())
                .excelType(ExcelTypeEnum.XLS)
                .sheet()
                .doRead();
    }
}
