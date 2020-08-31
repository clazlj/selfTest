package util;

import java.util.LinkedList;
import java.util.List;

public class RedPacketUtils {
    /**
     * 红包最小值1分
     */
    private static final int MIN_MONEY = 1;

    /**
     * 红包最大值200元
     */
    private static final int MAX_MONEY = 200 * 100;

    /**
     * 小于最小值
     */
    private static final int LESS = -1;

    /**
     * 大于最大值
     */
    private static final int MORE = -2;

    /**
     * 正常值
     */
    private static final int OK = 1;

    /**
     * 最大的红包是平均值的TIMES倍，防止某一次分配红包较大
     */
    private static final double TIMES = 2.1F;

    private static int recursiveCount = 0;

    public static List<Integer> splitRedPacket(int money, int count) {
        List<Integer> moneys = new LinkedList<>();

        //每人都领到最大红包钱还没分完
        if (MAX_MONEY * count < money) {
            System.err.println("请调大最大红包金额 MAX_MONEY=[" + MAX_MONEY + "]");
            return moneys;
        }

        //计算出最大红包
        int max = (int) ((money / count) * TIMES);
        max = max > MAX_MONEY ? MAX_MONEY : max;

        for (int i = 0; i < count; i++) {
            int redPacket = randomRedpacket(money, MIN_MONEY, max, count - i);
            moneys.add(redPacket);
            //总金额每次减少
            money -= redPacket;
        }

        return moneys;
    }

    private static int randomRedpacket(int totalMoney, int minMoney, int maxMoney, int count) {
        //只开一个红包
        if (count == 1) {
            return totalMoney;
        }

        if (minMoney == maxMoney) {
            return minMoney;
        }

        //如果最大金额大于了剩余金额 则用剩余金额
        maxMoney = maxMoney > totalMoney ? totalMoney : maxMoney;

        int redPacket = (int) (Math.random() * (maxMoney - minMoney) + minMoney);

        int leftMoney = totalMoney - redPacket;

        int status = checkMoney(leftMoney, count - 1);

        if (OK == status) {
            return redPacket;
        }

        //如果生成的金额不合法 则递归重新生成
        if (LESS == status) {
            recursiveCount++;
            System.out.println("recursiveCount==" + recursiveCount);
            return randomRedpacket(totalMoney, minMoney, redPacket, count);
        }

        if (MORE == status) {
            recursiveCount++;
            System.out.println("recursiveCount==" + recursiveCount);
            return randomRedpacket(totalMoney, redPacket, maxMoney, count);
        }

        return redPacket;
    }

    private static int checkMoney(int leftMoney, int count) {
        double avg = (double) leftMoney / count;
        if (avg < MIN_MONEY) {
            return LESS;
        }

        if (avg > MAX_MONEY) {
            return MORE;
        }

        return OK;
    }
}
