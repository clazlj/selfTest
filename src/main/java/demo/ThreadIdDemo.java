package demo;

public class ThreadIdDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        Thread t2 = new Thread("t2");

        getIdAndName(t1);
        getIdAndName(t2);
    }

    private static void getIdAndName(Thread t) {
        //唯一id(long型)【发现debug和run生成的值不同】
        System.out.println(t.getId());
        //没指定name时，自动生成name，格式：Thread-自增数字(int型)
        System.out.println(t.getName());
    }
}
