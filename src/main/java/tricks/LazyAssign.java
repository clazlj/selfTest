package tricks;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * 延迟赋值
 */
public class LazyAssign {
    public static void main(String[] args) {
        System.out.println("请输入内容");
        Scanner scanner = new Scanner(System.in);
        String content;
        //content不满足条件length不用赋值
        int length;
        if (StringUtils.isNotBlank(content = scanner.next())
                && (length = content.length()) > 5) {
            System.out.println("长度>5，长为：" + length);
        }

        scanner.close();
    }
}
