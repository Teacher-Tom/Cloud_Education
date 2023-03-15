package xyz.likailing.cloud.service.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xyz.likailing.cloud.service.entity.File;
import xyz.likailing.cloud.service.entity.UserDir;
import xyz.likailing.cloud.service.mapper.UserDirMapper;
import xyz.likailing.cloud.service.service.FileService;
import xyz.likailing.cloud.service.service.UserDirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-06-11
 */
@Service
public class UserDirServiceImpl extends ServiceImpl<UserDirMapper, UserDir> implements UserDirService {
    @Autowired
    private FileService service;

    @Override
    public UserDir getUserDir(String id) {
        UserDir userDir = baseMapper.selectById(id);
        return userDir;
    }

    @Override
    public int setUserDir(UserDir userDir) {
        return baseMapper.updateById(userDir);
    }

    @Override
    public boolean deleteStruct(String memid, String url) {
        QueryWrapper<File> wrapper=new QueryWrapper<>();
        wrapper.like("f_dir",url);
        wrapper.eq("mem_id",memid);
        boolean b = service.remove(wrapper);
        return b;
    }

}
