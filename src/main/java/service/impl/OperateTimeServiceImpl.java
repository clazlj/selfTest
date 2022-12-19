package service.impl;

import service.OperateTimeService;

import java.time.*;
import java.time.temporal.*;
import java.util.Date;

/**
 * Created by jdd on 2017/9/5.
 */
public class OperateTimeServiceImpl implements OperateTimeService {
    public static void main(String[] args) {
        predefinedTemporalAdjuster();

        customizedTemporalAdjuster();
    }

    /**
     * @see TemporalAdjusters 常用的获取java.time.temporal.TemporalAdjuster的工具类
     * 预定义的java.time.temporal.TemporalAdjuster
     */
    private static void predefinedTemporalAdjuster() {
        LocalDate date1 = LocalDate.of(2022, 12, 19);
        LocalDate date2 = date1.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        LocalDate date3 = date2.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate date4 = date1.with(TemporalAdjusters.firstDayOfYear());

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(date4);
    }

    /**
     * @see TemporalAdjuster 函数式接口，调整时间对象的策略。
     * 自定义java.time.temporal.TemporalAdjuster
     */
    private static void customizedTemporalAdjuster() {
        LocalDate date1 = LocalDate.now();
        //获取下一个工作日
        LocalDate date2 = date1.with(nextWorkingDay());

        System.out.println(date1);
        System.out.println(date2);
    }

    /**
     * 周五加3天，周六加2天，其余加1天
     */
    private static TemporalAdjuster nextWorkingDay() {
        return temporal -> {
            int dayOfWeek = temporal.get(ChronoField.DAY_OF_WEEK);
            DayOfWeek dow = DayOfWeek.of(dayOfWeek);
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }
            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        };
    }


    @Override
    public void learnDate() {
        Long lastMillis = System.currentTimeMillis();
        Date now = new Date(lastMillis);
        Date nowTime = new Date();

        //日期(不含时分秒)
        LocalDate today = LocalDate.now();
        System.out.println("今天的日期是:" + today);
        System.out.println("年：" + today.getYear() + ",月:" + today.getMonthValue() + ",日:" + today.getDayOfMonth());
        System.out.println("年：" + today.get(ChronoField.YEAR) + ",月:" + today.get(ChronoField.MONTH_OF_YEAR) + ",日:" + today.get(ChronoField.DAY_OF_MONTH));

        //日期加减
        System.out.println("一周后的日期是：" + today.plus(1, ChronoUnit.WEEKS));
        System.out.println("一年前的日期是：" + today.minus(1, ChronoUnit.YEARS));
        //特定日期
        LocalDate dateOfBirth = LocalDate.of(1989, 4, 16);
        dateOfBirth = LocalDate.parse("1989-04-16");    //1989-4-16报异常
        System.out.println("您的生日是:" + dateOfBirth);
        //日期比较
        System.out.println("今天是我的生日吗？" + today.equals(dateOfBirth));
        System.out.println("我的日期比今天小吗？" + dateOfBirth.isBefore(today));
        Period periodTime = Period.between(dateOfBirth, today);
        System.out.println("我的出生日期是" + periodTime.getYears() + "年" + periodTime.getMonths() + "月," + periodTime.getDays() + "天前");

        //时间(不含日期)
        LocalTime localTime = LocalTime.now();
        System.out.println("当前时间为:" + localTime + "小时：" + localTime.getHour() + "分：" + localTime.getMinute() + "秒：" + localTime.getMinute());
        //时间加减
        System.out.println("2个小时之后的时间是：" + localTime.plusHours(2));


        //时间(日期+时分秒)
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("当前日期时间为:" + localDateTime);
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        Date dateTime = Date.from(zdt.toInstant());
        System.out.println("转换成Date类型时间为：" + dateTime);

        //时间戳
        System.out.println("当前的时间戳是:" + Instant.now());
    }
}
