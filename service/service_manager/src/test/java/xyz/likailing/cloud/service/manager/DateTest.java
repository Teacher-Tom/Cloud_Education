package xyz.likailing.cloud.service.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import xyz.likailing.cloud.service.manager.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class DateTest {

    @Test
    public void test() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(simpleDateFormat.format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
//        calendar.add(Calendar.WEEK_OF_YEAR, 1);
//        System.out.println(simpleDateFormat.format(calendar.getTime()));

        int beginWeek = 1, dayOfWeek = 3;

        calendar.add(Calendar.WEEK_OF_YEAR, beginWeek - 2);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        calendar.add(Calendar.DAY_OF_WEEK, dayOfWeek - 6);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }

    @Test
    public void dateUtilsTest() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(simpleDateFormat.format(calendar.getTime()));

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(now);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        System.out.println(simpleDateFormat.format(cal1.getTime()));

        System.out.println(DateUtils.d1BeforeD2(calendar.getTime(), cal1.getTime()));
    }

}
