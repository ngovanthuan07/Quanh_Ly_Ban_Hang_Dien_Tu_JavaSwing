/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.views;

import com.laptrinhjavaswing.model.HangSanXuatModel;
import com.laptrinhjavaswing.model.PhieuNhapModel;
import com.laptrinhjavaswing.model.SanPhamModel;
import com.laptrinhjavaswing.model.displayvalueModel;
import com.laptrinhjavaswing.service.impl.HangSanXuatService;
import com.laptrinhjavaswing.service.impl.LoaiSanPhamModel;
import com.laptrinhjavaswing.service.impl.LoaiSanPhamService;
import com.laptrinhjavaswing.service.impl.PhieuNhapService;
import com.laptrinhjavaswing.service.impl.SanPhamService;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ngova
 */
public class SanPhamJPanel extends javax.swing.JPanel {

    LoaiSanPhamService loaiSanPhamService;
    HangSanXuatService hangSanXuatService;
    SanPhamService sanPhamService;
    SanPhamService sanPhanService_LoaiSanPham;
    PhieuNhapService phieuNhapService;
    DefaultTableModel modelLoaiSanPham, modelSanPham, modelPhieuNhap, modelSanPham2;
    int count = 0;
    String images_Anh = null;
    List<displayvalueModel> loaiSanPham_New_Model, hangSanXuat_New_Model, find_model;

    public SanPhamJPanel() {
        initComponents();
        renderLoaiSanPham();
        renderSanPham();
        //renderPhieuNhap();
        loaiSanPhamService = new LoaiSanPhamService();
        hangSanXuatService = new HangSanXuatService();
        loaiSanPham_New_Model = new ArrayList<>();
        hangSanXuat_New_Model = new ArrayList<>();
        find_model = new ArrayList<>();
        List<LoaiSanPhamModel> loaiSanPham_Model = loaiSanPhamService.findAll();
        for (int i = 0; i < loaiSanPham_Model.size(); i++) {
            LoaiSanPhamModel get = loaiSanPham_Model.get(i);
            cbbMaLoaiSanPham_SanPham.addItem(get.getTenLoaiSanPham());
            cbbTimKiemLoaiSanPham_SanPham.addItem(get.getTenLoaiSanPham());
            find_model.add(new displayvalueModel(get.getMaLoaiSanPham(), get.getTenLoaiSanPham()));
            loaiSanPham_New_Model.add(new displayvalueModel(get.getMaLoaiSanPham(), get.getTenLoaiSanPham()));
        }

        List<HangSanXuatModel> hangsanXuat_Model = hangSanXuatService.findAll();
        for (int i = 0; i < hangsanXuat_Model.size(); i++) {
            HangSanXuatModel get = hangsanXuat_Model.get(i);
            cbbHangSanXuat_SanPham.addItem(get.getTenHangSanXuat());
            hangSanXuat_New_Model.add(new displayvalueModel(get.getMaHangSanXuat(), get.getTenHangSanXuat()));
        }

    }

    public void renderLoaiSanPham() {
        loaiSanPhamService = new LoaiSanPhamService();
        modelLoaiSanPham = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblLoaiSanPham_LoaiSanPham.setModel(modelLoaiSanPham);

        modelLoaiSanPham.addColumn("Stt");
        modelLoaiSanPham.addColumn("Mã Loại Sản Phẩm");
        modelLoaiSanPham.addColumn("Tên Loại Sản Phẩm");

        List<LoaiSanPhamModel> listLoaiSanPhamItem = loaiSanPhamService.findAll();

        for (int i = 0; i < listLoaiSanPhamItem.size(); i++) {
            LoaiSanPhamModel get = listLoaiSanPhamItem.get(i);
            modelLoaiSanPham.addRow(new Object[]{
                (i + 1),
                get.getMaLoaiSanPham(),
                get.getTenLoaiSanPham()
            });
        }
    }

    public void renderSanPham() {
        sanPhamService = new SanPhamService();
        modelSanPham = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblSanPham.setModel(modelSanPham);
        modelSanPham.addColumn("Stt");
        modelSanPham.addColumn("Mã Sản Phẩm");
        modelSanPham.addColumn("Tên Sản Phẩm");
        modelSanPham.addColumn("Loại Sản Phẩm");
        modelSanPham.addColumn("Giá Nhập");
        modelSanPham.addColumn("Giá Bán");
        modelSanPham.addColumn("Nhà Sản Xuất");
        modelSanPham.addColumn("Tồn Kho");
        modelSanPham.addColumn("Hình Ảnh");
        modelSanPham.addColumn("Ghi Chú");

        List<SanPhamModel> listSanPham = sanPhamService.findAll();
        for (int i = 0; i < listSanPham.size(); i++) {
            SanPhamModel get = listSanPham.get(i);
            modelSanPham.addRow(new Object[]{
                (i + 1),
                get.getMaSanPham(),
                get.getTenSanPham(),
                get.getTenLoaiSanPham(),
                get.getGiaNhap(),
                get.getGiaBan(),
                get.getTenHangSanXuat(),
                get.getTonKho(),
                get.getImage(),
                get.getChuThich()
            });
        }
    }

    public void renderPhieuNhap() {
//        phieuNhapService = new PhieuNhapService();
//        modelPhieuNhap = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//        tblPhieuNhap_PhieuNhap.setModel(modelPhieuNhap);
//
//        modelPhieuNhap.addColumn("Stt");
//        modelPhieuNhap.addColumn("Mã Phiếu Nhập");
//        modelPhieuNhap.addColumn("Nhân Viên");
//        modelPhieuNhap.addColumn("Nhà Phần Phổi");
//        modelPhieuNhap.addColumn("Tổng Tiền");
//        modelPhieuNhap.addColumn("Ngày Nhập");
//        modelPhieuNhap.addColumn("Chú Thích");
//
//        List<PhieuNhapModel> listPhieuNhap = phieuNhapService.findAll();
//
//        for (int i = 0; i < listPhieuNhap.size(); i++) {
//            PhieuNhapModel get = listPhieuNhap.get(i);
//
//            modelPhieuNhap.addRow(new Object[]{
//                (i + 1),
//                get.getMaPhieuNhap(),
//                get.getTenNhanVien(),
//                get.getTenNhaPhanPhoi(),
//                get.getTongTien(),
//                get.getNgayNhap(),
//                get.getChuThich()
//            });
//        }

    }

    public void renderMaLoaiSanPham(int MaLoaiSanPham) {
        sanPhanService_LoaiSanPham = new SanPhamService();
        modelSanPham2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblSanPham_LoaiSanPham.setModel(modelSanPham2);
        modelSanPham2.addColumn("Stt");
        modelSanPham2.addColumn("Mã Sản Phẩm");
        modelSanPham2.addColumn("Tên Sản Phẩm");
        modelSanPham2.addColumn("Loại Sản Phẩm");

        List<SanPhamModel> listSanPham_loaiSanPham = sanPhanService_LoaiSanPham.findByCodeLoaiSanPham(MaLoaiSanPham);
        if (listSanPham_loaiSanPham == null) {
            return;
        }

        for (int i = 0; i < listSanPham_loaiSanPham.size(); i++) {
            SanPhamModel get = listSanPham_loaiSanPham.get(i);
            modelSanPham2.addRow(new Object[]{
                (i + 1),
                get.getMaSanPham(),
                get.getTenSanPham(),
                get.getTenLoaiSanPham()
            });
        }
    }

    public void renderMaLoaiSanPham_SanPham(int MaLoaiSanPham) {
        sanPhamService = new SanPhamService();
        modelSanPham = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblSanPham.setModel(modelSanPham);
        modelSanPham.addColumn("Stt");
        modelSanPham.addColumn("Mã Sản Phẩm");
        modelSanPham.addColumn("Tên Sản Phẩm");
        modelSanPham.addColumn("Loại Sản Phẩm");
        modelSanPham.addColumn("Giá Nhập");
        modelSanPham.addColumn("Giá Bán");
        modelSanPham.addColumn("Nhà Sản Xuất");
        modelSanPham.addColumn("Tồn Kho");
        modelSanPham.addColumn("Hình Ảnh");
        modelSanPham.addColumn("Ghi Chú");

        List<SanPhamModel> listSanPham_loaiSanPham = sanPhamService.findByCodeLoaiSanPham(MaLoaiSanPham);
        if (listSanPham_loaiSanPham == null) {
            return;
        }

        for (int i = 0; i < listSanPham_loaiSanPham.size(); i++) {
            SanPhamModel get = listSanPham_loaiSanPham.get(i);
            modelSanPham.addRow(new Object[]{
                (i + 1),
                get.getMaSanPham(),
                get.getTenSanPham(),
                get.getTenLoaiSanPham(),
                get.getGiaNhap(),
                get.getGiaBan(),
                get.getTenHangSanXuat(),
                get.getTonKho(),
                get.getImage(),
                get.getChuThich()
            });
        }
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

    public void ThongBao(String noiDungThongBao, String tieuDeThongBao, int icon) {
        JOptionPane.showMessageDialog(new JFrame(), noiDungThongBao,
                tieuDeThongBao, icon);
    }

    public void clear() {
        txtMaSanPham_SanPham.setText("");
        txtTenSanPham_SanPham.setText("");
        txtGiaNhap_SanPham.setText("");
        txtGiaBan_SanPham.setText("");
        txtTonKho_SanPham.setText("");
        txtChuThich_SanPham.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneQuanLySanPham = new javax.swing.JTabbedPane();
        jPanelSanPham = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblTenSanPham_SanPham = new javax.swing.JLabel();
        lblMaSanPham_SanPham = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbbMaLoaiSanPham_SanPham = new javax.swing.JComboBox<>();
        txtGiaNhap_SanPham = new javax.swing.JTextField();
        lblGiaNhap_SanPham = new javax.swing.JLabel();
        txtGiaBan_SanPham = new javax.swing.JTextField();
        lblGiaBan = new javax.swing.JLabel();
        txtTenSanPham_SanPham = new javax.swing.JTextField();
        txtMaSanPham_SanPham = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbbHangSanXuat_SanPham = new javax.swing.JComboBox<>();
        lblTonKho_SanPham = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtChuThich_SanPham = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        btnThem_SanPham = new javax.swing.JButton();
        bntSua_SanPham = new javax.swing.JButton();
        btnXoa_SanPham = new javax.swing.JButton();
        btnReset_SanPham = new javax.swing.JButton();
        txtTonKho_SanPham = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        labelHinhAnh = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        cbbTimKiemLoaiSanPham_SanPham = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnDoiHinh_SanPham = new javax.swing.JButton();
        jlbAnh_SanPham = new javax.swing.JLabel();
        jPanelLoaiSanPham = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblLoaiSanPham_LoaiSanPham = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTenLoaiSanPham_LoaiSanPham = new javax.swing.JTextField();
        btnThem_LoaiSanPham = new javax.swing.JButton();
        btnSua_LoaiSanPham = new javax.swing.JButton();
        btnXoa_LoaiSanPham = new javax.swing.JButton();
        txtMaLoaiSanPham_LoaiSanPham = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPham_LoaiSanPham = new javax.swing.JTable();
        jLabel74 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();

        jPanelSanPham.setBackground(new java.awt.Color(179, 230, 255));
        jPanelSanPham.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelSanPhamComponentShown(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 204, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel4.setBackground(new java.awt.Color(0, 204, 153));

        lblTenSanPham_SanPham.setText("Tên Sản PHẩm");

        lblMaSanPham_SanPham.setText("Mã Sản Phẩm");

        jLabel3.setText("Loại Sản Phẩm");

        cbbMaLoaiSanPham_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMaLoaiSanPham_SanPhamActionPerformed(evt);
            }
        });

        lblGiaNhap_SanPham.setText("Giá Nhập");

        lblGiaBan.setText("Giá Bán");

        txtMaSanPham_SanPham.setEditable(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMaSanPham_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(lblGiaNhap_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTenSanPham_SanPham)
                            .addComponent(lblGiaBan))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtGiaBan_SanPham)
                            .addComponent(txtTenSanPham_SanPham)
                            .addComponent(txtMaSanPham_SanPham)
                            .addComponent(cbbMaLoaiSanPham_SanPham, 0, 232, Short.MAX_VALUE)
                            .addComponent(txtGiaNhap_SanPham)))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaSanPham_SanPham)
                    .addComponent(txtMaSanPham_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTenSanPham_SanPham)
                    .addComponent(txtTenSanPham_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cbbMaLoaiSanPham_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaNhap_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGiaNhap_SanPham))
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGiaBan))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jLabel9.setText("Hãng Sản Xuất");

        lblTonKho_SanPham.setText("Tồn Kho");

        jLabel11.setText("Chiếc");

        txtChuThich_SanPham.setColumns(20);
        txtChuThich_SanPham.setRows(5);
        jScrollPane2.setViewportView(txtChuThich_SanPham);

        jLabel12.setText("Chú Thích");

        btnThem_SanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/add.png"))); // NOI18N
        btnThem_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_SanPhamActionPerformed(evt);
            }
        });

        bntSua_SanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/edit.png"))); // NOI18N
        bntSua_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntSua_SanPhamActionPerformed(evt);
            }
        });

        btnXoa_SanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/remove.png"))); // NOI18N
        btnXoa_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_SanPhamActionPerformed(evt);
            }
        });

        btnReset_SanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/reset.png"))); // NOI18N
        btnReset_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_SanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addGap(28, 28, 28)
                            .addComponent(cbbHangSanXuat_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addComponent(lblTonKho_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txtTonKho_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnThem_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnXoa_SanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bntSua_SanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset_SanPham, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(cbbHangSanXuat_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTonKho_SanPham)
                    .addComponent(jLabel11)
                    .addComponent(txtTonKho_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntSua_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(27, 27, 27)
                .addComponent(btnXoa_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnReset_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm ", "Tên Sản Phẩm", "Loại Sản Phẩm", "Giá Nhập", "Giá Bán", "Nhà Sản Xuất", "Tồn Kho", "ảnh", "Chú Thích"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jPanel7.setBackground(new java.awt.Color(153, 153, 255));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Tìm Kiếm");

        cbbTimKiemLoaiSanPham_SanPham.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbTimKiemLoaiSanPham_SanPhamItemStateChanged(evt);
            }
        });
        cbbTimKiemLoaiSanPham_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTimKiemLoaiSanPham_SanPhamActionPerformed(evt);
            }
        });

        jLabel1.setText("Loại Sản Phẩm");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbbTimKiemLoaiSanPham_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel19)
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbTimKiemLoaiSanPham_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnDoiHinh_SanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/changer.png"))); // NOI18N
        btnDoiHinh_SanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiHinh_SanPhamActionPerformed(evt);
            }
        });

        jlbAnh_SanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/dienthoaisamsung.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanelSanPhamLayout = new javax.swing.GroupLayout(jPanelSanPham);
        jPanelSanPham.setLayout(jPanelSanPhamLayout);
        jPanelSanPhamLayout.setHorizontalGroup(
            jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jlbAnh_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(labelHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnDoiHinh_SanPham)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelSanPhamLayout.setVerticalGroup(
            jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSanPhamLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addComponent(labelHinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSanPhamLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jlbAnh_SanPham)
                        .addGap(51, 51, 51)
                        .addComponent(btnDoiHinh_SanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanelSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelSanPhamLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jTabbedPaneQuanLySanPham.addTab("Sản Phẩm", jPanelSanPham);

        jPanelLoaiSanPham.setBackground(new java.awt.Color(179, 230, 255));
        jPanelLoaiSanPham.setPreferredSize(new java.awt.Dimension(1030, 600));
        jPanelLoaiSanPham.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                jPanelLoaiSanPhamComponentAdded(evt);
            }
        });
        jPanelLoaiSanPham.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelLoaiSanPhamComponentShown(evt);
            }
        });

        tblLoaiSanPham_LoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Loại Sản Phẩm ", "Tên Loại Sản Phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLoaiSanPham_LoaiSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLoaiSanPham_LoaiSanPhamMouseClicked(evt);
            }
        });
        tblLoaiSanPham_LoaiSanPham.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tblLoaiSanPham_LoaiSanPhamComponentShown(evt);
            }
        });
        jScrollPane3.setViewportView(tblLoaiSanPham_LoaiSanPham);

        jPanel6.setBackground(new java.awt.Color(0, 204, 153));

        jLabel13.setText("Mã Loại Sản Phẩm");

        jLabel14.setText("Tên Loại Sản Phẩm");

        btnThem_LoaiSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/add.png"))); // NOI18N
        btnThem_LoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_LoaiSanPhamActionPerformed(evt);
            }
        });

        btnSua_LoaiSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/edit.png"))); // NOI18N
        btnSua_LoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_LoaiSanPhamActionPerformed(evt);
            }
        });

        btnXoa_LoaiSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/reset.png"))); // NOI18N
        btnXoa_LoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_LoaiSanPhamActionPerformed(evt);
            }
        });

        txtMaLoaiSanPham_LoaiSanPham.setEditable(false);
        txtMaLoaiSanPham_LoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaLoaiSanPham_LoaiSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnThem_LoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(4, 4, 4)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTenLoaiSanPham_LoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                    .addComponent(txtMaLoaiSanPham_LoaiSanPham))
                                .addContainerGap(38, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(btnSua_LoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXoa_LoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaLoaiSanPham_LoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(txtTenLoaiSanPham_LoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua_LoaiSanPham)
                    .addComponent(btnThem_LoaiSanPham)
                    .addComponent(btnXoa_LoaiSanPham))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(179, 230, 255));

        tblSanPham_LoaiSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Sản Phẩm ", "Tên Sản Phẩm", "Loại Sản Phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tblSanPham_LoaiSanPham);

        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(0, 51, 255));
        jLabel74.setText(" Sản Phẩm");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(285, 285, 285))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addGap(534, 534, 534))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(0, 51, 255));
        jLabel73.setText("Loại Sản Phẩm");

        javax.swing.GroupLayout jPanelLoaiSanPhamLayout = new javax.swing.GroupLayout(jPanelLoaiSanPham);
        jPanelLoaiSanPham.setLayout(jPanelLoaiSanPhamLayout);
        jPanelLoaiSanPhamLayout.setHorizontalGroup(
            jPanelLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelLoaiSanPhamLayout.createSequentialGroup()
                .addGroup(jPanelLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLoaiSanPhamLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLoaiSanPhamLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel73)))
                .addContainerGap(298, Short.MAX_VALUE))
        );
        jPanelLoaiSanPhamLayout.setVerticalGroup(
            jPanelLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoaiSanPhamLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel73)
                .addGap(18, 18, 18)
                .addGroup(jPanelLoaiSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPaneQuanLySanPham.addTab("Loại Sản Phẩm", jPanelLoaiSanPham);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPaneQuanLySanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 1342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneQuanLySanPham, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelLoaiSanPhamComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelLoaiSanPhamComponentShown
        return;
    }//GEN-LAST:event_jPanelLoaiSanPhamComponentShown

    private void jPanelLoaiSanPhamComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jPanelLoaiSanPhamComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanelLoaiSanPhamComponentAdded

    private void txtMaLoaiSanPham_LoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaLoaiSanPham_LoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaLoaiSanPham_LoaiSanPhamActionPerformed

    private void btnXoa_LoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_LoaiSanPhamActionPerformed

    }//GEN-LAST:event_btnXoa_LoaiSanPhamActionPerformed

    private void btnSua_LoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_LoaiSanPhamActionPerformed
        int row = tblLoaiSanPham_LoaiSanPham.getSelectedRow();
        if (row == -1) {
            ThongBao("Mời bạn nhập vào ổ để sửa", "Lỗi", 2);
            return;
        }

        if (txtTenLoaiSanPham_LoaiSanPham.getText().equals("")) {
            ThongBao("Bạn nhập bị thiếu", "Lỗi", 2);
            return;
        }
        try {
            loaiSanPhamService = new LoaiSanPhamService();
            LoaiSanPhamModel lsp = new LoaiSanPhamModel();
            lsp.setMaLoaiSanPham(Integer.parseInt(txtMaLoaiSanPham_LoaiSanPham.getText()));
            lsp.setTenLoaiSanPham(txtTenLoaiSanPham_LoaiSanPham.getText());
            loaiSanPhamService.edit(lsp, lsp.getMaLoaiSanPham());

            ThongBao("Sửa thành công", "Thành công", 3);
            renderLoaiSanPham();
            txtMaLoaiSanPham_LoaiSanPham.setText("");
            txtTenLoaiSanPham_LoaiSanPham.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSua_LoaiSanPhamActionPerformed

    private void btnThem_LoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_LoaiSanPhamActionPerformed
        if (txtTenLoaiSanPham_LoaiSanPham.getText().equals("")) {
            ThongBao("Bạn nhập bị thiếu", "Lỗi", 2);
            return;
        }
        try {
            loaiSanPhamService = new LoaiSanPhamService();
            LoaiSanPhamModel lsp = new LoaiSanPhamModel();
            lsp.setTenLoaiSanPham(txtTenLoaiSanPham_LoaiSanPham.getText());

            Object index = loaiSanPhamService.save(lsp);

            ThongBao("Thêm thành công", "Thành công", 3);
            renderLoaiSanPham();
            txtMaLoaiSanPham_LoaiSanPham.setText("");
            txtTenLoaiSanPham_LoaiSanPham.setText("");
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnThem_LoaiSanPhamActionPerformed

    private void tblLoaiSanPham_LoaiSanPhamComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tblLoaiSanPham_LoaiSanPhamComponentShown

    }//GEN-LAST:event_tblLoaiSanPham_LoaiSanPhamComponentShown

    private void tblLoaiSanPham_LoaiSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLoaiSanPham_LoaiSanPhamMouseClicked
        int row = tblLoaiSanPham_LoaiSanPham.getSelectedRow();
        int MaLoaiSanPham = (int) tblLoaiSanPham_LoaiSanPham.getValueAt(row, 1);
        txtMaLoaiSanPham_LoaiSanPham.setText("" + MaLoaiSanPham);
        txtTenLoaiSanPham_LoaiSanPham.setText(tblLoaiSanPham_LoaiSanPham.getValueAt(row, 2).toString());
        renderMaLoaiSanPham(MaLoaiSanPham);

    }//GEN-LAST:event_tblLoaiSanPham_LoaiSanPhamMouseClicked

    private void jPanelSanPhamComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelSanPhamComponentShown
        return;
    }//GEN-LAST:event_jPanelSanPhamComponentShown

    private void btnDoiHinh_SanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiHinh_SanPhamActionPerformed
        HinhAnh hinhAnh = new HinhAnh();
        List<String> iconDienTu = hinhAnh.getHinhAnh();
        if (count == iconDienTu.size() - 1) {
            count = 0;
        }
        String img = iconDienTu.get(count);
        images_Anh = img;
        ImageIcon icon = new ImageIcon(img);
        jlbAnh_SanPham.setIcon(icon);
        count++;
    }//GEN-LAST:event_btnDoiHinh_SanPhamActionPerformed

    private void cbbTimKiemLoaiSanPham_SanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTimKiemLoaiSanPham_SanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbTimKiemLoaiSanPham_SanPhamActionPerformed

    private void cbbTimKiemLoaiSanPham_SanPhamItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbTimKiemLoaiSanPham_SanPhamItemStateChanged

        try {

            Object maLoaiSanPham = GetCbbSelected(cbbTimKiemLoaiSanPham_SanPham.getSelectedItem(), (ArrayList<displayvalueModel>) find_model);
            if (maLoaiSanPham == null) {
                return;
            } else {
                int id = Integer.parseInt(maLoaiSanPham.toString());
                renderMaLoaiSanPham_SanPham(id);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cbbTimKiemLoaiSanPham_SanPhamItemStateChanged

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        try {
            int viTriDongVuaBam = tblSanPham.getSelectedRow();

            txtMaSanPham_SanPham.setText(tblSanPham.getValueAt(viTriDongVuaBam, 1).toString());

            txtTenSanPham_SanPham.setText(tblSanPham.getValueAt(viTriDongVuaBam, 2).toString());

            txtGiaNhap_SanPham.setText(tblSanPham.getValueAt(viTriDongVuaBam, 4).toString());

            txtGiaBan_SanPham.setText(tblSanPham.getValueAt(viTriDongVuaBam, 5).toString());

            txtTonKho_SanPham.setText(tblSanPham.getValueAt(viTriDongVuaBam, 7).toString());

            txtChuThich_SanPham.setText(tblSanPham.getValueAt(viTriDongVuaBam, 9).toString());

            setSelectedCombobox(tblSanPham.getValueAt(viTriDongVuaBam, 3).toString(), cbbMaLoaiSanPham_SanPham);

            setSelectedCombobox(tblSanPham.getValueAt(viTriDongVuaBam, 6).toString(), cbbHangSanXuat_SanPham);

            ImageIcon icon = new ImageIcon(tblSanPham.getValueAt(viTriDongVuaBam, 8).toString());
            jlbAnh_SanPham.setIcon(icon);

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnReset_SanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_SanPhamActionPerformed
        clear();
        renderSanPham();
    }//GEN-LAST:event_btnReset_SanPhamActionPerformed

    private void btnXoa_SanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_SanPhamActionPerformed
        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            ThongBao("Mời Bạn Chọn Hàng Để Xóa", "Lỗi", 2);
        }

        try {

            sanPhamService = new SanPhamService();
            SanPhamModel sanPham = new SanPhamModel();
            sanPham.setMaSanPham(Integer.parseInt(txtMaSanPham_SanPham.getText()));
            sanPham.setTenSanPham(txtTenSanPham_SanPham.getText().trim());
            sanPham.setMaLoaiSanPham(Integer.parseInt(GetCbbSelected(cbbMaLoaiSanPham_SanPham.getSelectedItem(), (ArrayList<displayvalueModel>) loaiSanPham_New_Model)));
            sanPham.setMaHangSanXuat(Integer.parseInt(GetCbbSelected(cbbHangSanXuat_SanPham.getSelectedItem(), (ArrayList<displayvalueModel>) hangSanXuat_New_Model)));
            sanPham.setGiaNhap(Long.parseLong(txtGiaNhap_SanPham.getText()));
            sanPham.setGiaBan(Long.parseLong(txtGiaBan_SanPham.getText()));
            sanPham.setTonKho(Integer.parseInt(txtTonKho_SanPham.getText()));
            String image = String.valueOf(jlbAnh_SanPham.getIcon());
            image = image.substring(image.lastIndexOf("/") + 1, image.length());
            sanPham.setImage(image);
            sanPham.setChuThich(txtChuThich_SanPham.getText());
            ThongBao("Xóa thành công", "Thành công", 3);
            sanPhamService.edit(sanPham, sanPham.getMaSanPham(), 2);
            renderSanPham();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clear();
        }
    }//GEN-LAST:event_btnXoa_SanPhamActionPerformed

    private void bntSua_SanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntSua_SanPhamActionPerformed

        int row = tblSanPham.getSelectedRow();
        if (row == -1) {
            ThongBao("Mời Bạn Chọn Hàng Để Nhập", "Lỗi", 2);
            return;
        }
        if (txtTenSanPham_SanPham.getText().equals("") || txtGiaNhap_SanPham.getText().equals("") || txtGiaBan_SanPham.equals("") || txtTonKho_SanPham.getText().equals("")) {
            ThongBao("Bạn Nhập Bị Thiết", "Lỗi", 3);
            return;
        }
        if (!txtGiaNhap_SanPham.getText().matches("^\\d{0,20}$") || !txtGiaBan_SanPham.getText().matches("^\\d{0,20}$") || !txtTonKho_SanPham.getText().matches("^\\d{0,20}$")) {
            ThongBao("Tiền phải là số không phải là chữ", "Lỗi", 3);
            return;
        }
        try {

            sanPhamService = new SanPhamService();
            SanPhamModel sanPham = new SanPhamModel();
            sanPham.setMaSanPham(Integer.parseInt(txtMaSanPham_SanPham.getText()));
            sanPham.setTenSanPham(txtTenSanPham_SanPham.getText().trim());
            sanPham.setMaLoaiSanPham(Integer.parseInt(GetCbbSelected(cbbMaLoaiSanPham_SanPham.getSelectedItem(), (ArrayList<displayvalueModel>) loaiSanPham_New_Model)));
            sanPham.setMaHangSanXuat(Integer.parseInt(GetCbbSelected(cbbHangSanXuat_SanPham.getSelectedItem(), (ArrayList<displayvalueModel>) hangSanXuat_New_Model)));
            sanPham.setGiaNhap(Long.parseLong(txtGiaNhap_SanPham.getText()));
            sanPham.setGiaBan(Long.parseLong(txtGiaBan_SanPham.getText()));
            sanPham.setTonKho(Integer.parseInt(txtTonKho_SanPham.getText()));
            String image = String.valueOf(jlbAnh_SanPham.getIcon());
            image = image.substring(image.lastIndexOf("/") + 1, image.length());
            sanPham.setImage(image);
            sanPham.setChuThich(txtChuThich_SanPham.getText());
            ThongBao("Sửa thành công", "Thành công", 3);
            sanPhamService.edit(sanPham, sanPham.getMaSanPham(), 1);
            renderSanPham();

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clear();
        }
    }//GEN-LAST:event_bntSua_SanPhamActionPerformed

    private void btnThem_SanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_SanPhamActionPerformed
        if (txtTenSanPham_SanPham.getText().equals("") || txtGiaNhap_SanPham.getText().equals("") || txtGiaBan_SanPham.equals("") || txtTonKho_SanPham.getText().equals("")) {
            ThongBao("Bạn Nhập Bị Thiết", "Lỗi", 3);
            return;
        }
        if (!txtGiaNhap_SanPham.getText().matches("^\\d{0,20}$") || !txtGiaBan_SanPham.getText().matches("^\\d{0,20}$") || !txtTonKho_SanPham.getText().matches("^\\d{0,20}$")) {
            ThongBao("Tiền phải là số không phải là chữ", "Lỗi", 3);
            return;
        }

        try {
            sanPhamService = new SanPhamService();
            SanPhamModel sanPham = new SanPhamModel();
            sanPham.setTenSanPham(txtTenSanPham_SanPham.getText().trim());
            sanPham.setMaLoaiSanPham(Integer.parseInt(GetCbbSelected(cbbMaLoaiSanPham_SanPham.getSelectedItem(), (ArrayList<displayvalueModel>) loaiSanPham_New_Model)));

            sanPham.setMaHangSanXuat(Integer.parseInt(GetCbbSelected(cbbHangSanXuat_SanPham.getSelectedItem(), (ArrayList<displayvalueModel>) hangSanXuat_New_Model)));
            sanPham.setGiaNhap(Long.parseLong(txtGiaNhap_SanPham.getText()));
            sanPham.setGiaBan(Long.parseLong(txtGiaBan_SanPham.getText()));
            sanPham.setTonKho(Integer.parseInt(txtTonKho_SanPham.getText()));
            String image = String.valueOf(jlbAnh_SanPham.getIcon());
            image = image.substring(image.lastIndexOf("/") + 1, image.length());
            sanPham.setImage(image);
            sanPham.setChuThich(txtChuThich_SanPham.getText());

            Object test = sanPhamService.save(sanPham);
            renderSanPham();
            ThongBao("Thêm thành công", "Thành công", 3);
            clear();
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_btnThem_SanPhamActionPerformed

    private void cbbMaLoaiSanPham_SanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMaLoaiSanPham_SanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbMaLoaiSanPham_SanPhamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntSua_SanPham;
    private javax.swing.JButton btnDoiHinh_SanPham;
    private javax.swing.JButton btnReset_SanPham;
    private javax.swing.JButton btnSua_LoaiSanPham;
    private javax.swing.JButton btnThem_LoaiSanPham;
    private javax.swing.JButton btnThem_SanPham;
    private javax.swing.JButton btnXoa_LoaiSanPham;
    private javax.swing.JButton btnXoa_SanPham;
    private javax.swing.JComboBox<String> cbbHangSanXuat_SanPham;
    private javax.swing.JComboBox<String> cbbMaLoaiSanPham_SanPham;
    private javax.swing.JComboBox<String> cbbTimKiemLoaiSanPham_SanPham;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelLoaiSanPham;
    private javax.swing.JPanel jPanelSanPham;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPaneQuanLySanPham;
    private javax.swing.JLabel jlbAnh_SanPham;
    private javax.swing.JLabel labelHinhAnh;
    private javax.swing.JLabel lblGiaBan;
    private javax.swing.JLabel lblGiaNhap_SanPham;
    private javax.swing.JLabel lblMaSanPham_SanPham;
    private javax.swing.JLabel lblTenSanPham_SanPham;
    private javax.swing.JLabel lblTonKho_SanPham;
    private javax.swing.JTable tblLoaiSanPham_LoaiSanPham;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblSanPham_LoaiSanPham;
    private javax.swing.JTextArea txtChuThich_SanPham;
    private javax.swing.JTextField txtGiaBan_SanPham;
    private javax.swing.JTextField txtGiaNhap_SanPham;
    private javax.swing.JTextField txtMaLoaiSanPham_LoaiSanPham;
    private javax.swing.JTextField txtMaSanPham_SanPham;
    private javax.swing.JTextField txtTenLoaiSanPham_LoaiSanPham;
    private javax.swing.JTextField txtTenSanPham_SanPham;
    private javax.swing.JTextField txtTonKho_SanPham;
    // End of variables declaration//GEN-END:variables
}
