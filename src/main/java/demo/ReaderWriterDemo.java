package demo;

import cn.hutool.core.io.FileUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderWriterDemo {
    public static void main(String[] args) {
//        copyPomFile();

//        writeTxt();

        writeTxtByUtil();
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

    private static void writeTxtByUtil() {
        List<String> strList = new ArrayList<>();
        strList.add("本次选用cn.hutool的hutool-all中的工具类");
        strList.add("也可以使用其他比较成熟的工具类");
        strList.add("thank you!");
        FileUtil.appendUtf8Lines(strList, new File("writeByUtil.txt"));
    }
}
