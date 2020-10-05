package demo;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class CollectionDemo {
    public static void main(String[] args) {
        toArrayList();
//        operateArrayList();

//        operateLinkedList();
    }

    /**
     * 将数组转换为ArrayList
     */
    public static void toArrayList() {
        //最简便的方法
        List<String> list = new ArrayList<>(Arrays.asList("3", "56", "gy"));

        //使用 Java8 的Stream
        Integer[] myArray1 = { 1, 2, 3 };
        List<Integer> list1 = Arrays.stream(myArray1).collect(Collectors.toList());

        int[] myArray2 = {1, 2, 3};
        List<Integer> list2 = Arrays.stream(myArray2).mapToObj(Integer::valueOf).collect(Collectors.toList());
        list2 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());

        //使用 Guava：ImmutableList，Lists

        //使用 Apache Commons Collections

    }

    /**
     * 数学中的集合是不允许有重复元素的，类比Set
     */
    public static void operateArrayList() {
        List<String> listA = Arrays.asList("1", "2", "2", "3", "4");
        List<String> listB = Arrays.asList("3", "4", "4", "5", "2", "2", "2");

        //重复元素在哪个list出现的次数多，则取哪个list中的重复元素
        System.out.println("并集：" + CollectionUtils.union(listA, listB));

        System.out.println("交集：" + CollectionUtils.intersection(listA, listB));

        System.out.println("交集的补集：" + CollectionUtils.disjunction(listA, listB));

        System.out.println("交集的补集：" +CollectionUtils.disjunction(listB, listA));

        System.out.println("A与B的差：" +CollectionUtils.subtract(listA, listB));

        System.out.println("B与A的差：" +CollectionUtils.subtract(listB, listA));
    }

    public static void operateLinkedList() {
        List<StudentVO> linkedList = new LinkedList<StudentVO>(){
            {
                add(new StudentVO("小米", 3));
                add(new StudentVO("黑猫", 5));
            }
        };

        //生效前提：重写hashCode、equals方法
        linkedList.remove(new StudentVO("小米", 3));
    }
}

class StudentVO{
    public StudentVO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentVO studentVO = (StudentVO) o;
        return age == studentVO.age &&
                Objects.equals(name, studentVO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

