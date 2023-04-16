package xyz.likailing.cloud.service.exp.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.base.model.File;

import java.util.List;

/**
 * @Author 12042
 * @create 2023/3/23 16:19
 */
@FeignClient(value = "service-alls")
@Component
public interface AllsService {
    /**
     * 上传文件
     * 
 * @param file
 * @param catalogue
 * @param memid
     * @return xyz.likailing.cloud.common.base.result.R
     * @author likailing
     * @create 2023/3/23
     **/
    
    @PostMapping(value = "/eduoss/fileoss/upload/{memid}",consumes = "multipart/form-data")
    public R upload(MultipartFile file, @RequestParam String catalogue, @PathVariable String memid) ;

    //根据文件id删除阿里云视频
    @DeleteMapping("/eduoss/fileoss/removeAlyVideo/{memid}")
    public R removeAlyVideo(@RequestParam("idList") String[] idList,@PathVariable String memid) ;

    //根据视频id获取视频凭证
    @PostMapping("/eduoss/fileoss/getPlayAuth")
    public R getPlayAuth(@RequestParam("isList") List<String> isList) ;

    @PostMapping("/educenter/file/addFile")
    public R addFile(@RequestBody File file);

    @PostMapping("/educenter/member/create-course")
    public R createCourseAccount(@RequestParam String courseId,@RequestParam String courseName,@RequestParam String coverUrl);

    @GetMapping("/educenter/file/getFileInfo/{id}")
    public R getfileInfo(@PathVariable String id) ;



}
