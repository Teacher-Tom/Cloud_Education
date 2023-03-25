package xyz.likailing.cloud.service.manager.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.base.model.File;
import xyz.likailing.cloud.service.manager.feign.AllsService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
public class CourseResourceController {

    @Autowired
    AllsService allsService;
    @ApiOperation("上传课程全局资源")
    @PostMapping("upload-global/{courseId}")
    public R uploadGlobalResource(@PathVariable String courseId, @RequestParam(value = "file") @RequestPart MultipartFile file){
        String memid = courseId;
        String catalogue = "/root";
        R upload = allsService.upload(file, catalogue, memid);
        Map<String, Object> data = upload.getData();
        if (data.containsKey("file_list")){
            List<File> files = (List<File>)(data.get("file_list"));
            for (File file1 : files) {
                R r = allsService.addFile(file1);
                return r;
            }

        } else if (data.containsKey("file")) {
            File file1 = JSON.parseObject(JSON.toJSONString(data.get("file")),File.class);
            file1.setMemId(memid);
            file1.setFDir(catalogue);
            R r = allsService.addFile(file1);
            return r;
        }
        return R.error().message("上传文件失败");
    }
}

