package demo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        operateFile();

        operateDirectory();

        listFile();
    }

    private static void operateFile() throws IOException {
        String path = String.format("E:%sfff", File.separator);
        File file = new File(path);

        System.out.println("文件或目录的名称:" + file.getName());
        System.out.println(path + "文件创建成功?" + file.createNewFile());
        System.out.println("绝对路径:" + file.getAbsolutePath());
        System.out.println(path + "删除成功?" + file.delete());
        System.out.println("------");
    }

    private static void operateDirectory() {
        String path = "javaHtml";
        directoryByPath(path);


        //多级目录一次只删除了最里面的一级目录
        String nestedPath = String.format("javaWeb%sController%sbiz", File.separator, File.separator);
        directoryByPath(nestedPath);
    }

    private static void directoryByPath(String path) {
        File pathDir = new File(path);
        System.out.println(path + "进行mkdir成功?" + pathDir.mkdir());
        System.out.println(path + "进行mkdirs成功?" + pathDir.mkdirs());
        System.out.println("绝对路径:" + pathDir.getAbsolutePath());
        System.out.println(path + "删除成功?" + pathDir.delete());
        System.out.println("------");
    }


    private static void listFile() {
        System.out.println("请输入文件夹的绝对路径：");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File不存在");
            return;
        }

        System.out.println("此抽象路径名表示的目录中的文件和目录有：");
        String[] list = file.list();
        if (list != null) {
            for (String str : list) {
                System.out.println(str);
            }
        }

        System.out.println("遍历获得文件路径：");
        File[] files = file.listFiles();
        listAllFileName(files);

        System.out.println("------");
    }

    private static void listAllFileName(File[] files) {
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                File[] nestedFiles = file.listFiles();
                listAllFileName(nestedFiles);
            } else {
                System.out.println(file.getAbsolutePath());
            }
        }
    }
}
