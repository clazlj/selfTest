package demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PECS（Producer Extends Consumer Super）原则：
 * 频繁往外读取内容的，适合用上界Extends。
 * 经常往里插入的，适合用下界Super。
 */
public class PECSDemo {
    public static void main(String[] args) {

        typicalDemoWithCopy();

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
