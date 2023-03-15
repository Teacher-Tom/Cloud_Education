package xyz.likailing.cloud.service.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import xyz.likailing.cloud.service.edu.entity.excel.ExcelSubjectData;

/**
 * @Author 12042
 * @create 2023/3/8 11:09
 */
@Slf4j
public class ExcelSubjectDataListener extends AnalysisEventListener<ExcelSubjectData> {

    @Override
    public void invoke(ExcelSubjectData data, AnalysisContext context) {
        log.info("解析到一条记录：{}",data );
        String levelOneTitle = data.getLevelOneTitle();
        String levelTwoTitle = data.getLevelTwoTitle();
        log.info("一级标题：{}",levelOneTitle);
        log.info("二级标题：{}",levelTwoTitle);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("全部数据解析完成");
    }
}
