package xyz.likailing.cloud.service.exp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.exp.entity.Team;
import xyz.likailing.cloud.service.exp.service.TeamService;
import xyz.likailing.cloud.service.exp.mapper.TeamMapper;
import org.springframework.stereotype.Service;

/**
* @author 12042
* @description 针对表【exp_team】的数据库操作Service实现
* @createDate 2023-03-28 15:09:48
*/
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
    implements TeamService{

}




