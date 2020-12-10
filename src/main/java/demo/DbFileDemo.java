package demo;

import java.io.*;
import java.sql.*;

/**
 * 数据库存储文件
 */
public class DbFileDemo {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&serverTimezone=GMT";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";


    public static void main(String[] args) {
//        writeFileToDb();

        readFileFromDb();
    }

    private static void writeFileToDb()  {
        //注册JDBC驱动
        //Class.forName(JDBC_DRIVER);
        String sql = "insert into file_demo(s_file_name,m_data) values(?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = connection.prepareStatement(sql);
             FileInputStream fis = new FileInputStream("C:\\Users\\jdd\\Desktop\\B.jpg");) {
            pstmt.setString(1, "B.jpg");
            pstmt.setBinaryStream(2, fis);
            pstmt.executeUpdate();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void readFileFromDb() {
        String sql = "select s_file_name,m_data FROM file_demo WHERE id=?;";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, 1L);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            try (InputStream is = new BufferedInputStream(rs.getBinaryStream("m_data"));
                 OutputStream fos = new BufferedOutputStream(new FileOutputStream(rs.getString("s_file_name")));) {
                byte[] bytes = new byte[1024];
                int len;
                while ((len = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, len);
                    fos.flush();
                }
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
