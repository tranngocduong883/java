package regiaydemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GiayDao {

    // Lấy toàn bộ danh sách giày kèm số lượng tồn kho
    public static ArrayList<Giay> getAllGiay() {
        ArrayList<Giay> list = new ArrayList<>();
        Connection conn = DBConnection.getConnection();

        // Truy vấn lấy thông tin giày + số lượng tồn kho
        String sql = """
            SELECT G.MaGiay, G.TenGiay, G.HangSX, G.LoaiGiay, G.MauSac, G.Size, G.giaban,
                   ISNULL(SUM(HDN.SoluongN), 0) - ISNULL(SUM(HDX.SoluongX), 0) AS SoLuongTonKho
            FROM Giay G
            LEFT JOIN HoaDonNhap HDN ON G.MaGiay = HDN.MaGiay
            LEFT JOIN HoaDonXuat HDX ON G.MaGiay = HDX.MaGiay
            GROUP BY G.MaGiay, G.TenGiay, G.HangSX, G.LoaiGiay, G.MauSac, G.Size, G.giaban
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
                        rs.getFloat("giaban"),
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
}
