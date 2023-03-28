package xyz.likailing.cloud.service.exp.service;

import xyz.likailing.cloud.service.exp.entity.Experiment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 12042
* @description 针对表【exp_experiment】的数据库操作Service
* @createDate 2023-03-28 15:23:14
*/
public interface ExperimentService extends IService<Experiment> {

    List<Experiment> listByCourseId(String courseId);

    List<Experiment> listAllExpsByUserId(String userId);
}
