package regiaydemo;

import java.sql.*;

public class UserDao {
    private Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLBanHang;encrypt=false";
        String user = "sa";   // user SQL Server
        String pass = "23082005"; // mật khẩu sa của bạn

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, pass);
    }

    public boolean kiemTraDangNhap(String user, String pass) {
        String sql = "SELECT * FROM NguoiDung WHERE MaNguoiDung = ? AND MatKhau = ?";
        try {
            Connection cn = getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // Nếu có dữ liệu → đúng tài khoản
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
