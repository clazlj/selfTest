package demo;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 带戳的原子引用
 * 可判断是否发生了ABA
 */
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(13, 0);

        Integer reference = stampedReference.getReference();
        int stamp = stampedReference.getStamp();

        System.out.println(stampedReference.compareAndSet(reference, 15, stamp, stamp + 1));


        System.out.println(stampedReference.compareAndSet(reference, 16, stamp, stamp + 1));
    }
}
