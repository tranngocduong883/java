package regiaydemo;

import java.util.Date;

public class HoaDonXuat {

    private String MaHDX;
    private String MaGiay;
    private String MaNguoiDung;
    private int SoluongX;
    private double donGiaX;
    private Date ngayX;

    public HoaDonXuat() {
    }

    public HoaDonXuat(String MaHDX, String MaGiay, String MaNguoiDung, int SoluongX, double donGiaX, Date ngayX) {
        this.MaHDX = MaHDX;
        this.MaGiay = MaGiay;
        this.MaNguoiDung = MaNguoiDung;
        this.SoluongX = SoluongX;
        this.donGiaX = donGiaX;
        this.ngayX = ngayX;
    }
    
    public double getThanhTien() {
    return this.SoluongX * this.donGiaX;
    }

    // Getter & Setter
    public String getMaHDX() {
        return MaHDX;
    }

    public void setMaHDX(String MaHDX) {
        this.MaHDX = MaHDX;
    }

    public String getMaGiay() {
        return MaGiay;
    }

    public void setMaGiay(String MaGiay) {
        this.MaGiay = MaGiay;
    }

    public String getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(String MaNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
    }

    public int getSoLuongX() {
        return SoluongX;
    }

    public void setSoLuongX(int SoluongX) {
        this.SoluongX = SoluongX;
    }

    public double getDonGiaX() {
        return donGiaX;
    }

    public void setDonGiaX(double donGiaX) {
        this.donGiaX = donGiaX;
    }

    public Date getNgayX() {
        return ngayX;
    }

    public void setNgayX(Date ngayX) {
        this.ngayX = ngayX;
    }
}

