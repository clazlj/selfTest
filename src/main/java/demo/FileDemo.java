package demo;

import java.io.*;
import java.util.Scanner;

public class FileDemo {
    public static void main(String[] args) throws IOException {
//        operateFile();
//
//        operateDirectory();
//
//        listFile();

        copyImage();
    }

    private static void copyImage() {
        File file = new File("C:\\Users\\jdd\\Desktop\\B.jpg");

        try (InputStream is = new FileInputStream(file);
             OutputStream os1 = new FileOutputStream("copyB1.jpg");) {
            long start = System.currentTimeMillis();

            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                os1.write(bytes);
            }
            System.out.println("byte[]拷贝" + file.length() + "字节耗时" + (System.currentTimeMillis() - start) + "毫秒");

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (InputStream is = new FileInputStream(file);
             OutputStream os2 = new FileOutputStream("copyB2.jpg");) {
            long start = System.currentTimeMillis();
            int read;
            while ((read = is.read()) != -1) {
                os2.write(read);
            }
            System.out.println("int拷贝" + file.length() + "字节耗时" + (System.currentTimeMillis() - start) + "毫秒");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (InputStream is = new BufferedInputStream(new FileInputStream(file));
             OutputStream os3 = new BufferedOutputStream(new FileOutputStream("copyB3.jpg"))) {
            long start = System.currentTimeMillis();

            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                os3.write(bytes, 0, bytes.length);
            }
            System.out.println("Buffered缓冲byte[]拷贝" + file.length() + "字节耗时" + (System.currentTimeMillis() - start) + "毫秒");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
