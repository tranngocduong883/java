package regiaydemo;

public class Giay {
    private String maGiay;
    private String tenGiay;
    private String hangSX;
    private String loaiGiay;
    private String mauSac;
    private float size;
    private float giaBan;
    private int soLuongTonKho; // số lượng tồn kho

    // Constructor đầy đủ
    public Giay(String maGiay, String tenGiay, String hangSX, String loaiGiay,
                String mauSac, float size, float giaBan, int soLuongTonKho) {
        this.maGiay = maGiay;
        this.tenGiay = tenGiay;
        this.hangSX = hangSX;
        this.loaiGiay = loaiGiay;
        this.mauSac = mauSac;
        this.size = size;
        this.giaBan = giaBan;
        this.soLuongTonKho = soLuongTonKho;
    }

    // Getter & Setter
    public String getMaGiay() { return maGiay; }
    public void setMaGiay(String maGiay) { this.maGiay = maGiay; }

    public String getTenGiay() { return tenGiay; }
    public void setTenGiay(String tenGiay) { this.tenGiay = tenGiay; }

    public String getHangSX() { return hangSX; }
    public void setHangSX(String hangSX) { this.hangSX = hangSX; }

    public String getLoaiGiay() { return loaiGiay; }
    public void setLoaiGiay(String loaiGiay) { this.loaiGiay = loaiGiay; }

    public String getMauSac() { return mauSac; }
    public void setMauSac(String mauSac) { this.mauSac = mauSac; }

    public float getSize() { return size; }
    public void setSize(float size) { this.size = size; }

    public float getGiaBan() { return giaBan; }
    public void setGiaBan(float giaBan) { this.giaBan = giaBan; }

    public int getSoLuongTonKho() { return soLuongTonKho; }
    public void setSoLuongTonKho(int soLuongTonKho) { this.soLuongTonKho = soLuongTonKho; }

    @Override
    public String toString() {
        return "Giay{" +
                "maGiay='" + maGiay + '\'' +
                ", tenGiay='" + tenGiay + '\'' +
                ", hangSX='" + hangSX + '\'' +
                ", loaiGiay='" + loaiGiay + '\'' +
                ", mauSac='" + mauSac + '\'' +
                ", size=" + size +
                ", giaBan=" + giaBan +
                ", soLuongTonKho=" + soLuongTonKho +
                '}';
    }
}
