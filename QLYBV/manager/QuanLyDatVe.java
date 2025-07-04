package manager;

import model.Bus;
import model.VeXe;

import java.util.ArrayList;

public class QuanLyDatVe {
    private ArrayList<Bus> danhSachChuyenXe = new ArrayList<>();
    private ArrayList<VeXe> danhSachVe = new ArrayList<>();

    public void themChuyenXe(Bus xe) {
        danhSachChuyenXe.add(xe);
    }

    public ArrayList<Bus> getDanhSachChuyenXe() {
        return danhSachChuyenXe;
    }

    public ArrayList<VeXe> getDanhSachVe() {
        return danhSachVe;
    }

    public boolean datVe(VeXe ve) {
        if (ve.getChuyenXe().datGhe(ve.getSoGheDaDat())) {
            danhSachVe.add(ve);
            return true;
        }
        return false;
    }

    public void huyVe(String maVe) {
        VeXe veCanHuy = null;
        for (VeXe ve : danhSachVe) {
            if (ve.getMaVe().equals(maVe)) {
                ve.getChuyenXe().huyGhe(ve.getSoGheDaDat());
                veCanHuy = ve;
                break;
            }
        }
        if (veCanHuy != null) {
            danhSachVe.remove(veCanHuy);
        }
    }
}
