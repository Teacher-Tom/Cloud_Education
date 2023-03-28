package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.exp.entity.Line;
import xyz.likailing.cloud.service.exp.service.LineService;
import xyz.likailing.cloud.service.exp.mapper.LineMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 12042
* @description 针对表【exp_line】的数据库操作Service实现
* @createDate 2023-03-28 17:51:25
*/
@Service
public class LineServiceImpl extends ServiceImpl<LineMapper, Line>
    implements LineService{


    @Override
    public List<Line> getAllLinesByExpId(String expId) {
        QueryWrapper<Line> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("experiment_id",expId);
        List<Line> lines = baseMapper.selectList(queryWrapper);
        return lines;
    }
}




