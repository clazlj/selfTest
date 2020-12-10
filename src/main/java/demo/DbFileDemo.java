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


    /**
     * 关于注册JDBC驱动Class.forName(JDBC_DRIVER);
     * JDBC 4.0 的规范规定，所有 JDBC 4.0 的驱动 jar 文件必须包含一个 java.sql.Driver，
     * 它位于 jar 文件的 META-INF/services 目录下。这个文件里每一行便描述了一个对应的驱动类
     * 在启动项目或是服务时，会判断当前classpath中的所的jar包，并检查他们META-INF目录下，是否包含services文件夹，如果包含，就会将里面的配置加载成相应的服务。
     * 所以，对于4.0前，使用Class.forName
     * 4.0后我们只需要将JAR包管理好就足够了，这一切都会有人帮我们完成(当然前提是需要使用配套的驱动Jar包）
     * 从 Java 6 开始，应用程序不再需要显式地加载驱动程序了，DriverManager 开始能够自动地承担这项任务.
     */
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
