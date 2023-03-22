package xyz.likailing.cloud.service.manager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.likailing.cloud.common.base.util.RedisCache;
import xyz.likailing.cloud.service.manager.entity.Timetable;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisCache redisCache;

    @Test
    public void test() {
//        boolean hello = redisCache.deleteObject("hello");
//        System.out.println(hello);
//        ArrayList<Timetable> timetables = new ArrayList<>();
//        Timetable timetable1 = new Timetable(); timetable1.setCourseId("111111");
//        Timetable timetable2 = new Timetable(); timetable2.setCourseId("111111"); timetable2.setBeginIndex(2);
//        Timetable timetable3 = new Timetable(); timetable3.setCourseId("333333"); timetable3.setWeek(5);
//        Timetable timetable4 = new Timetable(); timetable4.setCourseId("111111");
//        Timetable timetable5 = new Timetable(); timetable5.setCourseId("444444"); timetable5.setLocation("llsjdns");
//        Timetable timetable6 = new Timetable(); timetable6.setCourseId("111111");
//        timetables.add(timetable1);
//        timetables.add(timetable2);
//        timetables.add(timetable3);
//        timetables.add(timetable4);
//        timetables.add(timetable5);
//        timetables.add(timetable6);
//        redisCache.setCacheList("tempList", timetables);
//        List<Object> tempList = redisCache.getCacheList("tempList");
//        for (Object o : tempList) {
//            Timetable o1 = (Timetable) o;
//            System.out.println(o1);
//        }
//        System.out.println(redisCache.deleteObject("tempList"));
    }
}
