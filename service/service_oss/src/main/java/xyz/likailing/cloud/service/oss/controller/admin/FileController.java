package xyz.likailing.cloud.service.oss.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.common.base.result.ResultCodeEnum;
import xyz.likailing.cloud.common.base.util.ExceptionUtils;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.oss.service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @Author 12042
 * @create 2023/2/1 13:20
 */
@Api(tags = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {
    @Autowired
    private FileService fileService;
    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(@ApiParam(value = "文件",required = true) @RequestPart("file") MultipartFile file,
                    @ApiParam(value = "文件夹",required = true) @RequestParam("module") String module) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String url = fileService.upload(inputStream, module, originalFilename);
            return R.ok().message("文件上传成功").data("url",url);
        } catch (Exception e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new CloudException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }
    @ApiOperation(value = "文件删除")
    @DeleteMapping("remove")
    public R removeFile(
            @ApiParam(value = "文件url",required = true)
            @RequestParam String url){
        fileService.removeFile(url);
        return R.ok().message("文件删除成功");
    }

    @ApiOperation(value = "调试")
    @GetMapping("test")
    public R test(){
        try{
            TimeUnit.SECONDS.sleep(3);

        }catch (InterruptedException e){
            e.printStackTrace();
        }
         log.info("oss test被调用");
         return R.ok();
    }

}
