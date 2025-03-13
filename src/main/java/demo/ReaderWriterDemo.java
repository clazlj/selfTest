package demo;

import cn.hutool.core.io.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderWriterDemo {
    public static void main(String[] args) {
//        copyPomFile();

//        writeTxt();

//        readTxt();

//        writeTxtByUtil();

        readTxtByUtil("writeByUtil.txt");

    }

    public static String readTxtByUtil(String pathname) {
        List<String> resultList = FileUtil.readUtf8Lines(new File(pathname));
        StringBuilder sb = new StringBuilder();
        for (String str : resultList) {
            sb.append(String.format("INSERT INTO `jdd_zhcx_info`.`info_fake_user`(`s_tenant_code`, `n_user_id`, `d_update_time`, `d_create_time`) VALUES ('zhcx', %s, now(), now());", str));
            sb.append(System.lineSeparator());
        }
        String result = sb.toString();
        System.out.println(result);
        return result;
    }

    private static void copyPomFile() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("pom.xml")));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("pom-backup.xml")))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                bw.write(line);
                //换行
                bw.newLine();
                bw.flush();
            }
        } catch (IOException ex) {

        }
    }

    private static void writeTxt() {
        List<String> strList = new ArrayList<>();
        strList.add("今天是2022年10月22号");
        strList.add("就在今天全国人民代表大会第二十次全体会议胜利闭幕");
        strList.add("所以将这段话记录到txt文件中");
        strList.add("thank you!");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("write.txt", true)))) {
            for (String str : strList) {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readTxt() {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("write.txt"))))) {
            String str;
            while ((str = reader.readLine()) != null) {
                stringBuilder.append(str);
                stringBuilder.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stringBuilder);
    }

    private static void writeTxtByUtil() {
        List<String> strList = new ArrayList<>();
        strList.add("本次选用cn.hutool的hutool-all中的工具类");
        strList.add("也可以使用其他比较成熟的工具类");
        strList.add("thank you!");
        FileUtil.appendUtf8Lines(strList, new File("writeByUtil.txt"));
    }
}
