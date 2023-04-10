package xyz.likailing.cloud.service.exp.entity;

import lombok.Data;

/**
 * @Author 12042
 * @create 2023/4/9 23:30
 */
@Data
public class WebSSHData {
    //操作
    private String operate;
    private String host;
    //端口号默认为22
    private Integer port = 22;
    private String username;
    private String password;
    private String command = "";

}
