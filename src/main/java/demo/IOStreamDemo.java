package demo;

import java.io.*;

public class IOStreamDemo {
    public static void main(String[] args) {
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
                os1.write(bytes, 0, len);
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
                os3.write(bytes, 0, len);
            }
            System.out.println("Buffered缓冲byte[]拷贝" + file.length() + "字节耗时" + (System.currentTimeMillis() - start) + "毫秒");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
