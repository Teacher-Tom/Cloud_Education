package xyz.likailing.cloud.service.manager.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static boolean d1BeforeD2(Date d1, Date d2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(d1);
        calendar1.set(Calendar.HOUR_OF_DAY, 0);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        calendar1.set(Calendar.MILLISECOND, 0);

        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(d2);
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);

        return calendar1.getTime().before(calendar2.getTime());
//        int i10 = calendar1.get(Calendar.ERA);
//        int i20 = calendar2.get(Calendar.ERA);
//        int i11 = calendar1.get(Calendar.YEAR);
//        int i21 = calendar2.get(Calendar.YEAR);
//        int i16 = calendar1.get(Calendar.DAY_OF_YEAR);
//        int i26 = calendar2.get(Calendar.DAY_OF_YEAR);
//        if (i10 < i20) {
//            return true;
//        } else if (i10 == i20) {
//            if (i11 < i21) {
//                return true;
//            } else if (i11 == i21) {
//                return i16 < i26;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
    }
}
