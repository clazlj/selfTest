package demo;

/**
 * Java中 char 类型的系统默认值是空 ， \u0000 代表字符为空 转换为int类型表示 0 。
 */
public class CharDemo {
    private static char myChar;

    public static void main(String[] args) {
        System.out.println(myChar);
        System.out.println("----\u0000----");

        System.out.println(myChar == ' ');

        //空字符，对应int的0
        System.out.println(myChar == '\u0000');
        System.out.println(myChar == 0);
    }
}
