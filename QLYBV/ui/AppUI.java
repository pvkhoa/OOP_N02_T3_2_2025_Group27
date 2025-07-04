package ui;

import model.Bus;
import model.VeXe;
import manager.QuanLyDatVe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.UUID;

public class AppUI extends JFrame {
    private QuanLyDatVe ql = new QuanLyDatVe();

    private JTextField tfMa, tfDiemDi, tfDiemDen, tfGio, tfSoGhe;
    private JTextField tfTenKhach, tfSoGheDat, tfMaVeHuy;

    private DefaultListModel<String> modelBus = new DefaultListModel<>();
    private DefaultListModel<String> modelVe = new DefaultListModel<>();
    private JList<String> listBus = new JList<>(modelBus);
    private JList<String> listVe = new JList<>(modelVe);

    public AppUI() {
        setTitle("Ứng dụng Quản lý Vé Xe - Swing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTabbedPane tab = new JTabbedPane();
        tab.add("Quản lý chuyến xe", createBusPanel());
        tab.add("Đặt vé", createVePanel());

        add(tab);
        setVisible(true);
    }

    private JPanel createBusPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel input = new JPanel(new GridLayout(6, 2, 5, 5));
        tfMa = new JTextField();
        tfDiemDi = new JTextField();
        tfDiemDen = new JTextField();
        tfGio = new JTextField();
        tfSoGhe = new JTextField();

        input.add(new JLabel("Mã chuyến:"));
        input.add(tfMa);
        input.add(new JLabel("Điểm đi:"));
        input.add(tfDiemDi);
        input.add(new JLabel("Điểm đến:"));
        input.add(tfDiemDen);
        input.add(new JLabel("Giờ khởi hành:"));
        input.add(tfGio);
        input.add(new JLabel("Số ghế tối đa:"));
        input.add(tfSoGhe);

        JButton btnThem = new JButton("Thêm chuyến xe");
        btnThem.addActionListener(this::themChuyenXe);
        input.add(btnThem);

        panel.add(input, BorderLayout.NORTH);
        panel.add(new JScrollPane(listBus), BorderLayout.CENTER);

        return panel;
    }

    private JPanel createVePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel input = new JPanel(new GridLayout(5, 2, 5, 5));
        tfTenKhach = new JTextField();
        tfSoGheDat = new JTextField();
        tfMaVeHuy = new JTextField();

        input.add(new JLabel("Tên khách hàng:"));
        input.add(tfTenKhach);
        input.add(new JLabel("Số ghế muốn đặt:"));
        input.add(tfSoGheDat);
        JButton btnDat = new JButton("Đặt vé");
        btnDat.addActionListener(this::datVe);
        input.add(btnDat);

        input.add(new JLabel("Nhập mã vé để hủy:"));
        input.add(tfMaVeHuy);
        JButton btnHuy = new JButton("Hủy vé");
        btnHuy.addActionListener(this::huyVe);
        input.add(btnHuy);

        panel.add(input, BorderLayout.NORTH);
        panel.add(new JScrollPane(listVe), BorderLayout.CENTER);

        return panel;
    }

    private void themChuyenXe(ActionEvent e) {
        try {
            Bus xe = new Bus(
                    tfMa.getText(),
                    tfDiemDi.getText(),
                    tfDiemDen.getText(),
                    tfGio.getText(),
                    Integer.parseInt(tfSoGhe.getText())
            );
            ql.themChuyenXe(xe);
            updateBusList();
            tfMa.setText(""); tfDiemDi.setText(""); tfDiemDen.setText("");
            tfGio.setText(""); tfSoGhe.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ và đúng định dạng.");
        }
    }

    private void datVe(ActionEvent e) {
        int selectedIndex = listBus.getSelectedIndex();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Hãy chọn một chuyến xe trong danh sách.");
            return;
        }

        try {
            Bus xe = ql.getDanhSachChuyenXe().get(selectedIndex);
            int soGhe = Integer.parseInt(tfSoGheDat.getText());
            String maVe = UUID.randomUUID().toString().substring(0, 8);

            VeXe ve = new VeXe(tfTenKhach.getText(), maVe, xe, soGhe);
            if (ql.datVe(ve)) {
                updateVeList();
                updateBusList();
                tfTenKhach.setText(""); tfSoGheDat.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Không đủ ghế trống.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng.");
        }
    }

    private void huyVe(ActionEvent e) {
        String maVe = tfMaVeHuy.getText();
        ql.huyVe(maVe);
        updateVeList();
        updateBusList();
        tfMaVeHuy.setText("");
    }

    private void updateBusList() {
        modelBus.clear();
        for (Bus b : ql.getDanhSachChuyenXe()) {
            modelBus.addElement(b.toString());
        }
    }

    private void updateVeList() {
        modelVe.clear();
        for (VeXe v : ql.getDanhSachVe()) {
            modelVe.addElement(v.toString());
        }
    }

    public static void main(String[] args) {
        new AppUI();
    }
}
