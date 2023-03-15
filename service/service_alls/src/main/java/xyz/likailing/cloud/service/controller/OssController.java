package xyz.likailing.cloud.service.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import xyz.likailing.cloud.service.entity.File;
import xyz.likailing.cloud.service.entity.UcenterMember;
import xyz.likailing.cloud.service.excepyionhandler.SpaceException;
import xyz.likailing.cloud.service.service.FileService;
import xyz.likailing.cloud.service.service.OssService;
import xyz.likailing.cloud.service.service.UcenterMemberService;
import xyz.likailing.cloud.service.utils.ConstanPropertiesUtils;
import xyz.likailing.cloud.service.utils.InitVodCilent;
import xyz.likailing.cloud.service.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
//@CrossOrigin
public class OssController {
    @Autowired
    private OssService ossService;
    @Autowired
    private FileService fileService;

    @Autowired
    private UcenterMemberService memberService;

    //上传头像
    @ApiOperation(value = "根据用户id上传头像")
    @PostMapping("uploadFileAvatar")
    public R uploadOssFile(MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url", url);
    }

    //判断上传的文件类型
    @ApiOperation(value = "上传文件")
    @PostMapping("upload/{memid}")
    public R upload(MultipartFile file, @RequestParam String catalogue, @PathVariable String memid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("id", memid);
        UcenterMember one = memberService.getOne(wrapper);
        long neicun = one.getNeicun();
        long size = file.getSize();
        long result=neicun+ size;
        if (result<1073741824){
            UcenterMember member=new UcenterMember();
            member.setNeicun(result);
            member.setId(memid);
            boolean b = memberService.updateById(member);
            System.out.println(b);
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //获取文件类型
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            String type = fileType.substring(1);
            if (type.equals("mp3") || type.equals("mpeg") || type.equals("vma") || type.equals("aac") || type.equals("ra") || type.equals("am") || type.equals("rmx") || type.equals("mp3")
                    || type.equals("avi") || type.equals("mov") || type.equals("rmvb") || type.equals("rm") || type.equals("mp4") || type.equals("3gp") || type.equals("flv") || type.equals("ape") || type.equals("flac") || type.equals("wmv")) {
                //获取文件名称
                String title = fileName.substring(0, fileName.lastIndexOf("."));
                File file1 = new File();
                file1.setSize(size);
                if (type.equals("mp3") || type.equals("mpeg") || type.equals("vma") || type.equals("aac") || type.equals("ra") || type.equals("am") || type.equals("rmx")
                        || type.equals("ape") || type.equals("flac")) {
                    file1.setFiletype("audio");
                }
                if (type.equals("avi") || type.equals("mov") || type.equals("wmv") || type.equals("rmvb") || type.equals("rm") || type.equals("mp4") || type.equals("3gp") || type.equals("flv")) {
                    file1.setFiletype("video");
                }
                file1.setName(title);
                file1.setType(type);
                file1.setFDir(catalogue);
                String videoId = ossService.uploadfile(file);
                file1.setVideoId(videoId);
                return R.ok().data("file", file1);
            } else {
                File file1 = ossService.upload(file, catalogue);

                if (file1.equals("")) {
                    return R.error();
                }
                return R.ok().data("file", file1);
            }
        }else {
            throw new SpaceException(20001,"内存溢出");
        }

    }

    @ApiOperation(value = "根据文件id删除文件")
    //根据文件id删除阿里云视频
    @DeleteMapping("removeAlyVideo/{memid}")
    public R removeAlyVideo(@RequestParam("idList") String[] idList,@PathVariable String memid) {
        QueryWrapper<UcenterMember> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id", memid);
        UcenterMember one = memberService.getOne(wrapper1);
        boolean flag = false;
        for (int i = 0; i < idList.length; i++) {
            //System.out.println(id);
            QueryWrapper<File> wrapper = new QueryWrapper<>();
            wrapper.eq("id", idList[i]);
            File file = fileService.getFiles(idList[i]);
            long result=one.getNeicun()-file.getSize();
            UcenterMember member=new UcenterMember();
            member.setId(memid);
            member.setNeicun(result);
            memberService.updateById(member);
            //System.out.println(file);
            if (file.getFiletype().equals("video") || file.getFiletype().equals("audio")) {
                System.out.println(file.getVideoId());
                String videoId = ossService.deleteVideo(idList[i]);
                String video = video(videoId);
                if (video.equals("success")) {
                    flag = true;
                }
            } else {
                //System.out.println("晚间222222");
                String status = ossService.delete(idList[i]);
                if (status.equals("error")) {
                    flag = false;
                } else {
                    flag = true;
                }
            }
        }
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    public static String video(String videoId) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(ConstanPropertiesUtils.ACCESS_KEY_ID, ConstanPropertiesUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(videoId);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            throw new SpaceException(20001, "删除视频失败");
        }
    }

    //根据视频id获取视频凭证
    @ApiOperation(value = "根据视频vodioId获取播放地址")
    @PostMapping("getPlayAuth")
    public R getPlayAuth(@RequestParam("isList") List<String> isList) {
        String accessKeyId = ConstanPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstanPropertiesUtils.ACCESS_KEY_SECRET;
        //List<String>isList =Arrays.asList(list);
        System.out.println(isList);

        ArrayList urlList = new ArrayList();
        File file = new File();
        // 创建SubmitMediaInfoJob实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-Shanghai",                // // 点播服务所在的地域ID，中国大陆地域请填cn-shanghai
                accessKeyId,        // 您的AccessKey ID
                accessKeySecret);    // 您的AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        // 视频ID。
        for (int i = 0; i < isList.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            // System.out.println(isList.get(i));
            file.setVideoId(isList.get(i));
            map.put("videoId", isList.get(i));
            request.setVideoId(isList.get(i));
            // request.setVideoId(id);
            try {
                GetPlayInfoResponse response = client.getAcsResponse(request);
                // System.out.println(response.getPlayInfoList().get(3));
//                response.getPlayInfoList().
                //response.getVideoBase().getCoverURL();
                //System.out.println(new Gson().toJson(response));
                for (GetPlayInfoResponse.PlayInfo playInfo : response.getPlayInfoList()) {
                    // 播放地址
                    System.out.println("PlayInfo.PlayURL = " + playInfo.getPlayURL());
                    file.setUrl(playInfo.getPlayURL());
                    map.put("url", playInfo.getPlayURL());
                    urlList.add(map);
                    System.out.println(urlList);
                    //System.out.println(map);
                    //urlList.add(playInfo.getPlayURL());
                    request.setVideoId(null);
                }
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                System.out.println("ErrCode:" + e.getErrCode());
                System.out.println("ErrMsg:" + e.getErrMsg());
                System.out.println("RequestId:" + e.getRequestId());
            }
            //urlList.add(map);
            //map.clear();
            //list.add(file);
            //System.out.println(urlList);
        }
        return R.ok().data("urlList", urlList);
    }


}
