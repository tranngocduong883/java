package regiaydemo;

import java.util.Date;

public class HoaDonNhap {

    private String MaHDN;
    private String MaGiay;
    private int SoluongN;
    private double donGiaN;
    private Date ngayN;

    public HoaDonNhap() {}

    public HoaDonNhap(String MaHDN, String MaGiay, int SoluongN, double donGiaN, Date ngayN) {
        this.MaHDN = MaHDN;
        this.MaGiay = MaGiay;
        this.SoluongN = SoluongN;
        this.donGiaN = donGiaN;
        this.ngayN = ngayN;
    }
    
    public double getThanhTien() {
    return this.SoluongN * this.donGiaN;
    }


    // Getter & Setter đúng theo DAO
    public String getMaHDN() { return MaHDN; }
    public void setMaHDN(String MaHDN) { this.MaHDN = MaHDN; }

    public String getMaGiay() { return MaGiay; }
    public void setMaGiay(String MaGiay) { this.MaGiay = MaGiay; }

    public int getSoLuongN() { return SoluongN; }
    public void setSoLuongN(int SoluongN) { this.SoluongN = SoluongN; }

    public double getDonGiaN() { return donGiaN; }
    public void setDonGiaN(double donGiaN) { this.donGiaN = donGiaN; }

    public Date getNgayN() { return ngayN; }
    public void setNgayN(Date ngayN) { this.ngayN = ngayN; }
}
