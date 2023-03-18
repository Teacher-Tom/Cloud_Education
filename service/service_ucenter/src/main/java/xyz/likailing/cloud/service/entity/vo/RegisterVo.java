package xyz.likailing.cloud.service.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author helen
 * @since 2020/4/29
 */
@Data
public class RegisterVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nickname;
    private String username;
    private String password;
    private int role;
    private String code;
}