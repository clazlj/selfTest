package demo;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

public class CollectionDemo {
    public static void main(String[] args) {
        operateCollections();
    }

    /**
     * 数学中的集合是不允许有重复元素的，类比Set
     */
    public static void operateCollections() {
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
}
