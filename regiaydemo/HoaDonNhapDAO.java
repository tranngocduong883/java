package regiaydemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonNhapDAO {

    private Connection conn;

    public HoaDonNhapDAO() {
        conn = DBConnection.getConnection();
    }

    // ===================== INSERT =====================
    public boolean insert(HoaDonNhap hdn) {
        String sql = "INSERT INTO hoadonnhap (MaHDN, MaGiay, SoluongN, donGiaN, ngayN) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hdn.getMaHDN());
            ps.setString(2, hdn.getMaGiay());
            ps.setInt(3, hdn.getSoLuongN());
            ps.setDouble(4, hdn.getDonGiaN());
            ps.setDate(5, new java.sql.Date(hdn.getNgayN().getTime()));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===================== UPDATE =====================
    public boolean update(HoaDonNhap hdn) {
        String sql = "UPDATE hoadonnhap SET MaGiay=?, SoluongN=?, donGiaN=?, ngayN=? "
                   + "WHERE MaHDN=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hdn.getMaGiay());
            ps.setInt(2, hdn.getSoLuongN());
            ps.setDouble(3, hdn.getDonGiaN());
            ps.setDate(4, new java.sql.Date(hdn.getNgayN().getTime()));
            ps.setString(5, hdn.getMaHDN());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===================== DELETE =====================
    public boolean delete(String maHDN) {
        String sql = "DELETE FROM hoadonnhap WHERE MaHDN=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHDN);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===================== GET ALL =====================
    public List<HoaDonNhap> getAll() {
        List<HoaDonNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadonnhap";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonNhap hdn = new HoaDonNhap(
                        rs.getString("MaHDN"),
                        rs.getString("MaGiay"),
                        rs.getInt("SoluongN"),
                        rs.getDouble("donGiaN"),
                        rs.getDate("ngayN")
                );
                list.add(hdn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ===================== GET BY ID =====================
    public HoaDonNhap getByID(String maHDN) {
        String sql = "SELECT * FROM hoadonnhap WHERE MaHDN=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHDN);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new HoaDonNhap(
                        rs.getString("MaHDN"),
                        rs.getString("MaGiay"),
                        rs.getInt("SoluongN"),
                        rs.getDouble("donGiaN"),
                        rs.getDate("ngayN")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ===================== SEARCH BY MaGiay =====================
    public List<HoaDonNhap> searchByMaGiay(String maGiay) {
        List<HoaDonNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadonnhap WHERE MaGiay LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + maGiay + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDonNhap hdn = new HoaDonNhap(
                        rs.getString("MaHDN"),
                        rs.getString("MaGiay"),
                        rs.getInt("SoluongN"),
                        rs.getDouble("donGiaN"),
                        rs.getDate("ngayN")
                );
                list.add(hdn);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ===================== CHECK EXIST =====================
    // Kiểm tra trùng mã hóa đơn khi thêm mới
    public boolean exists(String maHDN) {
        String sql = "SELECT MaHDN FROM hoadonnhap WHERE MaHDN=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHDN);
            ResultSet rs = ps.executeQuery();

            return rs.next();  // true = đã tồn tại

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
