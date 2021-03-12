package tricks;

public class BitOperation {
    public static void main(String[] args) {
        tableSizeFor(9);

        System.out.println(highestThanNum(9));
    }

    /**
     * <=某个数的，是2次幂的最大数
     */
    private static int highestThanNum(int num) {
        //数的2进制看做 1 ****，最终结果想让它变成1 0000这种格式【1后面的数字全变成0】
        //求出一个最大的并<=原数的1 0000...
        //先将1后面的数字全变成1
        //右移1位：    0 1***，或操作得到1 1*** 【前2位是1】
        //新数右移2位: 0  011*，或操作得到1 111*【前4位是1】
        //新数右移4位: 0  0001，或操作得到1 1111【前8位是1】
        //新数右移8位: 0  0000，或操作得到1 1111【前16位是1】
        //新数右移16位: 0  0000，或操作得到1 1111【前32位是1】
        //新数全是1，返回新数-新数>>>1【无符号右移，高位补0】
        num = num | num >>> 1;
        num = num | num >>> 2;
        num = num | num >>> 4;
        num = num | num >>> 8;
        num = num | num >>> 16;
        //移位运算的优先级小于加减运算的优先级，运算时先进行加减
        return num - (num >> 1);
    }

    /**
     * java8计算HashMap的数组长度
     */
    private static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        int MAXIMUM_CAPACITY = 1 << 30;

        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

}
