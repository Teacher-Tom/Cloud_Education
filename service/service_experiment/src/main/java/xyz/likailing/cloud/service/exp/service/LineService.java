package xyz.likailing.cloud.service.exp.service;

import xyz.likailing.cloud.service.exp.entity.Line;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 12042
* @description 针对表【exp_line】的数据库操作Service
* @createDate 2023-03-28 17:51:25
*/
public interface LineService extends IService<Line> {

    List<Line> getAllLinesByExpId(String expId);
}
