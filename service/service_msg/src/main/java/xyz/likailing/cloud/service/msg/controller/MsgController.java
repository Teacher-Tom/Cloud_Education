package xyz.likailing.cloud.service.msg.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import xyz.likailing.cloud.common.base.result.R;
import xyz.likailing.cloud.service.base.exception.CloudException;
import xyz.likailing.cloud.service.msg.service.WebSocketServer;

import java.io.IOException;

/**
 * @Author 12042
 * @create 2023/3/20 18:58
 */
@CrossOrigin
@Api(description = "消息管理")
@RestController
@RequestMapping("/api/msg")
@Slf4j
public class MsgController {


    @GetMapping("/index/{username}")
    public R socket(@PathVariable String username){
        return R.ok().message(username);
    }

    @ApiOperation("推送消息")
    @ResponseBody
    @RequestMapping("/push/{username}")
    public R pushToWeb(@PathVariable String username, String message){
        try{
            WebSocketServer.sendInfo(message,username);
        }catch (IOException e){
            e.printStackTrace();
            throw new CloudException("消息通信异常",20001);
        }
        return R.ok().message("发送消息成功:"+username);
    }
}
