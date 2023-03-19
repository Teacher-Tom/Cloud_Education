package xyz.likailing.cloud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.likailing.cloud.service.mapper.AuthorityMapper;

import java.util.List;

/**
 * @Author 12042
 * @create 2023/3/18 16:26
 */
@SpringBootTest
public class Test {
    @Autowired
    private AuthorityMapper authorityMapper;
    @org.junit.jupiter.api.Test
    public void testSelectParamByUserId(){
        List<String> strings = authorityMapper.selectParamsByUserId("1637036809054887937");
        System.out.println(strings);
    }
}
