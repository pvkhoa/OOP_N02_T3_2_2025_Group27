package model;

public class VeXe {
    private String tenKhachHang;
    private String maVe;
    private Bus chuyenXe;
    private int soGheDaDat;

    public VeXe(String tenKhachHang, String maVe, Bus chuyenXe, int soGheDaDat) {
        this.tenKhachHang = tenKhachHang;
        this.maVe = maVe;
        this.chuyenXe = chuyenXe;
        this.soGheDaDat = soGheDaDat;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public String getMaVe() {
        return maVe;
    }

    public Bus getChuyenXe() {
        return chuyenXe;
    }

    public int getSoGheDaDat() {
        return soGheDaDat;
    }

    @Override
    public String toString() {
        return maVe + " | " + tenKhachHang + " | " + chuyenXe.getMaChuyenXe() + " | Ghế: " + soGheDaDat;
    }
}
