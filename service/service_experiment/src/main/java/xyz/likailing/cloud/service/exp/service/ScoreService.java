package xyz.likailing.cloud.service.exp.service;

import xyz.likailing.cloud.service.exp.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 12042
* @description 针对表【exp_score】的数据库操作Service
* @createDate 2023-03-28 15:09:48
*/
public interface ScoreService extends IService<Score> {

    int getByStudentIdAndExpId(String studenId, String expId);

    Boolean saveScoreByStudentIdAndExpId(String studenId, String expId, Integer score);
}
