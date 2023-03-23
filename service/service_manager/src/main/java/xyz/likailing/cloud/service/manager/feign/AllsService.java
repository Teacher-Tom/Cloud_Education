package xyz.likailing.cloud.service.manager.feign;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import xyz.likailing.cloud.common.base.result.R;

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
    
    @PostMapping("/eduoss/fileoss/upload/{memid}")
    public R upload(MultipartFile file, @RequestParam String catalogue, @PathVariable String memid) ;

    //根据文件id删除阿里云视频
    @DeleteMapping("/eduoss/fileoss/removeAlyVideo/{memid}")
    public R removeAlyVideo(@RequestParam("idList") String[] idList,@PathVariable String memid) ;

    //根据视频id获取视频凭证
    @PostMapping("/eduoss/fileoss/getPlayAuth")
    public R getPlayAuth(@RequestParam("isList") List<String> isList) ;


}
