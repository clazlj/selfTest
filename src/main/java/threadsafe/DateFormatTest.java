package threadsafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateFormat中定义了一个protected属性的 Calendar类的对象，且calendar这个成员变量既被用于format方法也被用于parse方法
 *
 * parse()很容易复现
 *
 * format()比较难复现【线程1调用format方法，改变了calendar这个字段。中断来了。线程2开始执行，它也改变了calendar。
 * 又中断了。线程1回来了，此时，calendar已然不是它所设的值，而是走上了线程2设计的道路。
 * 如果多个线程同时争抢calendar对象，则会出现各种问题，时间不对，线程挂死等等。】
 *
 * @author cl
 */
public class DateFormatTest {
    public static void main(String[] args) {
        Date now = new Date();
        String dateStr = "2020-01-28 11:40:45";

        for(int i = 0; i < 5; i++){
            new Thread(()->{
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + DateUtil.formatDate(now));

                    System.out.println(Thread.currentThread().getName() + ":" + DateUtil.parse(dateStr));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(Date date) {
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException{
        return sdf.parse(strDate);
    }
}
