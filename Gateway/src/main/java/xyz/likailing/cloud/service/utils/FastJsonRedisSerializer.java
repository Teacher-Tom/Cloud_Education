package xyz.likailing.cloud.service.utils;

/**
 * @Author 12042
 * @create 2023/3/18 21:49
 */
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * com.avatar.redis
 * Description: 设置redis使用fastJson序列化
 *
 * @author jack
 * @date 2020/11/22 14:36
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;

    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        // byte[] bytes = JSON.toJSONString(t, SerializerFeature.WriteClassName, ).getBytes(DEFAULT_CHARSET);
        byte[] bytes = JSON.toJSONString(t, SerializerFeature.WriteClassName,
                // 是否输出值为null的字段,默认为false
                SerializerFeature.WriteMapNullValue,
                // List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty).getBytes(DEFAULT_CHARSET);
        return bytes;
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return (T) JSON.parseObject(str, clazz);
    }
}
