package demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 通用的
 * 具体整型之类的可使用AtomicInteger，AtomicBoolean，AtomicLong
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        DecimalAccount.demo(new DecimalAccountCas(BigDecimal.valueOf(10000)));
    }
}

class DecimalAccountCas implements DecimalAccount {
    private AtomicReference<BigDecimal> balance;

    public DecimalAccountCas(BigDecimal balance) {
        this.balance = new AtomicReference<>(balance);
    }

    @Override
    public BigDecimal getBalance() {
        return balance.get();
    }

    @Override
    public void withDraw(BigDecimal amount) {
        //不用返回值
//        while (true) {
//            BigDecimal prev = balance.get();
//            BigDecimal next = prev.subtract(amount);
//            if (balance.compareAndSet(prev, next)) {
//                break;
//            }
//        }

        //可返回值
//        BigDecimal prev, next;
//        do {
//            prev = balance.get();
//            next = prev.subtract(amount);
//        } while (!balance.compareAndSet(prev, next));

        //封装的api
        //++i
//        balance.updateAndGet((i) -> i.subtract(amount));

        //i++
        balance.getAndUpdate((i) -> i.subtract(amount));
    }
}

interface DecimalAccount{
    BigDecimal getBalance();

    void withDraw(BigDecimal amount);

    static void demo(DecimalAccount account) {
        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threadList.add(new Thread(() -> {
                account.withDraw(BigDecimal.TEN);
            }, String.format("t%s", i)));
        }
        threadList.forEach(Thread::start);
        threadList.forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("余额：" + account.getBalance());
    }
}
