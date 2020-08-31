package service.impl;

import service.OperateFileService;

import java.io.*;

/**
 * Created by jdd on 2018/6/24.
 */
public class OperateFileServiceImpl implements OperateFileService {
    @Override
    public void copyFile() {
        try {
            FileInputStream fis = new FileInputStream("a.jpg");
            FileOutputStream fos = new FileOutputStream("copy.jpg");

            int b;
            //字节数
            int size = 0;
            /**
             * 逐个字节的拷贝
             * 效率低，实际开发不考虑
             */
            while ((b = fis.read()) != -1) {
                size++;
                fos.write(b);
            }
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bufferCopyFile() {
        try {
            FileInputStream fis = new FileInputStream("a.jpg");
            FileOutputStream fos = new FileOutputStream("copy2.jpg");
            BufferedInputStream bis = new BufferedInputStream(fis); //一次性从文件中读取8*1024个，存在缓冲区中，程序读取时不再找文件
            BufferedOutputStream bos = new BufferedOutputStream(fos);//不直接写到文件，先写到缓冲区中，直至写满再将其中数据一次性写到文件里

            int b;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }
            bis.close();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
