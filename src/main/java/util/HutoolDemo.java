package util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.crypto.SecureUtil;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author cl
 */
public class HutoolDemo {
    public static void main(String[] args) {
        part2();
    }

    private static void part1() {
        String str = "abc";
        String md5 = SecureUtil.md5(str);
        System.out.println("md5:" + md5);

        long[] b = {1, 2, 3, 4, 5};
        String bStr = Convert.toStr(b);
        System.out.println("bStr:" + bStr);

        String[] strArr = {"1", "2", "3", "4"};
        Integer[] intArray = Convert.toIntArray(strArr);

        String a = "123456789";

        //结果为："１２３４５６７８９"
        String sbc = Convert.toSBC(a);
        System.out.println("sbc:" + sbc);

        //转换为大写只能精确到分（小数点儿后两位），之后的数字会被忽略
        double money = 67556.32;
        String digitUppercase = Convert.digitToChinese(money);
        System.out.println("digitUppercase:" + digitUppercase);

        String dateStr = "2024-11-05 22:33:23";
        Date date = DateUtil.parse(dateStr);

        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);

        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);
    }

    private static void part2() {
        LocalDateTime localDateTime = LocalDateTimeUtil.beginOfDay(LocalDateTime.now());

        LocalDateTime start = LocalDateTimeUtil.parse("2019-02-02T00:00:00");
        LocalDateTime end = LocalDateTimeUtil.parse("2020-02-02T12:00:00");

        //Duration.between()
        Duration between = LocalDateTimeUtil.between(start, end);
        // 365
        long days = between.toDays();
    }
}
