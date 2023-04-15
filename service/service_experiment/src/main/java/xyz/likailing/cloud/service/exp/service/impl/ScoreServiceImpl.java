package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.exp.entity.Score;
import xyz.likailing.cloud.service.exp.service.ScoreService;
import xyz.likailing.cloud.service.exp.mapper.ScoreMapper;
import org.springframework.stereotype.Service;

/**
* @author 12042
* @description 针对表【exp_score】的数据库操作Service实现
* @createDate 2023-03-28 15:09:48
*/
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score>
    implements ScoreService{

    @Override
    public int getByStudentIdAndExpId(String studenId, String expId) {
        return 0;
    }

    @Override
    public Boolean saveScoreByStudentIdAndExpId(String studenId, String expId, Integer score) {
        return null;
    }
}




