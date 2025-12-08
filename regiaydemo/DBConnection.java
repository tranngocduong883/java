package regiaydemo;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLBanHang;encrypt=false;";
        String user = "quocanh";
        String pass = "12345678";  // Đổi mật khẩu SQL Server

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Kết nối thành công SQL Server!");
            return conn;
        } catch (Exception e) {
            System.out.println("Kết nối thất bại!");
            e.printStackTrace();
        }
        return null;
    }
}
