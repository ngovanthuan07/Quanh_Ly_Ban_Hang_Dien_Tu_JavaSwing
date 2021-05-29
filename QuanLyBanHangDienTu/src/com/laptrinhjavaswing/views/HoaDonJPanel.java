/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.views;

import com.laptrinhjavaswing.model.ChiTietHoaDonModel;
import com.laptrinhjavaswing.model.HoaDonModel;
import com.laptrinhjavaswing.model.KhachHangModel;
import com.laptrinhjavaswing.model.SanPhamModel;
import com.laptrinhjavaswing.model.displayvalueModel;
import com.laptrinhjavaswing.service.impl.ChiTietHoaDonService;
import com.laptrinhjavaswing.service.impl.HoaDonService;
import com.laptrinhjavaswing.service.impl.KhachHangService;
import com.laptrinhjavaswing.service.impl.NhanVienModel;
import com.laptrinhjavaswing.service.impl.NhanVienService;
import com.laptrinhjavaswing.service.impl.SanPhamService;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ngova
 */
public class HoaDonJPanel extends javax.swing.JPanel {

    /**
     * Creates new form HoaDonJPanel
     */
    HoaDonModel hoaDonModel;
    HoaDonService hoaDonService;
    ChiTietHoaDonService chiTietHoaDonService;
    DefaultTableModel modelHoaDon, modelChiTietHoaDon;
    NhanVienService nhanVienService;
    KhachHangService khachHangService;
    SanPhamService sanPhamService;
    List<displayvalueModel> nhanVienModel, sanphamModel, khachHangModel;

    public HoaDonJPanel() {
        initComponents();
        nhanVienModel = new ArrayList<>();
        khachHangModel = new ArrayList<>();
        sanphamModel = new ArrayList<>();
        renderHoaDon();

        khachHangService = new KhachHangService();
        nhanVienService = new NhanVienService();
        sanPhamService = new SanPhamService();

        List<KhachHangModel> tenKhachHang = khachHangService.findAll();
        List<NhanVienModel> tenNhanVien = nhanVienService.findAll();
        List<SanPhamModel> tenSanPham = sanPhamService.findAll();

        cbbNhanVien_HoaDon.removeAllItems();
        cbbKhachHang_HoaDon.removeAllItems();
        cbbSanPham_ChiTietHoaDon.removeAllItems();
        for (int i = 0; i < tenNhanVien.size(); i++) {
            NhanVienModel get = tenNhanVien.get(i);
            cbbNhanVien_HoaDon.addItem(get.getTenNhanVien());
            nhanVienModel.add(new displayvalueModel(get.getMaNhanVien(), get.getTenNhanVien()));
        }

        for (int i = 0; i < tenKhachHang.size(); i++) {
            KhachHangModel get = tenKhachHang.get(i);
            cbbKhachHang_HoaDon.addItem(get.getTenKhachHang());
            khachHangModel.add(new displayvalueModel(get.getMaKhachHang(), get.getTenKhachHang()));
        }

        for (int i = 0; i < tenSanPham.size(); i++) {
            SanPhamModel get = tenSanPham.get(i);
            cbbSanPham_ChiTietHoaDon.addItem(get.getTenSanPham());
            sanphamModel.add(new displayvalueModel(get.getMaSanPham(), get.getTenSanPham()));
        }

    }

    public void renderHoaDon() {
        hoaDonService = new HoaDonService();
        modelHoaDon = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblHoaDon_HoaDon.setModel(modelHoaDon);

        modelHoaDon.addColumn("Stt");
        modelHoaDon.addColumn("Mã hóa đơn");
        modelHoaDon.addColumn("Tên khách hàng");
        modelHoaDon.addColumn("Tên nhân viên");
        modelHoaDon.addColumn("Ngày lập hóa đơn");
        modelHoaDon.addColumn("Tổng tiền");
        modelHoaDon.addColumn("Ghi chú");

        List<HoaDonModel> hoaDonItem = hoaDonService.findAll();
        for (int i = 0; i < hoaDonItem.size(); i++) {
            modelHoaDon.addRow(new Object[]{
                (i + 1), hoaDonItem.get(i).getMaHoaDon(), hoaDonItem.get(i).getTenKhachHang(), hoaDonItem.get(i).getTenNhanVien(), hoaDonItem.get(i).getNgayLapHoaDon(), hoaDonItem.get(i).getTongTien(), hoaDonItem.get(i).getGhiChu()
            });
        }
    }

    public void renderChiTietHoaDon(int MaHoaDon) {
        chiTietHoaDonService = new ChiTietHoaDonService();
        modelChiTietHoaDon = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblCTHoaDon_ChiTietHoaDon.setModel(modelChiTietHoaDon);
        modelChiTietHoaDon.addColumn("Stt");
        modelChiTietHoaDon.addColumn("Mã CTHĐ");
        modelChiTietHoaDon.addColumn("Mã Hóa Đơn");
        modelChiTietHoaDon.addColumn("Sản Phẩm");
        modelChiTietHoaDon.addColumn("Số Lượng");
        modelChiTietHoaDon.addColumn("Tổng tiền");
        modelChiTietHoaDon.addColumn("Ghi chú");

        List<ChiTietHoaDonModel> listChiTiet = chiTietHoaDonService.findAllByCodeMaHoaDon(MaHoaDon);
        if (listChiTiet == null) {
            return;
        }
        for (int i = 0; i < listChiTiet.size(); i++) {
            ChiTietHoaDonModel get = listChiTiet.get(i);
            modelChiTietHoaDon.addRow(new Object[]{
                (i + 1),
                get.getMaCTHD(),
                get.getMaHoaDon(),
                get.getTenSanPham(),
                get.getSoLuong(),
                get.getTongTien(),
                get.getGhiChu()
            });
        }
    }

    public String GetCbbSelected(Object cbb, ArrayList<displayvalueModel> pramater) {
        Object obj = cbb;
        int index = -1;
        for (int i = 0; i < pramater.size(); i++) {
            displayvalueModel get = pramater.get(i);
            if (get.getDisplayvalue().equals(obj)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return null;
        }
        return pramater.get(index).toString();
    }

    public void setSelectedCombobox(String cbbselected, JComboBox cbb) {
        for (int i = 0; i < cbb.getItemCount(); i++) {
            Object obj = cbb.getItemAt(i);
            if (obj != null) {
                if (cbbselected.trim().equals(obj)) {
                    cbb.setSelectedItem(obj);
                }
            }
        }
    }

    public void ThongBao(String noiDungThongBao, String tieuDeThongBao, int icon) {
        JOptionPane.showMessageDialog(new JFrame(), noiDungThongBao,
                tieuDeThongBao, icon);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaPhieuMua_HoaDon = new javax.swing.JTextField();
        txtTongTien_HoaDon = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtGhiChu_HoaDon = new javax.swing.JTextArea();
        cbbNhanVien_HoaDon = new javax.swing.JComboBox<>();
        cbbKhachHang_HoaDon = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnThem_HoaDon = new javax.swing.JButton();
        btnSua_HoaDon = new javax.swing.JButton();
        btnXoa_HoaDon = new javax.swing.JButton();
        btnReset_HoaDon = new javax.swing.JButton();
        txtNgayLapHoaDon_HoaDon = new com.toedter.calendar.JDateChooser();
        btnBaoCao_HoaDon = new javax.swing.JButton();
        txtTimKiemHoaDon = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        lblSoLuong_CTPM = new javax.swing.JPanel();
        lblMaCTPM = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtMaCTH_ChiTietHoaDon = new javax.swing.JTextField();
        txtMaHoaDon_ChiTietHoaDon = new javax.swing.JTextField();
        txtSoLuong_ChiTietHoaDon = new javax.swing.JTextField();
        txtTongTien_ChiTietHoaDon = new javax.swing.JTextField();
        cbbSanPham_ChiTietHoaDon = new javax.swing.JComboBox<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtGhiChu_ChiTietHoaDon = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        btnThem_ChiTietHoaDon = new javax.swing.JButton();
        btnSua_ChiTietHoaDon = new javax.swing.JButton();
        btnXoa_ChiTietHoaDon = new javax.swing.JButton();
        btnReset_ChiTietHoaDon = new javax.swing.JButton();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHoaDon_HoaDon = new javax.swing.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        tblCTHoaDon_ChiTietHoaDon = new javax.swing.JTable();

        setBackground(new java.awt.Color(179, 230, 255));

        jPanel1.setBackground(new java.awt.Color(179, 230, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 102, 255));
        jLabel7.setText("Bảng Hóa Đơn");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 102, 255));
        jLabel8.setText("Chi Tiết Hóa Đơn");

        jPanel2.setBackground(new java.awt.Color(179, 230, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(359, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(0, 204, 153));

        jLabel1.setText("Mã Hóa Đơn");

        jLabel2.setText("Nhân Viên");

        jLabel3.setText("Khách Hàng");

        jLabel4.setText("Ngày Lập");

        jLabel5.setText("Tổng Tiền");

        txtMaPhieuMua_HoaDon.setEditable(false);
        txtMaPhieuMua_HoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtTongTien_HoaDon.setEditable(false);

        txtGhiChu_HoaDon.setColumns(20);
        txtGhiChu_HoaDon.setRows(5);
        jScrollPane3.setViewportView(txtGhiChu_HoaDon);

        cbbNhanVien_HoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbKhachHang_HoaDon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Ghi Chú");

        btnThem_HoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/add.png"))); // NOI18N
        btnThem_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_HoaDonActionPerformed(evt);
            }
        });

        btnSua_HoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/edit.png"))); // NOI18N
        btnSua_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_HoaDonActionPerformed(evt);
            }
        });

        btnXoa_HoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/remove.png"))); // NOI18N
        btnXoa_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_HoaDonActionPerformed(evt);
            }
        });

        btnReset_HoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/reset.png"))); // NOI18N
        btnReset_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_HoaDonActionPerformed(evt);
            }
        });

        txtNgayLapHoaDon_HoaDon.setDateFormatString("yyyy-MM-dd");

        btnBaoCao_HoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/excel.png"))); // NOI18N
        btnBaoCao_HoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBaoCao_HoaDonActionPerformed(evt);
            }
        });

        txtTimKiemHoaDon.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtTimKiemHoaDonInputMethodTextChanged(evt);
            }
        });
        txtTimKiemHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemHoaDonActionPerformed(evt);
            }
        });
        txtTimKiemHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemHoaDonKeyPressed(evt);
            }
        });

        jLabel9.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTien_HoaDon)
                            .addComponent(txtMaPhieuMua_HoaDon)
                            .addComponent(cbbNhanVien_HoaDon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbKhachHang_HoaDon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayLapHoaDon_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnThem_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnSua_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(btnXoa_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btnReset_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(btnBaoCao_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                                    .addComponent(txtTimKiemHoaDon))
                                .addGap(34, 34, 34))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(124, 124, 124))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaPhieuMua_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbNhanVien_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbKhachHang_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtNgayLapHoaDon_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtTongTien_HoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtTimKiemHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSua_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(btnThem_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa_HoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBaoCao_HoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        lblSoLuong_CTPM.setBackground(new java.awt.Color(0, 204, 153));

        lblMaCTPM.setText("Mã CTPM");

        lblMaHoaDon.setText("Mã Hóa Đơn");

        jLabel11.setText("Sản Phẩm");

        jLabel12.setText("Số Lượng");

        jLabel13.setText("Thành Tiền");

        txtMaCTH_ChiTietHoaDon.setEditable(false);
        txtMaCTH_ChiTietHoaDon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtMaHoaDon_ChiTietHoaDon.setEditable(false);
        txtMaHoaDon_ChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHoaDon_ChiTietHoaDonActionPerformed(evt);
            }
        });

        txtSoLuong_ChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuong_ChiTietHoaDonActionPerformed(evt);
            }
        });
        txtSoLuong_ChiTietHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSoLuong_ChiTietHoaDonKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSoLuong_ChiTietHoaDonKeyReleased(evt);
            }
        });

        txtTongTien_ChiTietHoaDon.setEditable(false);

        cbbSanPham_ChiTietHoaDon.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbSanPham_ChiTietHoaDonItemStateChanged(evt);
            }
        });

        txtGhiChu_ChiTietHoaDon.setColumns(20);
        txtGhiChu_ChiTietHoaDon.setRows(5);
        jScrollPane4.setViewportView(txtGhiChu_ChiTietHoaDon);

        jLabel14.setText("Ghi Chú");

        btnThem_ChiTietHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/add.png"))); // NOI18N
        btnThem_ChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_ChiTietHoaDonActionPerformed(evt);
            }
        });

        btnSua_ChiTietHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/edit.png"))); // NOI18N
        btnSua_ChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_ChiTietHoaDonActionPerformed(evt);
            }
        });

        btnXoa_ChiTietHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/remove.png"))); // NOI18N
        btnXoa_ChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_ChiTietHoaDonActionPerformed(evt);
            }
        });

        btnReset_ChiTietHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/reset.png"))); // NOI18N
        btnReset_ChiTietHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_ChiTietHoaDonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lblSoLuong_CTPMLayout = new javax.swing.GroupLayout(lblSoLuong_CTPM);
        lblSoLuong_CTPM.setLayout(lblSoLuong_CTPMLayout);
        lblSoLuong_CTPMLayout.setHorizontalGroup(
            lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblSoLuong_CTPMLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(btnThem_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSua_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(btnXoa_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76)
                .addComponent(btnReset_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(lblSoLuong_CTPMLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaCTPM)
                    .addComponent(lblMaHoaDon)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(45, 45, 45)
                .addGroup(lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTongTien_ChiTietHoaDon)
                    .addComponent(txtSoLuong_ChiTietHoaDon)
                    .addComponent(cbbSanPham_ChiTietHoaDon, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaCTH_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaHoaDon_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblSoLuong_CTPMLayout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblSoLuong_CTPMLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(156, 156, 156))))
        );
        lblSoLuong_CTPMLayout.setVerticalGroup(
            lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lblSoLuong_CTPMLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaCTPM)
                    .addComponent(txtMaCTH_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lblSoLuong_CTPMLayout.createSequentialGroup()
                        .addGroup(lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblMaHoaDon)
                            .addComponent(txtMaHoaDon_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbbSanPham_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtSoLuong_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblSoLuong_CTPMLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)))
                .addGroup(lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongTien_ChiTietHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(lblSoLuong_CTPMLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa_ChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua_ChiTietHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem_ChiTietHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(btnReset_ChiTietHoaDon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );

        tblHoaDon_HoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Khách Hàng", "Nhân Viên", "Ngày Lập HĐ", "Tổng Tiền", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon_HoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDon_HoaDonMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tblHoaDon_HoaDon);

        tblCTHoaDon_ChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã CTHD", "Mã Hóa Đơn", "Sản Phẩm", "Số Lượng", "Tổng Tiền", "Ghi Chú"
            }
        ));
        tblCTHoaDon_ChiTietHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTHoaDon_ChiTietHoaDonMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(tblCTHoaDon_ChiTietHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblSoLuong_CTPM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(258, 258, 258)
                        .addComponent(jLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel8))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSoLuong_CTPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnReset_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_HoaDonActionPerformed
        // TODO add your handling code here:
        cbbKhachHang_HoaDon.setSelectedIndex(1);
        cbbNhanVien_HoaDon.setSelectedIndex(1);
        txtTongTien_HoaDon.setText("");
        txtGhiChu_HoaDon.setText("");
        txtMaPhieuMua_HoaDon.setText("");
        renderHoaDon();
    }//GEN-LAST:event_btnReset_HoaDonActionPerformed

    private void btnReset_ChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_ChiTietHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReset_ChiTietHoaDonActionPerformed

    private void tblHoaDon_HoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDon_HoaDonMouseClicked
        try {
            int row = tblHoaDon_HoaDon.getSelectedRow();
            int chiTietHoaDon = (int) tblHoaDon_HoaDon.getValueAt(row, 1);
            renderChiTietHoaDon(chiTietHoaDon);
            txtMaHoaDon_ChiTietHoaDon.setText("" + chiTietHoaDon);
            txtSoLuong_ChiTietHoaDon.setText("0");
            Date date;

            date = new SimpleDateFormat("yyyy-MM-dd").parse((String) tblHoaDon_HoaDon.getValueAt(row, 4).toString());
            txtNgayLapHoaDon_HoaDon.setDate(date);

            txtMaPhieuMua_HoaDon.setText(tblHoaDon_HoaDon.getValueAt(row, 1).toString());
            txtTongTien_HoaDon.setText(tblHoaDon_HoaDon.getValueAt(row, 5).toString());
            txtGhiChu_HoaDon.setText(tblHoaDon_HoaDon.getValueAt(row, 6).toString());
            setSelectedCombobox(tblHoaDon_HoaDon.getValueAt(row, 3).toString(), cbbNhanVien_HoaDon);
            setSelectedCombobox(tblHoaDon_HoaDon.getValueAt(row, 2).toString(), cbbKhachHang_HoaDon);
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_tblHoaDon_HoaDonMouseClicked

    private void tblCTHoaDon_ChiTietHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTHoaDon_ChiTietHoaDonMouseClicked

        try {
            int viTriDongVuaBam = tblCTHoaDon_ChiTietHoaDon.getSelectedRow();
            txtMaCTH_ChiTietHoaDon.setText(tblCTHoaDon_ChiTietHoaDon.getValueAt(viTriDongVuaBam, 1).toString());
            txtMaHoaDon_ChiTietHoaDon.setText(tblCTHoaDon_ChiTietHoaDon.getValueAt(viTriDongVuaBam, 2).toString());
            txtSoLuong_ChiTietHoaDon.setText(tblCTHoaDon_ChiTietHoaDon.getValueAt(viTriDongVuaBam, 4).toString());
            txtTongTien_ChiTietHoaDon.setText(tblCTHoaDon_ChiTietHoaDon.getValueAt(viTriDongVuaBam, 5).toString());
            txtGhiChu_ChiTietHoaDon.setText(tblCTHoaDon_ChiTietHoaDon.getValueAt(viTriDongVuaBam, 6).toString());
            setSelectedCombobox(tblCTHoaDon_ChiTietHoaDon.getValueAt(viTriDongVuaBam, 3).toString(), cbbSanPham_ChiTietHoaDon);
        } catch (Exception e) {
            return;
        }

//        setSelectedCombobox(tblCTHoaDon_ChiTietHoaDon.getValueAt(viTriDongVuaBam, 3).toString(), cbbSanPham_ChiTietHoaDon);

    }//GEN-LAST:event_tblCTHoaDon_ChiTietHoaDonMouseClicked

    private void btnThem_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_HoaDonActionPerformed
        if (txtNgayLapHoaDon_HoaDon.getDate() == null) {
            ThongBao("Bị thiếu", "Bị thiếu", 2);
            return;
        }
        try {
            HoaDonModel hd = new HoaDonModel();
            hd.setMaKhachHang(Integer.parseInt(GetCbbSelected(cbbKhachHang_HoaDon.getSelectedItem(), (ArrayList<displayvalueModel>) khachHangModel)));
            hd.setMaNhanVien(Integer.parseInt(GetCbbSelected(cbbNhanVien_HoaDon.getSelectedItem(), (ArrayList<displayvalueModel>) nhanVienModel)));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(txtNgayLapHoaDon_HoaDon.getDate());
            hd.setNgayLapHoaDon(date);
            hd.setTongTien(0);
            hd.setGhiChu(txtGhiChu_HoaDon.getText());

            int mhd = hoaDonService.save(hd);
            ThongBao("Thêm thành công", "Thành công", 3);
            renderHoaDon();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnThem_HoaDonActionPerformed

    private void btnXoa_ChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_ChiTietHoaDonActionPerformed
        // TODO add your handling code here:
        if (txtMaCTH_ChiTietHoaDon.getText().equals("")) {
            ThongBao("Chưa nhập mã xóa", "lỗi", 2);
            return;
        }
        try {
            chiTietHoaDonService.remove(Integer.parseInt(txtMaCTH_ChiTietHoaDon.getText()));
            ThongBao("Xóa thành công", "Thành công", 3);
            hoaDonService.editTongTien(Integer.parseInt(txtMaHoaDon_ChiTietHoaDon.getText()));

            renderHoaDon();
            renderChiTietHoaDon(Integer.parseInt(txtMaHoaDon_ChiTietHoaDon.getText()));
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_btnXoa_ChiTietHoaDonActionPerformed

    private void btnSua_ChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_ChiTietHoaDonActionPerformed
        // TODO add your handling code here:
        try {
            int row = tblCTHoaDon_ChiTietHoaDon.getSelectedRow();
            if (row == -1 || txtSoLuong_ChiTietHoaDon.getText().equals("") || txtMaCTH_ChiTietHoaDon.getText().equals("")) {
                ThongBao("có thể bạn không chọn vào bảng hoặc là cố một cố cột bị trỗng", "lỗi", 1);
                return;
            }

            ChiTietHoaDonModel ct = new ChiTietHoaDonModel();
            ct.setMaCTHD(Integer.parseInt(txtMaCTH_ChiTietHoaDon.getText()));
            ct.setMaHoaDon(Integer.parseInt(txtMaHoaDon_ChiTietHoaDon.getText()));
            ct.setMaSanPham(Integer.parseInt(GetCbbSelected(cbbSanPham_ChiTietHoaDon.getSelectedItem(), (ArrayList<displayvalueModel>) sanphamModel)));
            ct.setSoLuong(Integer.parseInt(txtSoLuong_ChiTietHoaDon.getText()));
            ct.setTongTien(Long.parseLong(txtTongTien_ChiTietHoaDon.getText()));
            ct.setGhiChu(txtGhiChu_ChiTietHoaDon.getText());

            if (!txtMaCTH_ChiTietHoaDon.getText().equals("")) {
                chiTietHoaDonService.edit(ct, Integer.parseInt(txtMaCTH_ChiTietHoaDon.getText()));
                System.out.println("success");
            } else {
                System.out.println("failure");
            }
            ThongBao("Sửa thành công", "success", 3);

            hoaDonService.editTongTien(Integer.parseInt(txtMaHoaDon_ChiTietHoaDon.getText()));

            renderHoaDon();
            renderChiTietHoaDon(Integer.parseInt(txtMaHoaDon_ChiTietHoaDon.getText()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSua_ChiTietHoaDonActionPerformed

    private void btnThem_ChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_ChiTietHoaDonActionPerformed
        // TODO add your handling code here:

        if (txtSoLuong_ChiTietHoaDon.getText().equals("") || txtMaHoaDon_ChiTietHoaDon.getText().equals("")) {
            ThongBao("Có Tội Ô Bị Thiếu", "Lỗi", 1);
            return;
        }
        if (!txtSoLuong_ChiTietHoaDon.getText().matches("^\\d{0,20}$")) {
            ThongBao("Số lượng phải là số", "Lỗi", 2);
            return;
        }
        try {
            ChiTietHoaDonModel ct = new ChiTietHoaDonModel();
            ct.setMaHoaDon(Integer.parseInt(txtMaHoaDon_ChiTietHoaDon.getText()));
            ct.setMaSanPham(Integer.parseInt(GetCbbSelected(cbbSanPham_ChiTietHoaDon.getSelectedItem(), (ArrayList<displayvalueModel>) sanphamModel)));
            ct.setSoLuong(Integer.parseInt(txtSoLuong_ChiTietHoaDon.getText()));
            ct.setTongTien(Long.parseLong(txtTongTien_ChiTietHoaDon.getText()));
            ct.setGhiChu(txtGhiChu_ChiTietHoaDon.getText());
            int kiemTra = chiTietHoaDonService.save(ct);
            System.out.println("Thành công nhé" + kiemTra);
            ThongBao("Thành công", "Thành Công", 3);
            renderHoaDon();
            renderChiTietHoaDon(Integer.parseInt(txtMaHoaDon_ChiTietHoaDon.getText()));
        } catch (Exception e) {
            System.out.println("lỗi rồi nhé");
        }


    }//GEN-LAST:event_btnThem_ChiTietHoaDonActionPerformed

    private void txtSoLuong_ChiTietHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuong_ChiTietHoaDonKeyReleased
        // TODO add your handling code here:
        SanPhamService sp = new SanPhamService();
        int SoLuong = 0;
        long Tien = 0l;
        try {
            SoLuong = Integer.valueOf(txtSoLuong_ChiTietHoaDon.getText());
        } catch (Exception e) {
        }
        int maSP = Integer.parseInt(GetCbbSelected(cbbSanPham_ChiTietHoaDon.getSelectedItem(), (ArrayList<displayvalueModel>) sanphamModel));
        List<SanPhamModel> sanPham_SoLuong = sp.findByCodeMaSanPham(maSP);
        Tien = (long) sanPham_SoLuong.get(0).getGiaBan() * SoLuong;
        txtTongTien_ChiTietHoaDon.setText(String.valueOf(Tien));
    }//GEN-LAST:event_txtSoLuong_ChiTietHoaDonKeyReleased

    private void txtSoLuong_ChiTietHoaDonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoLuong_ChiTietHoaDonKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuong_ChiTietHoaDonKeyPressed

    private void btnSua_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_HoaDonActionPerformed
        // TODO add your handling code here:
        int row = tblHoaDon_HoaDon.getSelectedRow();
        if (row == -1) {
            ThongBao("Vui lòng chọn ô vào bảng để chỉnh sửa", "erro", row);
            return;
        }
        try {
            int maHoaDon = Integer.valueOf(txtMaPhieuMua_HoaDon.getText());
            HoaDonModel hd = new HoaDonModel();
            hd.setMaKhachHang(Integer.parseInt(GetCbbSelected(cbbKhachHang_HoaDon.getSelectedItem(), (ArrayList<displayvalueModel>) khachHangModel)));
            hd.setMaNhanVien(Integer.parseInt(GetCbbSelected(cbbNhanVien_HoaDon.getSelectedItem(), (ArrayList<displayvalueModel>) nhanVienModel)));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(txtNgayLapHoaDon_HoaDon.getDate());
            hd.setNgayLapHoaDon(date);
            hd.setGhiChu(txtGhiChu_HoaDon.getText());
            hoaDonService.edit(hd, maHoaDon);
            renderChiTietHoaDon(maHoaDon);
            renderHoaDon();

            ThongBao("Chỉnh sửa thành công", "Sửa thành công", 3);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSua_HoaDonActionPerformed

    private void btnXoa_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_HoaDonActionPerformed
        // TODO add your handling code here:

        if (!txtMaPhieuMua_HoaDon.getText().equals("")) {
            try {
                int maMaHoaDon = Integer.parseInt(txtMaPhieuMua_HoaDon.getText());
                List<ChiTietHoaDonModel> cthd = chiTietHoaDonService.findAllByCodeMaHoaDon(maMaHoaDon);
                System.out.println(cthd);
                if (cthd == null) {
                    hoaDonService.remove(maMaHoaDon);
                    ThongBao("Xóa Thành Công", "Xóa thành công", 3);
                    renderHoaDon();
                } else {
                    ThongBao("Ở bản chi tiết hóa đơn còn hóa đơn chưa xóa", "xóa đi bạn", cthd.size());
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            ThongBao("bạn chưa chọn hóa đơn để xóa", "Này bạn chộn ô trong bản đế xóa nhé", 2);
        }
    }//GEN-LAST:event_btnXoa_HoaDonActionPerformed

    private void txtMaHoaDon_ChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHoaDon_ChiTietHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHoaDon_ChiTietHoaDonActionPerformed

    private void btnBaoCao_HoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBaoCao_HoaDonActionPerformed
        // TODO add your handling code here:
        FileOutputStream out = null;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("hoadon");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Hóa Đơn");

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã Hóa Đơn");

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Khách hàng");

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Nhân viên");

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Ngày lập");

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Tổng tiền");

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Ghi chú");

            List<HoaDonModel> listHD = hoaDonService.findAll();
            if (listHD != null) {
                for (int i = 0; i < listHD.size(); i++) {
                    HoaDonModel get = listHD.get(i);
                    row = spreadsheet.createRow((short) 4 + i);
                    row.setHeight((short) 400);
                    row.createCell(0).setCellValue(i + 1);
                    row.createCell(1).setCellValue(get.getMaHoaDon());
                    row.createCell(2).setCellValue(get.getTenKhachHang());
                    row.createCell(3).setCellValue(get.getTenNhanVien());
                    row.createCell(4).setCellValue(get.getNgayLapHoaDon());
                    row.createCell(5).setCellValue(get.getTongTien());
                    row.createCell(6).setCellValue(get.getGhiChu());
                }

                out = new FileOutputStream(new File("D:/hv.xlsx"));
                workbook.write(out);
                ThongBao("Vào ổ đĩa D dể xem tài liệu đã lưu", "Thành công", 1);
                out.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }//GEN-LAST:event_btnBaoCao_HoaDonActionPerformed

    private void txtSoLuong_ChiTietHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuong_ChiTietHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuong_ChiTietHoaDonActionPerformed

    private void cbbSanPham_ChiTietHoaDonItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbSanPham_ChiTietHoaDonItemStateChanged
        // TODO add your handling code here:
        if (sanphamModel != null);
        {
            SanPhamService sp = new SanPhamService();
            int SoLuong = 0;
            long Tien = 0l;
            try {
                if (txtSoLuong_ChiTietHoaDon.getText().equals("")) {
                    txtSoLuong_ChiTietHoaDon.setText("" + 0);
                    return;
                }
                SoLuong = Integer.valueOf(txtSoLuong_ChiTietHoaDon.getText());
                int maSP = Integer.parseInt(GetCbbSelected(cbbSanPham_ChiTietHoaDon.getSelectedItem(), (ArrayList<displayvalueModel>) sanphamModel));
                List<SanPhamModel> sanPham_SoLuong = sp.findByCodeMaSanPham(maSP);
                Tien = (long) sanPham_SoLuong.get(0).getGiaBan() * SoLuong;
                txtTongTien_ChiTietHoaDon.setText(String.valueOf(Tien));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_cbbSanPham_ChiTietHoaDonItemStateChanged

    private void txtTimKiemHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiemHoaDonActionPerformed

    private void txtTimKiemHoaDonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DefaultTableModel table = (DefaultTableModel) tblHoaDon_HoaDon.getModel();
            String search = txtTimKiemHoaDon.getText().toLowerCase().trim();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
            tblHoaDon_HoaDon.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
        }
    }//GEN-LAST:event_txtTimKiemHoaDonKeyPressed

    private void txtTimKiemHoaDonInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtTimKiemHoaDonInputMethodTextChanged
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txtTimKiemHoaDonInputMethodTextChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBaoCao_HoaDon;
    private javax.swing.JButton btnReset_ChiTietHoaDon;
    private javax.swing.JButton btnReset_HoaDon;
    private javax.swing.JButton btnSua_ChiTietHoaDon;
    private javax.swing.JButton btnSua_HoaDon;
    private javax.swing.JButton btnThem_ChiTietHoaDon;
    private javax.swing.JButton btnThem_HoaDon;
    private javax.swing.JButton btnXoa_ChiTietHoaDon;
    private javax.swing.JButton btnXoa_HoaDon;
    private javax.swing.JComboBox<String> cbbKhachHang_HoaDon;
    private javax.swing.JComboBox<String> cbbNhanVien_HoaDon;
    private javax.swing.JComboBox<String> cbbSanPham_ChiTietHoaDon;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMaCTPM;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JPanel lblSoLuong_CTPM;
    private javax.swing.JTable tblCTHoaDon_ChiTietHoaDon;
    private javax.swing.JTable tblHoaDon_HoaDon;
    private javax.swing.JTextArea txtGhiChu_ChiTietHoaDon;
    private javax.swing.JTextArea txtGhiChu_HoaDon;
    private javax.swing.JTextField txtMaCTH_ChiTietHoaDon;
    private javax.swing.JTextField txtMaHoaDon_ChiTietHoaDon;
    private javax.swing.JTextField txtMaPhieuMua_HoaDon;
    private com.toedter.calendar.JDateChooser txtNgayLapHoaDon_HoaDon;
    private javax.swing.JTextField txtSoLuong_ChiTietHoaDon;
    private javax.swing.JTextField txtTimKiemHoaDon;
    private javax.swing.JTextField txtTongTien_ChiTietHoaDon;
    private javax.swing.JTextField txtTongTien_HoaDon;
    // End of variables declaration//GEN-END:variables
}
