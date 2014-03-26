package cn.lichengwu.test.jdk8;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author 佐井
 * @version 1.0
 * @created 2014-03-21 下午9:01
 */
public class DateTimeAPI {


    @Test
    public void testClock() {
        Clock shanghai = Clock.systemDefaultZone();
        Clock tokyo = Clock.system(ZoneId.of("Asia/Tokyo"));
        System.out.println(shanghai.millis());
        System.out.println(tokyo.millis());
        System.out.println(tokyo.getZone());
        System.out.println(shanghai.getZone());
        Instant instant = shanghai.instant();
        Date date = Date.from(instant);
        System.out.println(date);
    }

    @Test
    public void testZoneId() {
        ZoneId shanghai = ZoneId.systemDefault();
        ZoneId tokyo = ZoneId.of("Asia/Tokyo");
        System.out.println(shanghai.getRules());
        System.out.println(tokyo.getRules());
        Clock myClock = Clock.system(shanghai);
        System.out.println(Date.from(myClock.instant()));
        System.out.println(myClock.instant().atZone(tokyo));
    }

    @Test
    public void testLocalTime() {
        LocalTime shanghai = LocalTime.now();
        LocalTime tokyo = LocalTime.now(ZoneId.of("Asia/Tokyo"));
        System.out.println(shanghai); //21:38:39.960
        System.out.println(tokyo);  //22:38:39.961
        System.out.println(shanghai.isBefore(tokyo));   //true
        System.out.println(ChronoUnit.HOURS.between(shanghai, tokyo));   // 1
        System.out.println(ChronoUnit.MINUTES.between(shanghai, tokyo)); // 60
    }

    @Test
    public void testLocalDate() {
        LocalDate now = LocalDate.now();
        LocalDate tomorrow = now.plus(1, ChronoUnit.DAYS);
        System.out.println(tomorrow); //2014-03-22
        LocalDate yesterday = now.minus(1, ChronoUnit.DAYS);
        System.out.println(yesterday); //2014-03-20
        System.out.println(now.getYear()); ///2014
        System.out.println(now.getMonth()); //MARCH
        System.out.println(now.getDayOfWeek()); //FRIDAY
    }

    @Test
    public void testLocalDateTime() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now); //2014-03-21T22:00:46.894
        LocalDateTime fest = LocalDateTime.of(2014, Month.DECEMBER, 1, 23, 59, 59);
        System.out.println(fest); //2014-12-01T23:59:59
        System.out.println(fest.isAfter(fest)); //false
    }

    @Test
    public void testDateTimeFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDate fest = LocalDate.parse("2014-10-01 23:59:59", formatter);
        System.out.println(fest);
    }
}
