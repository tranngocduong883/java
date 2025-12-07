/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package regiaydemo;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author trand
 */
public class TonKhoDAO {
    public static ArrayList<String> getTonKho() {
        ArrayList<String> list = new ArrayList<>();

        String sql =
            "SELECT G.MaGiay, G.TenGiay, " +
            "ISNULL(SUM(HDN.SoluongN), 0) AS TongNhap, " +
            "ISNULL(SUM(HDX.SoluongX), 0) AS TongXuat, " +
            "(ISNULL(SUM(HDN.SoluongN), 0) - ISNULL(SUM(HDX.SoluongX), 0)) AS TonKho " +
            "FROM Giay G " +
            "LEFT JOIN HoaDonNhap HDN ON G.MaGiay = HDN.MaGiay " +
            "LEFT JOIN HoaDonXuat HDX ON G.MaGiay = HDX.MaGiay " +
            "GROUP BY G.MaGiay, G.TenGiay " +
            "ORDER BY G.MaGiay";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String row =
                        rs.getString("MaGiay") + " | " +
                        rs.getString("TenGiay") + " | Nhập: " +
                        rs.getInt("TongNhap") + " | Xuất: " +
                        rs.getInt("TongXuat") + " | Tồn: " +
                        rs.getInt("TonKho");

                list.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
