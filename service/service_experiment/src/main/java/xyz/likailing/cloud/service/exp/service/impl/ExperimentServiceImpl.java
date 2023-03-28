package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.exp.entity.Experiment;
import xyz.likailing.cloud.service.exp.entity.User;
import xyz.likailing.cloud.service.exp.mapper.UserMapper;
import xyz.likailing.cloud.service.exp.service.ExperimentService;
import xyz.likailing.cloud.service.exp.mapper.ExperimentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
* @author 12042
* @description 针对表【exp_experiment】的数据库操作Service实现
* @createDate 2023-03-28 15:23:14
*/
@Service
public class ExperimentServiceImpl extends ServiceImpl<ExperimentMapper, Experiment>
    implements ExperimentService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Experiment> listByCourseId(String courseId) {
        QueryWrapper<Experiment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",courseId);
        List<Experiment> experiments = baseMapper.selectList(queryWrapper);
        return experiments;
    }

    @Override
    public List<Experiment> listAllExpsByUserId(String userId) {
        //查询用户身份
        User user = userMapper.selectById(userId);
        if(Objects.isNull(user)){
            throw new CloudException(ResultCodeEnum.PARAM_ERROR);
        }
        String role = user.getRole();
        if (StringUtils.isEmpty(role)){
            throw new CloudException(ResultCodeEnum.PARAM_ERROR);
        }
        if(role.equals("student")){
            List<Experiment> experiments = userMapper.selectExpsByStudentId(userId);
            return experiments;
        } else if (role.equals("teacher")) {
            List<Experiment> experiments = userMapper.selectExpsByTeacherId(userId);
            return experiments;
        }else {
            throw new CloudException("用户不是学生或者管理员",20001);
        }
    }
}




