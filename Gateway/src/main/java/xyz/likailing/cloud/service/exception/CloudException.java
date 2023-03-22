package xyz.likailing.cloud.service.exception;

import lombok.Data;
import xyz.likailing.cloud.service.result.ResultCodeEnum;

/**
 * @Author 12042
 * @create 2023/2/1 15:39
 */
@Data
public class CloudException extends RuntimeException{
    private Integer code;

    public CloudException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public CloudException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "CloudException{" +
                "code=" + code +
                ",message=" + this.getMessage() +
                '}';
    }
}
