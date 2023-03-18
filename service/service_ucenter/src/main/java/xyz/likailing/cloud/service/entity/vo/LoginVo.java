package xyz.likailing.cloud.service.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author 12042
 * @create 2023/3/17 22:36
 */
@Data
@AllArgsConstructor
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
}
