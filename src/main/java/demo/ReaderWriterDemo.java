package demo;

import java.io.*;

public class ReaderWriterDemo {
    public static void main(String[] args) {
        copyPomFile();
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
}
