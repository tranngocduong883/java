package regiaydemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {

    public boolean checkLogin(String user, String pass) {
        String sql = "SELECT * FROM NguoiDung WHERE MaNguoiDung = ? AND MatKhau = ?";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // nếu có dữ liệu → đúng
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
