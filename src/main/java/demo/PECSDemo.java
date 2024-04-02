package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PECS（Producer Extends Consumer Super）原则：
 * 频繁往外读取内容的，适合用上界Extends。
 * 经常往里插入的，适合用下界Super。
 *
 * 上界生产，下界消费。
 */
public class PECSDemo {
    public static void main(String[] args) {
//        无限通配符<?>
//        上界通配符<? extends E>
//        下界通配符<? super E>

        typicalDemoWithCopy();

        extendAddDemo();
        superAddDemo();
    }

    private static void extendAddDemo() {
        // ?可能是Worker，也可能是Programmer
        // demo：如果是Programmer，此时加Worker是不能转为Programmer的
        List<? extends Worker> workerList = new ArrayList<>();
        // 编译报错
//        workerList.add(new Worker());
//        workerList.add(new Programmer());

        // 唯一的例外：null。null可以是任意类的实例。
        workerList.add(null);
    }

    private static void superAddDemo() {
        // ?可能是Worker，Job，Object等。所以加Worker及其子类都可以
        List<? super Worker> workerList = new ArrayList<>();
        workerList.add(new Worker());
        workerList.add(new Programmer());
        workerList.add(null);
        // 不能加Job实例，因为如果是Worker，没法转成Worker
//        workerList.add(new Job());
    }

    private static void typicalDemoWithCopy() {
        List<Job> jobList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            jobList.add(new Job());
        }

        List<Worker> workerList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            workerList.add(new Worker());
        }

        List<Programmer> programmerList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            programmerList.add(new Programmer());
        }

        /*Collections.copy(programmerList, jobList);
        Collections.copy(workerList, jobList);*/

        Collections.copy(jobList, workerList);
        System.out.println(jobList);

        Collections.copy(jobList, programmerList);
        System.out.println(jobList);
    }

    static class Job {

    }

    static class Worker extends Job {

    }

    static class Programmer extends Worker {

    }
}
