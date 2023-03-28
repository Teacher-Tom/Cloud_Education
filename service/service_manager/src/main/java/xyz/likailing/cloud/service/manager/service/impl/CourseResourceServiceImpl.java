package xyz.likailing.cloud.service.manager.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.base.model.File;
import xyz.likailing.cloud.service.manager.entity.CourseResource;
import xyz.likailing.cloud.service.manager.feign.AllsService;
import xyz.likailing.cloud.service.manager.mapper.CourseResourceMapper;
import xyz.likailing.cloud.service.manager.service.CourseResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author derek
 * @since 2023-03-20
 */
@Service
public class CourseResourceServiceImpl extends ServiceImpl<CourseResourceMapper, CourseResource> implements CourseResourceService {

    @Autowired
    private AllsService allsService;
    @Override
    public List<File> getPPTByTimetableId(String timetableId) {
        QueryWrapper<CourseResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("timetable_id",timetableId);
        queryWrapper.eq("type",0);
        List<CourseResource> resources = baseMapper.selectList(queryWrapper);
        List<File> result = new ArrayList<>();
        for (CourseResource resource : resources) {
            String id = resource.getFileId();
            R r = allsService.getfileInfo(id);
            List<File> files = JSON.parseArray(JSON.toJSONString(r.getData().get("file")), File.class);
            result.addAll(files);
        }
        return result;
    }

    @Override
    public List<String> getVideoUrlByTimetableId(String timetableId) {
        QueryWrapper<CourseResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("timetable_id",timetableId);
        queryWrapper.eq("type",1);
        List<CourseResource> resources = baseMapper.selectList(queryWrapper);
        List<File> fileList = new ArrayList<>();
        for (CourseResource resource : resources) {
            String id = resource.getFileId();
            R r = allsService.getfileInfo(id);
            List<File> files = JSON.parseArray(JSON.toJSONString(r.getData().get("file")), File.class);
            fileList.addAll(files);
        }
        List<String> urls = new ArrayList<>();
        for (File file : fileList) {
            String vid = file.getVideoId();
            List<String> vids = new ArrayList<>();
            vids.add(vid);
            R playAuth = allsService.getPlayAuth(vids);
            List<Map> urlList = JSON.parseArray(JSON.toJSONString(playAuth.getData().get("urlList")), Map.class);
            for (Map map : urlList) {
                urls.add((String)map.get("url"));
            }
        }
        return urls;
    }

    @Override
    public List<File> getSharedFilesByTimetableId(String timetableId) {
        QueryWrapper<CourseResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("timetable_id",timetableId);
        queryWrapper.eq("type",2);
        List<CourseResource> resources = baseMapper.selectList(queryWrapper);
        List<File> result = new ArrayList<>();
        for (CourseResource resource : resources) {
            String id = resource.getFileId();
            R r = allsService.getfileInfo(id);
            List<File> files = JSON.parseArray(JSON.toJSONString(r.getData().get("file")), File.class);
            result.addAll(files);
        }
        return result;
    }
}
