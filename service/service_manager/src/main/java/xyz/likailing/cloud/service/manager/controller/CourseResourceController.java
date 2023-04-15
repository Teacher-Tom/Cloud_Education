package xyz.likailing.cloud.service.manager.controller;


import com.alibaba.fastjson.JSON;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.base.model.File;
import xyz.likailing.cloud.service.manager.entity.CourseResource;
import xyz.likailing.cloud.service.manager.feign.AllsService;
import xyz.likailing.cloud.service.manager.mapper.CourseResourceMapper;
import xyz.likailing.cloud.service.manager.service.CourseResourceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author derek
 * @since 2023-03-20
 */
@RestController
@RequestMapping("/manager/course-resource")
@CrossOrigin
public class CourseResourceController {

    @Autowired
    private AllsService allsService;

    @Autowired
    private CourseResourceService courseResourceService;

    @ApiOperation("上传图片并返回url")
    @PostMapping("upload-pic/{courseId}")
    public R uploadPicture(@PathVariable String courseId,@RequestParam(value = "file") @RequestPart MultipartFile file){
        String memid = courseId;
        String catalogue = "/root";
        R upload = allsService.upload(file, catalogue, memid);
        Map<String, Object> data = upload.getData();
        File file1 = JSON.parseObject(JSON.toJSONString(data.get("userInfo")),File.class);
        R r = allsService.addFile(file1);
        file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")),File.class);
        CourseResource courseResource = new CourseResource();
        courseResource.setCourseId(courseId);
        courseResource.setFileId(file1.getId());
        courseResource.setGlobal(true);
        courseResourceService.save(courseResource);
        return R.ok().data("resource",courseResource).data("file",file1);
    }

    @ApiOperation("根据资源id删除文件")
    @DeleteMapping("delete/{courseId}/{resourceId}")
    public R deleteResouce(@PathVariable String courseId, @PathVariable String resourceId){
        CourseResource resource = courseResourceService.getById(resourceId);
        if (Objects.isNull(resource)){
            throw new CloudException("没有找到该资源",20001);
        }
        boolean b = courseResourceService.removeById(resourceId);
        if (!b){
            return R.error().message("删除失败");
        }
        String[] idList = {resource.getFileId()};
        R remove = allsService.removeAlyVideo(idList,courseId);
        return remove;
    }

    @ApiOperation("上传课程全局资源，当上传ppt时，会自动转换为pdf，并同时上传ppt和pdf")
    @PostMapping("upload-global/{courseId}")
    public R uploadGlobalResource(@PathVariable String courseId, @RequestParam(value = "file") @RequestPart MultipartFile file){
        String memid = courseId;
        String catalogue = "/root";
        R upload = allsService.upload(file, catalogue, memid);
        Map<String, Object> data = upload.getData();
        if (data.containsKey("file_list")){
            List<File> files = JSON.parseArray(JSON.toJSONString(data.get("file_list")), File.class);
            List<CourseResource> resources = new ArrayList<>();
            for (File file1 : files) {
                R r = allsService.addFile(file1);
                file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")),File.class);
                CourseResource courseResource = new CourseResource();
                courseResource.setCourseId(courseId);
                courseResource.setFileId(file1.getId());
                courseResource.setGlobal(true);

                courseResourceService.save(courseResource);
                resources.add(courseResource);
            }
            return R.ok().data("resources",resources).data("files",files);
        } else if (data.containsKey("file")) {
            File file1 = JSON.parseObject(JSON.toJSONString(data.get("file")),File.class);
            file1.setMemId(memid);
            file1.setFDir(catalogue);
            R r = allsService.addFile(file1);
            file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")),File.class);
            CourseResource courseResource = new CourseResource();
            courseResource.setCourseId(courseId);
            courseResource.setFileId(file1.getId());
            courseResource.setGlobal(true);
            courseResourceService.save(courseResource);
            return R.ok().data("resource",courseResource).data("file",file1);
        }
        return R.error().message("上传文件失败");
    }
    @ApiOperation("小节上传ppt资源，可以是ppt，pptx，pdf格式")
    @PostMapping("upload-timetable/ppt/{courseId}/{timetableId}")
    public R uploadTimetablePPT(@PathVariable String courseId, @PathVariable String timetableId, @RequestParam(value = "file") @RequestPart MultipartFile file){
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件类型
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        fileType = fileType.substring(1);
        if (!"ppt".equals(fileType) && !"pptx".equals(fileType) && !"pdf".equals(fileType)){
            throw new CloudException("文件不是ppt或pdf",20001);
        }
        String memid = courseId;
        String catalogue = "/root/timetable";
        R upload = allsService.upload(file, catalogue, memid);
        Map<String, Object> data = upload.getData();
        if (data.containsKey("file_list")){
            List<File> files = JSON.parseArray(JSON.toJSONString(data.get("file_list")), File.class);
            List<CourseResource> resources = new ArrayList<>();
            for (File file1 : files) {
                R r = allsService.addFile(file1);
                file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")),File.class);
                CourseResource courseResource = new CourseResource();
                courseResource.setCourseId(courseId);
                courseResource.setFileId(file1.getId());
                courseResource.setGlobal(false);
                courseResource.setTimetableId(timetableId);
                courseResource.setType(0);
                courseResourceService.save(courseResource);
                resources.add(courseResource);
            }
            return R.ok().data("resources",resources).data("files",files);
        } else if (data.containsKey("file")) {
            File file1 = JSON.parseObject(JSON.toJSONString(data.get("file")),File.class);
            file1.setMemId(memid);
            file1.setFDir(catalogue);
            R r = allsService.addFile(file1);
            file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")),File.class);
            CourseResource courseResource = new CourseResource();
            courseResource.setCourseId(courseId);
            courseResource.setFileId(file1.getId());
            courseResource.setGlobal(false);
            courseResource.setTimetableId(timetableId);
            courseResource.setType(0);
            courseResourceService.save(courseResource);
            return R.ok().data("resource",courseResource).data("file",file1);
        }
        return R.error().message("上传文件失败");
    }



    @ApiOperation("小节上传视频资源")
    @PostMapping("upload-timetable/video/{courseId}/{timetableId}")
    public R uploadTimetableVideo(@PathVariable String courseId, @PathVariable String timetableId, @RequestParam(value = "file") @RequestPart MultipartFile file){
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件类型
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        String type = fileType.substring(1);
        if (StringUtils.isEmpty(type)){
            throw new CloudException("未知格式的文件",20001);
        }
        if (!(type.equals("avi") || type.equals("mov") || type.equals("wmv") || type.equals("rmvb") || type.equals("rm") || type.equals("mp4") || type.equals("3gp") || type.equals("flv"))) {
            throw new CloudException("文件不是视频格式",20001);
        }
        String memid = courseId;
        String catalogue = "/root/timetable";
        R upload = allsService.upload(file, catalogue, memid);
        Map<String, Object> data = upload.getData();
        if (data.containsKey("file")) {
            File file1 = JSON.parseObject(JSON.toJSONString(data.get("file")),File.class);
            file1.setMemId(memid);
            file1.setFDir(catalogue);
            R r = allsService.addFile(file1);
            file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")),File.class);
            CourseResource courseResource = new CourseResource();
            courseResource.setCourseId(courseId);
            courseResource.setFileId(file1.getId());
            courseResource.setGlobal(false);
            courseResource.setTimetableId(timetableId);
            courseResource.setType(1);
            courseResourceService.save(courseResource);
            String videoId = file1.getVideoId();
            List<String> vids = new ArrayList<>();
            vids.add(videoId);
            R playAuth = allsService.getPlayAuth(vids);
            Object urls = playAuth.getData().get("urlList");
            return R.ok().data("resource",courseResource).data("file",file1).data("urlList",urls);
        }
        return R.error().message("上传文件失败");
    }



    @ApiOperation("小节上传共享资源")
    @PostMapping("upload-timetable/share/{courseId}/{timetableId}")
    public R uploadTimetableShare(@PathVariable String courseId, @PathVariable String timetableId, @RequestParam(value = "file") @RequestPart MultipartFile file){
        String memid = courseId;
        String catalogue = "/root/timetable";
        R upload = allsService.upload(file, catalogue, memid);
        Map<String, Object> data = upload.getData();
        if (data.containsKey("file_list")){
            List<File> files = JSON.parseArray(JSON.toJSONString(data.get("file_list")), File.class);
            List<CourseResource> resources = new ArrayList<>();
            for (File file1 : files) {
                R r = allsService.addFile(file1);
                file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")),File.class);
                CourseResource courseResource = new CourseResource();
                courseResource.setCourseId(courseId);
                courseResource.setFileId(file1.getId());
                courseResource.setGlobal(false);
                courseResource.setTimetableId(timetableId);
                courseResource.setType(2);
                courseResourceService.save(courseResource);
                resources.add(courseResource);
            }
            return R.ok().data("resources",resources).data("files",files);
        } else if (data.containsKey("file")) {
            File file1 = JSON.parseObject(JSON.toJSONString(data.get("file")),File.class);
            file1.setMemId(memid);
            file1.setFDir(catalogue);
            R r = allsService.addFile(file1);
            file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")),File.class);
            CourseResource courseResource = new CourseResource();
            courseResource.setCourseId(courseId);
            courseResource.setFileId(file1.getId());
            courseResource.setGlobal(false);
            courseResource.setTimetableId(timetableId);
            courseResource.setType(2);
            courseResourceService.save(courseResource);
            return R.ok().data("resource",courseResource).data("file",file1);
        }
        return R.error().message("上传文件失败");
    }

    @ApiOperation("小节上传共享资源-链接")
    @PostMapping("upload-timetable/share/link/{courseId}/{timetableId}")
    public R uploadTimetableShareLink(@PathVariable String courseId, @PathVariable String timetableId, @RequestParam String url, @RequestParam String filename ){
        String memid = courseId;
        String catalogue = "/root/timetable/link";
        File file1 = new File();
        file1.setFDir(catalogue);
        file1.setFiletype("link");
        file1.setMemId(memid);
        file1.setName(filename);
        file1.setUrl(url);
        file1.setCollection(0);
        file1.setSize(0);
        file1.setMemId(memid);
        file1.setFDir(catalogue);
        R r = allsService.addFile(file1);
        file1 = JSON.parseObject(JSON.toJSONString(r.getData().get("file")),File.class);
        CourseResource courseResource = new CourseResource();
        courseResource.setCourseId(courseId);
        courseResource.setFileId(file1.getId());
        courseResource.setGlobal(false);
        courseResource.setTimetableId(timetableId);
        courseResource.setType(2);
        courseResourceService.save(courseResource);
        return R.ok().data("resource",courseResource).data("file",file1);
    }

    @ApiOperation("获取小节的ppt")
    @GetMapping("get-timetable/ppt/{timetableId}")
    public R getTimetablePPT(@PathVariable String timetableId){
        List<File> files = courseResourceService.getPPTByTimetableId(timetableId);
        List<CourseResource> resources = courseResourceService.getPPTResourceByTimetableId(timetableId);
        return R.ok().data("resource",resources).data("files",files);
    }

    @ApiOperation("获取小节的视频地址")
    @GetMapping("get-timetable/video/{timetableId}")
    public R getTimetableVideo(@PathVariable String timetableId){
        List<String> urls = courseResourceService.getVideoUrlByTimetableId(timetableId);
        List<CourseResource> resource = courseResourceService.getVideoResourceByTimetableId(timetableId);
        return R.ok().data("urls",urls).data("resources",resource);
    }

    @ApiOperation("获取小节的共享资源")
    @GetMapping("get-timetable/share/{timetableId}")
    public R getTimetableShare(@PathVariable String timetableId){
        List<File> files = courseResourceService.getSharedFilesByTimetableId(timetableId);
        List<CourseResource> resources = courseResourceService.getSharedFilesResourceByTimetableId(timetableId);
        return R.ok().data("resources",resources).data("files",files);
    }


}

