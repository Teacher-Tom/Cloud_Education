package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.exp.entity.Experiment;
import xyz.likailing.cloud.service.exp.service.ExperimentService;
import xyz.likailing.cloud.service.exp.mapper.ExperimentMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 12042
* @description 针对表【exp_experiment】的数据库操作Service实现
* @createDate 2023-03-28 15:23:14
*/
@Service
public class ExperimentServiceImpl extends ServiceImpl<ExperimentMapper, Experiment>
    implements ExperimentService{

    @Override
    public List<Experiment> listByCourseId(String courseId) {
        QueryWrapper<Experiment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<Experiment> experiments = baseMapper.selectList(queryWrapper);
        return experiments;
    }
}




