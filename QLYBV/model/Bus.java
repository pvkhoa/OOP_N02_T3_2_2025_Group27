package model;

public class Bus {
    private String maChuyenXe;
    private String diemDi;
    private String diemDen;
    private String gioKhoiHanh;
    private int soGheToiDa;
    private int soGheConTrong;

    public Bus(String maChuyenXe, String diemDi, String diemDen, String gioKhoiHanh, int soGheToiDa) {
        this.maChuyenXe = maChuyenXe;
        this.diemDi = diemDi;
        this.diemDen = diemDen;
        this.gioKhoiHanh = gioKhoiHanh;
        this.soGheToiDa = soGheToiDa;
        this.soGheConTrong = soGheToiDa;
    }

    public String getMaChuyenXe() {
        return maChuyenXe;
    }

    public String getDiemDi() {
        return diemDi;
    }

    public String getDiemDen() {
        return diemDen;
    }

    public String getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    public int getSoGheConTrong() {
        return soGheConTrong;
    }

    public boolean datGhe(int soGhe) {
        if (soGhe <= soGheConTrong) {
            soGheConTrong -= soGhe;
            return true;
        }
        return false;
    }

    public void huyGhe(int soGhe) {
        soGheConTrong += soGhe;
        if (soGheConTrong > soGheToiDa) soGheConTrong = soGheToiDa;
    }

    @Override
    public String toString() {
        return maChuyenXe + " | " + diemDi + " → " + diemDen + " | " + gioKhoiHanh + " | Ghế trống: " + soGheConTrong;
    }
}
