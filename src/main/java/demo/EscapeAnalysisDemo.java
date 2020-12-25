package demo;

/**
 * 逃逸分析把锁消除了，并在性能上得到了很大的提升。
 */
public class EscapeAnalysisDemo {
    public static void main(String[] args) {
        //去除逃逸分析的虚拟机配置参数：-XX:-DoEscapeAnalysis【130~150ms左右】
        //去除逃逸分析的虚拟机配置参数：-XX:+DoEscapeAnalysis或不写【4~5ms】
        long start = System.currentTimeMillis();
        for(int i = 0; i < 5_000_000; i++){
            createObject();
        }
        System.out.println("cost = " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void createObject(){
        synchronized (new Object()){

        }
    }
}
