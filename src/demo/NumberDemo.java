package demo;

public class NumberDemo {
    public static void main(String[] args) {
        readableNumber();
    }

    /**
     * 提高可读性
     */
    public static void readableNumber() {
        //编译期间，编译器把这些下划线移除，并把真实的数字赋值给变量。
        long number = 1_000_000_000L;
        System.out.println(number);
    }
}
