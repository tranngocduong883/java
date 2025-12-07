package regiaydemo;

import java.sql.*;
import java.util.ArrayList;

public class GiayDao {

    // Lấy toàn bộ danh sách giày kèm số lượng tồn kho
    public static ArrayList<Giay> getAllGiay() {
        ArrayList<Giay> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();

        String sql = """
            SELECT G.MaGiay, G.TenGiay, G.HangSX, G.LoaiGiay, G.MauSac, G.Size, G.GiaBan,
                   ISNULL(SUM(HDN.SoluongN), 0) - ISNULL(SUM(HDX.SoluongX), 0) AS SoLuongTonKho
            FROM Giay G
            LEFT JOIN HoaDonNhap HDN ON G.MaGiay = HDN.MaGiay
            LEFT JOIN HoaDonXuat HDX ON G.MaGiay = HDX.MaGiay
            GROUP BY G.MaGiay, G.TenGiay, G.HangSX, G.LoaiGiay, G.MauSac, G.Size, G.GiaBan
        """;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Giay g = new Giay(
                        rs.getString("MaGiay"),
                        rs.getString("TenGiay"),
                        rs.getString("HangSX"),
                        rs.getString("LoaiGiay"),
                        rs.getString("MauSac"),
                        rs.getFloat("Size"),
                        rs.getFloat("GiaBan"),
                        rs.getInt("SoLuongTonKho")
                );
                list.add(g);
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm giày mới
    public static boolean insert(Giay g) {
        String sql = "INSERT INTO Giay (MaGiay, TenGiay, HangSX, LoaiGiay, MauSac, Size, GiaBan) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getMaGiay());
            ps.setString(2, g.getTenGiay());
            ps.setString(3, g.getHangSX());
            ps.setString(4, g.getLoaiGiay());
            ps.setString(5, g.getMauSac());
            ps.setFloat(6, g.getSize());
            ps.setFloat(7, g.getGiaBan());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sửa giày
    public static boolean update(Giay g) {
        String sql = "UPDATE Giay SET TenGiay=?, HangSX=?, LoaiGiay=?, MauSac=?, Size=?, GiaBan=? WHERE MaGiay=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getTenGiay());
            ps.setString(2, g.getHangSX());
            ps.setString(3, g.getLoaiGiay());
            ps.setString(4, g.getMauSac());
            ps.setFloat(5, g.getSize());
            ps.setFloat(6, g.getGiaBan());
            ps.setString(7, g.getMaGiay());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xoá giày
    public static boolean delete(String maGiay) {
        String sql = "DELETE FROM Giay WHERE MaGiay=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maGiay);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm giày theo tên
    public static ArrayList<Giay> search(String keyword) {
        ArrayList<Giay> list = new ArrayList<>();
        String sql = "SELECT * FROM Giay WHERE TenGiay LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Giay g = new Giay(
                        rs.getString("MaGiay"),
                        rs.getString("TenGiay"),
                        rs.getString("HangSX"),
                        rs.getString("LoaiGiay"),
                        rs.getString("MauSac"),
                        rs.getFloat("Size"),
                        rs.getFloat("GiaBan"),
                        0 // tồn kho không tính ở đây
                );
                list.add(g);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
