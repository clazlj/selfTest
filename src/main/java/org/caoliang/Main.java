package org.caoliang;

import org.apache.commons.lang3.StringUtils;
import service.*;
import service.impl.*;
import util.RedPacketUtils;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

public class Main {
    private static UseStreamDemoService useStreamDemoService = new UseStreamDemoServiceImpl();

    private static SetUsageService setUsageService = new SetUsageServiceImpl();

    private static OperateTimeService operateTimeService = new OperateTimeServiceImpl();

    private static UseIteratorService useIteratorService = new UseIteratorServiceImpl();

    private static UseRedisService useRedisService = new UseRedisServiceImpl();

    private static OperateFileService operateFileService = new OperateFileServiceImpl();

    public static final Integer[] SSQ_RED_NUMBER = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33};


    public static void main(String[] ars) {
        List<Integer> def = new ArrayList<>(Arrays.asList(1, 2, 3, 5, 4));

        List<Integer> hij = def.stream().sorted(Comparator.comparing(Integer::intValue)).collect(Collectors.toList());

        String replaceResult = "2,3,4,5".replace(",", "球,");

        if (replaceResult != null && replaceResult.length() > 0) {
            replaceResult += "球";
        }

        //StreamUtils.dealCollect();
        //StreamUtils.measureSumPerf(StreamUtils::sequentialSum, 10000000);
        //StreamUtils.measureSumPerf(StreamUtils::parallelSum, 10000000);
        //StreamUtils.mapAndFlatMap();
        //operateTimeService.learnDate();

        //去除BigDecimal小数点后的多余0
        String decimalStr = new BigDecimal("100.000").stripTrailingZeros().toPlainString();

        decimalStr = new BigDecimal("100.000").setScale(2, BigDecimal.ROUND_HALF_EVEN).stripTrailingZeros().toString();

        //TestListUtils.sortList();

        //MapHandler.mapNewCharacter();

        RedPacketUtils.splitRedPacket(9990, 8);

        String abc = String.format("奖励比例设置不得低于%s%%",
                BigDecimal.valueOf(0.005).multiply(BigDecimal.valueOf(100)).stripTrailingZeros().toString());

        int abcd =(int) (253 * 0.2);

        Double efgh = 253 * Double.valueOf("0.2");

        List<String> ijk = Arrays.asList("1", "2", "3", "4", "5", "6", "7");

        if (true) {
            return;
        }

        useStreamDemoService.useStream();

        String key = String.format("%s:%s", new Object[]{null, "abcd"});

        if (1 == 1) {
            //operateFileService.copyFile();
            operateFileService.bufferCopyFile();
            return;
        }

        BigDecimal decimalVal = BigDecimal.ONE;
        decimalVal.add(BigDecimal.TEN);

        int a = 5, b = 8;

        String splitStr = "171122001=6_3S1.94/1S3.00^1_3S4.15,171122002=6_3S1.93/1S3.20^1_3S3.95/1S3.60";


        //随机打乱Collections
        String[] arr = new String[]{"1", "2", "3"};
        List arrList = Arrays.asList(arr);
        Collections.shuffle(arrList);

        BigDecimal decimalByDouble = BigDecimal.valueOf(0.35);

        useRedisService.connectToRedis();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -15);

        String booleanString = "123" + Boolean.TRUE;
        Collections.synchronizedCollection(new ArrayList<>());
        Collections.synchronizedList(new ArrayList<>());

        String dev = "DEV".toLowerCase();
        int hashCode = dev.hashCode();
        dev = "dev";
        hashCode = dev.toLowerCase().hashCode();

        String dataSql = getDataSql("");

        boolean isRight = Integer.valueOf(2).equals(null);

        String replaceStr = "5_55*9:0";
        replaceStr.replace("9:0", "胜其他");

        setUsageService.getDistinctObject();
        useIteratorService.iteratorMain();

//        DigestUtils
//        Long.valueOf(null);

        String fileCoding = System.getProperty("file.encoding");
        String charSet = Charset.defaultCharset().displayName();

        List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        for (Object o : list) {
            int orderNo = list.indexOf(o) + 1;
            System.out.println(orderNo);
        }

        Collection<Integer> colc = new ArrayList<>();
        colc.add(8);

        list.forEach(System.out::println);

        aboutBigDecimal();

        getCalendar();

        useStringUtils();

        useFixedMap();

        aboutZip();

        fun("5",  3, 2, 5.5, "我是字符串");

        //Math类
        Double sqrt = Math.sqrt(9);
        Integer maxInteger = Math.max(5, 15);
        Double minDouble = Math.min(7.8d, 10.98d);
        Double powDouble = Math.pow(2, 3);
        Integer roundInteger = Math.round(9.68f);
        Long roundLong = Math.round(5456464.558d);

        replaceWords();

        getTimeSpan();

    }

    private static String getDataSql(String inputCondition) {
        String tableName = "tb_phone";
        StringBuilder sqlSb = new StringBuilder();
        String[] someConditions = inputCondition.split(";");
        for (String someCondition : someConditions) {
            sqlSb.append(String.format("INSERT INTO %s VALUES(%s);\r", tableName, someCondition));
        }
        return sqlSb.toString();
    }

    /**
     * 带可变参数的方法
     * @param args
     */
    private static void fun(Object... args){
        for (Object arg : args) {
            System.out.println(arg);
        }
    }

    private static void aboutBigDecimal() {
        BigDecimal rate = new BigDecimal("2.855");
        rate = rate.multiply(new BigDecimal(100));
        rate = new BigDecimal(Math.round(rate.doubleValue()));


        int i = 1;
    }

    private static void getCalendar(){
        Calendar calendar = Calendar.getInstance();
        /**
         * ERA = 0,YEAR = 1,MONTH = 2,WEEK_OF_YEAR = 3,WEEK_OF_MONTH = 4
         * DATE = 5,DAY_OF_MONTH = 5,DAY_OF_YEAR = 6,DAY_OF_WEEK = 7
         * DAY_OF_WEEK_IN_MONTH = 8,AM_PM = 9,HOUR = 10,HOUR_OF_DAY = 11
         * MINUTE = 12,SECOND = 13,MILLISECOND = 14,ZONE_OFFSET = 15
         * DST_OFFSET = 16,FIELD_COUNT = 17
         * 其中DATE和DAY_OF_MONTH是synonym（同义词）
         */
        //calendar.set(13, 0);//将给定的日历字段设置为给定值
        calendar.add(6, -6);
        Date date = calendar.getTime();
    }

    private static void useStringUtils(){
        String testStr = "fafdfadsf,ljojogg,outomgsf";
        String[] strArr = testStr.split(",");

        List<String> newStr = new ArrayList<>();
        for (String str : strArr) {
            newStr.add("'" + str + "'");
        }
        String result = StringUtils.join(newStr, ",");
        result = String.join(",", newStr);
    }

    private static void useFixedMap(){
        Map linkedMap = new LinkedHashMap<>();
        linkedMap.put("abc", "1");
        linkedMap.put("acb", "2");
        linkedMap.put("bca", "3");

        Map<String, String> unLinkedMap = new HashMap<>();
        unLinkedMap.put("abc", "1");
        unLinkedMap.put("acb", "2");
        unLinkedMap.put("bca", "3");

        TreeMap<String, String> treeMap = new TreeMap();
        for (Map.Entry<String, String> stringStringEntry : unLinkedMap.entrySet()) {
            treeMap.put(stringStringEntry.getKey(), stringStringEntry.getValue());
        }
    }

    private static void aboutZip(){
        File file = new File("F:" + File.separator + "图片");
        if(file.isDirectory()){
            for (File file1 : file.listFiles()) {
                try {
                    FileInputStream fileStream = new FileInputStream(file1);
                    ZipInputStream zipInput = new ZipInputStream(fileStream);
                    zipInput.getNextEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        else{

        }
    }

    private static void replaceWords(){
        String[] oldString = new String[]{"aaa", "abc", "ccc"};
        String input = "AaAbacAbcCCCaaaabcccc";
        for (String s : oldString) {
            input = input.replaceAll("(?i)" + s, "");
        }
    }

    private static void  getTimeSpan() {
        Long milliSecond = (long)1000 * 60 * 60 * 24 + 10;
        Long timeSpan = milliSecond / (1000 * 60 * 60 * 24);
    }
}
