package demo;

public class CurrentInfoDemo {
    public static void main(String[] args) {
        getStaticMethodInfo();
    }

    private static void getStaticMethodInfo() {
        StackTraceElement[] stackTraceArray = Thread.currentThread().getStackTrace();
        StackTraceElement ste = stackTraceArray[1];

        System.out.println("文件名：" + ste.getFileName());
        System.out.println("类名：" + ste.getClassName());
        System.out.println("方法名：" + ste.getMethodName());
        System.out.println("行号：" + ste.getLineNumber());
    }
}
