package xyz.likailing.cloud.service.manager;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
}
