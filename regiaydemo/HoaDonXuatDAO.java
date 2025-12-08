package regiaydemo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonXuatDAO {

    private Connection conn;

    public HoaDonXuatDAO() {
        conn = DBConnection.getConnection();
    }

    // ===================== INSERT =====================
    public boolean insert(HoaDonXuat hdx) {
        String sql = "INSERT INTO hoadonxuat (MaHDX, MaGiay, MaNguoiDung, SoLuongX, donGiaX, ngayX) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hdx.getMaHDX());
            ps.setString(2, hdx.getMaGiay());
            ps.setString(3, hdx.getMaNguoiDung());
            ps.setInt(4, hdx.getSoLuongX());
            ps.setDouble(5, hdx.getDonGiaX());
            ps.setDate(6, new java.sql.Date(hdx.getNgayX().getTime()));

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===================== UPDATE =====================
    public boolean update(HoaDonXuat hdx) {
        String sql = "UPDATE hoadonxuat SET MaGiay=?, MaNguoiDung=?, SoLuongX=?, donGiaX=?, ngayX=? "
                   + "WHERE MaHDX=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hdx.getMaGiay());
            ps.setString(2, hdx.getMaNguoiDung());
            ps.setInt(3, hdx.getSoLuongX());
            ps.setDouble(4, hdx.getDonGiaX());
            ps.setDate(5, new java.sql.Date(hdx.getNgayX().getTime()));
            ps.setString(6, hdx.getMaHDX());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===================== DELETE =====================
    public boolean delete(String maHDX) {
        String sql = "DELETE FROM hoadonxuat WHERE MaHDX=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHDX);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ===================== GET ALL =====================
    public List<HoaDonXuat> getAll() {
        List<HoaDonXuat> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadonxuat";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonXuat hdx = new HoaDonXuat(
                        rs.getString("MaHDX"),
                        rs.getString("MaGiay"),
                        rs.getString("MaNguoiDung"),
                        rs.getInt("SoLuongX"),
                        rs.getDouble("donGiaX"),
                        rs.getDate("ngayX")
                );
                list.add(hdx);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ===================== GET BY ID =====================
    public HoaDonXuat getByID(String maHDX) {
        String sql = "SELECT * FROM hoadonxuat WHERE MaHDX=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHDX);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new HoaDonXuat(
                        rs.getString("MaHDX"),
                        rs.getString("MaGiay"),
                        rs.getString("MaNguoiDung"),
                        rs.getInt("SoLuongX"),
                        rs.getDouble("donGiaX"),
                        rs.getDate("ngayX")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ===================== SEARCH BY MaGiay =====================
    public List<HoaDonXuat> searchByMaGiay(String maGiay) {
        List<HoaDonXuat> list = new ArrayList<>();
        String sql = "SELECT * FROM hoadonxuat WHERE MaGiay LIKE ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + maGiay + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new HoaDonXuat(
                        rs.getString("MaHDX"),
                        rs.getString("MaGiay"),
                        rs.getString("MaNguoiDung"),
                        rs.getInt("SoLuongX"),
                        rs.getDouble("donGiaX"),
                        rs.getDate("ngayX")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ===================== CHECK EXIST =====================
    public boolean exists(String maHDX) {
        String sql = "SELECT MaHDX FROM hoadonxuat WHERE MaHDX=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maHDX);
            ResultSet rs = ps.executeQuery();

            return rs.next(); // true = tồn tại

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
