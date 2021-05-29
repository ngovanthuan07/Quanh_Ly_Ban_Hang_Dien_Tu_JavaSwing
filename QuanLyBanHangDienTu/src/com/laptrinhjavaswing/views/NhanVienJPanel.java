/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.views;

import com.laptrinhjavaswing.model.ChucVuModel;
import com.laptrinhjavaswing.model.QuyenModel;
import com.laptrinhjavaswing.model.UserModel;
import com.laptrinhjavaswing.model.displayvalueModel;
import com.laptrinhjavaswing.service.impl.ChucVuService;
import com.laptrinhjavaswing.service.impl.NhanVienModel;
import com.laptrinhjavaswing.service.impl.NhanVienService;
import com.laptrinhjavaswing.service.impl.QuyenService;
import com.laptrinhjavaswing.service.impl.UserService;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author ngova
 */
public class NhanVienJPanel extends javax.swing.JPanel {

    NhanVienService nhanVienService;
    QuyenService quyenService;
    UserService userService;
    ChucVuService chucVuService;
    DefaultTableModel modelNhanVien, modelUsers, modelChucVu, modelNhanVien_ChucVu;
    List<displayvalueModel> chucvu_NhanVien_model, listNhanVien_model, quyen_User_model;

    public NhanVienJPanel() {
        initComponents();
        nhanVienRender();
        UserRender();
        ChucVuRender();

        quyenService = new QuyenService();
        List<QuyenModel> listQuyen = quyenService.findAll();
        quyen_User_model = new ArrayList<>();

        for (int i = 0; i < listQuyen.size(); i++) {
            QuyenModel get = listQuyen.get(i);
            cbbQuyen_TaiKhoan.addItem(get.getTenQuyen());
            quyen_User_model.add(new displayvalueModel(get.getMaQuyen(), get.getTenQuyen()));
        }

    }

    public void nhanVienRender() {
        nhanVienService = new NhanVienService();

        modelNhanVien = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblNhanVien_NhanVien.setModel(modelNhanVien);
        chucvu_NhanVien_model = new ArrayList<>();
        chucVuService = new ChucVuService();
        List<NhanVienModel> listNhanVien = nhanVienService.findAll();
        listNhanVien_model = new ArrayList<>();

        modelNhanVien.addColumn("Stt");
        modelNhanVien.addColumn("Mã Nhân Viên");
        modelNhanVien.addColumn("Tên Nhân Viên");
        modelNhanVien.addColumn("Ngày Sinh");
        modelNhanVien.addColumn("Giới Tính");
        modelNhanVien.addColumn("Ngày Vào Làm");
        modelNhanVien.addColumn("Tên Chức Vụ");
        modelNhanVien.addColumn("Địa Chỉ");
        modelNhanVien.addColumn("SĐT");
        modelNhanVien.addColumn("Ghi Chú");

        for (int i = 0; i < listNhanVien.size(); i++) {
            NhanVienModel get = listNhanVien.get(i);
            modelNhanVien.addRow(new Object[]{
                (i + 1),
                get.getMaNhanVien(),
                get.getTenNhanVien(),
                get.getNgaySinh(),
                get.getGioiTinh() == 1 ? "Nam" : "Nữ",
                get.getNgayVaoLam(),
                get.getTenChucVu(),
                get.getDiaChi(),
                get.getSoDT(),
                get.getGhiChu()
            });
            cbbTenNhanVien_TaiKhoan.addItem(get.getTenNhanVien());
            listNhanVien_model.add(new displayvalueModel(get.getMaNhanVien(), get.getTenNhanVien()));
        }
        List<ChucVuModel> cv = chucVuService.findAll();

        for (int i = 0; i < cv.size(); i++) {
            NhanVienModel get = cv.get(i);
            cbbChucVu_NhanVien.addItem(get.getTenChucVu());
            chucvu_NhanVien_model.add(new displayvalueModel(get.getMaChucVu(), get.getTenChucVu()));
        }
    }

    public void UserRender() {
        userService = new UserService();

        modelUsers = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblTaiKhoan_TaiKhoan.setModel((modelUsers));
        modelUsers.addColumn("Stt");
        modelUsers.addColumn("ID");
        modelUsers.addColumn("Tên Nhân Viên");
        modelUsers.addColumn("Tên Đăng Nhập");
        modelUsers.addColumn("password");
        modelUsers.addColumn("Quyền");
        modelUsers.addColumn("Chú Thích");

        List<UserModel> listUser = userService.findAll();

        for (int i = 0; i < listUser.size(); i++) {
            UserModel get = listUser.get(i);

            modelUsers.addRow(new Object[]{
                (i + 1),
                get.getiD(),
                get.getTenNhanVien(),
                get.getTenDangNhap(),
                get.getPassword(),
                get.getTenQuyen(),
                get.getChuThich()
            });
        }

    }

    public void ChucVuRender() {
        chucVuService = new ChucVuService();

        modelChucVu = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblChucVu_ChucVu.setModel(modelChucVu);
        modelChucVu.addColumn("Stt");
        modelChucVu.addColumn("Mã Chức Vụ");
        modelChucVu.addColumn("Tên Chức vụ");
        modelChucVu.addColumn("Ghi Chú");

        List<ChucVuModel> listChucVu = chucVuService.findAll();

        for (int i = 0; i < listChucVu.size(); i++) {
            ChucVuModel get = listChucVu.get(i);
            modelChucVu.addRow(new Object[]{
                (i + 1),
                get.getMaChucVu(),
                get.getTenChucVu(),
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

    public void clearNhanVien() {
        txtMaNhanVien_NhanVien.setText("");
        txtTenNhanVien_NhanVien.setText("");

        cbbChucVu_NhanVien.setSelectedIndex(1);

        txtDiaChi_NhanVien.setText("");
        txtSoDT_NhanVien.setText("");
        txtChuThich_NhanVien.setText("");
        nhanVienRender();
    }

    public void clearChucVu() {
        txtMaChucVu_ChucVu.setText("");
        txtTenChucVu_ChucVu.setText("");
        txtChuThich_ChucVu.setText("");
    }

    public void clearUsers() {
        txtID_TaiKhoan.setText("");
        txtUser_TaiKhoan.setText("");
        txtPassword_TaiKhoan.setText("");
        txtChuThich_TaiKhoan.setText("");

        cbbQuyen_TaiKhoan.setSelectedIndex(1);
        cbbTenNhanVien_TaiKhoan.setSelectedIndex(1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPaneNhanVien = new javax.swing.JTabbedPane();
        jPanelNhanVien = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblNhanVien_NhanVien = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        lblTenNhanVien_NhanVien = new javax.swing.JLabel();
        lblMaNhanVien_NhanVien = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        txtTenNhanVien_NhanVien = new javax.swing.JTextField();
        txtMaNhanVien_NhanVien = new javax.swing.JTextField();
        rbtnNam_NhanVien = new javax.swing.JRadioButton();
        rbtnNu_NhanVien = new javax.swing.JRadioButton();
        txtNgaySinh_NhanVien = new com.toedter.calendar.JDateChooser();
        txtNgayVaoLam_NhanVien = new com.toedter.calendar.JDateChooser();
        lblDiaChi_NhanVien = new javax.swing.JLabel();
        lblSDT_NhanVien = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        txtChuThich_NhanVien = new javax.swing.JTextArea();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        btnThem_NhanVien = new javax.swing.JButton();
        btnSua_NhanVien = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        txtTimKiem_NhanVien = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        btnReset_NhanVien = new javax.swing.JButton();
        txtDiaChi_NhanVien = new javax.swing.JTextField();
        txtSoDT_NhanVien = new javax.swing.JTextField();
        cbbChucVu_NhanVien = new javax.swing.JComboBox<>();
        jPanelChucVu = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tblChucVu_ChucVu = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtTenChucVu_ChucVu = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        txtChuThich_ChucVu = new javax.swing.JTextArea();
        btnThem_ChucVu = new javax.swing.JButton();
        btnSua_ChucVu = new javax.swing.JButton();
        btnReset_ChucVu = new javax.swing.JButton();
        txtMaChucVu_ChucVu = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblNhanVien_ChucVu = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanelTaiKhoan = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblTaiKhoan_TaiKhoan = new javax.swing.JTable();
        btnThem_TaiKhoan = new javax.swing.JButton();
        btnXoa_TaiKhoan = new javax.swing.JButton();
        btnSua_TaiKhoan = new javax.swing.JButton();
        btnReset_TaiKhoan = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtUser_TaiKhoan = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        cbbQuyen_TaiKhoan = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtChuThich_TaiKhoan = new javax.swing.JTextArea();
        jLabel41 = new javax.swing.JLabel();
        txtPassword_TaiKhoan = new javax.swing.JPasswordField();
        jLabel55 = new javax.swing.JLabel();
        txtID_TaiKhoan = new javax.swing.JTextField();
        cbbTenNhanVien_TaiKhoan = new javax.swing.JComboBox<>();

        jTabbedPaneNhanVien.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPaneNhanVien.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jTabbedPaneNhanVienComponentShown(evt);
            }
        });

        jPanelNhanVien.setBackground(new java.awt.Color(179, 230, 255));
        jPanelNhanVien.setPreferredSize(new java.awt.Dimension(1030, 600));
        jPanelNhanVien.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelNhanVienComponentShown(evt);
            }
        });

        tblNhanVien_NhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
                "STT", "Mã Nhân Viên", "Tên Nhân Viên", "Ngày Sinh ", "Giới Tính", "Ngày Vào Làm", "Chức Vụ", "Địa Chỉ", "Số ĐT", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien_NhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVien_NhanVienMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tblNhanVien_NhanVien);

        jPanel15.setBackground(new java.awt.Color(0, 204, 153));
        jPanel15.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jPanel16.setBackground(new java.awt.Color(204, 153, 255));

        lblTenNhanVien_NhanVien.setText("Tên Nhân Viên");

        lblMaNhanVien_NhanVien.setText("Mã Nhân Viên");

        jLabel45.setText("Ngày Sinh");

        jLabel46.setText("Giới Tính");

        jLabel47.setText("Ngày Vào Làm");

        txtMaNhanVien_NhanVien.setEditable(false);

        rbtnNam_NhanVien.setText("Nam");
        rbtnNam_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNam_NhanVienActionPerformed(evt);
            }
        });

        rbtnNu_NhanVien.setText("Nữ");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaNhanVien_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(lblTenNhanVien_NhanVien))
                .addGap(23, 23, 23)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTenNhanVien_NhanVien, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtMaNhanVien_NhanVien, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addComponent(rbtnNam_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(rbtnNu_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtNgaySinh_NhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                    .addComponent(txtNgayVaoLam_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 27, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaNhanVien_NhanVien)
                    .addComponent(txtMaNhanVien_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenNhanVien_NhanVien)
                    .addComponent(txtTenNhanVien_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel45)
                    .addComponent(txtNgaySinh_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel46)
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbtnNam_NhanVien)
                        .addComponent(rbtnNu_NhanVien)))
                .addGap(28, 28, 28)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(txtNgayVaoLam_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblDiaChi_NhanVien.setText("Địa Chỉ");

        lblSDT_NhanVien.setText("Số ĐT");

        txtChuThich_NhanVien.setColumns(20);
        txtChuThich_NhanVien.setRows(5);
        jScrollPane8.setViewportView(txtChuThich_NhanVien);

        jLabel51.setText("Chú Thích");

        jLabel52.setText("Chức Vụ");

        btnThem_NhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/add.png"))); // NOI18N
        btnThem_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_NhanVienActionPerformed(evt);
            }
        });

        btnSua_NhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/edit.png"))); // NOI18N
        btnSua_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_NhanVienActionPerformed(evt);
            }
        });

        jPanel17.setBackground(new java.awt.Color(102, 153, 255));

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel53.setText("Tìm Kiếm");

        txtTimKiem_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiem_NhanVienActionPerformed(evt);
            }
        });
        txtTimKiem_NhanVien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiem_NhanVienKeyPressed(evt);
            }
        });

        jToggleButton1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/search.png"))); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(24, 24, 24)))))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(txtTimKiem_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jToggleButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnReset_NhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/reset.png"))); // NOI18N
        btnReset_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_NhanVienActionPerformed(evt);
            }
        });

        txtDiaChi_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChi_NhanVienActionPerformed(evt);
            }
        });

        txtSoDT_NhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoDT_NhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblDiaChi_NhanVien)
                                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(22, 22, 22))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                            .addComponent(lblSDT_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiaChi_NhanVien)
                    .addComponent(txtSoDT_NhanVien)
                    .addComponent(cbbChucVu_NhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cbbChucVu_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel52)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDiaChi_NhanVien)
                    .addComponent(txtDiaChi_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSDT_NhanVien)
                            .addComponent(txtSoDT_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addGap(93, 93, 93))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jScrollPane8)
                                .addContainerGap())))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(btnSua_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReset_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelNhanVienLayout = new javax.swing.GroupLayout(jPanelNhanVien);
        jPanelNhanVien.setLayout(jPanelNhanVienLayout);
        jPanelNhanVienLayout.setHorizontalGroup(
            jPanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNhanVienLayout.createSequentialGroup()
                .addGroup(jPanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelNhanVienLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelNhanVienLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanelNhanVienLayout.setVerticalGroup(
            jPanelNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPaneNhanVien.addTab("Nhân Viên", jPanelNhanVien);

        jPanelChucVu.setBackground(new java.awt.Color(179, 230, 255));
        jPanelChucVu.setPreferredSize(new java.awt.Dimension(1030, 600));
        jPanelChucVu.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelChucVuComponentShown(evt);
            }
        });

        tblChucVu_ChucVu.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Chức Vụ", "Tên Chức Vụ", "Ghi Chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChucVu_ChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChucVu_ChucVuMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tblChucVu_ChucVu);

        jPanel20.setBackground(new java.awt.Color(0, 204, 153));

        jLabel25.setText("Mã Chức Vụ");

        jLabel26.setText("Tên Chức Vụ");

        jLabel27.setText("Ghi Chú");

        txtChuThich_ChucVu.setColumns(20);
        txtChuThich_ChucVu.setRows(5);
        jScrollPane13.setViewportView(txtChuThich_ChucVu);

        btnThem_ChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/add.png"))); // NOI18N
        btnThem_ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_ChucVuActionPerformed(evt);
            }
        });

        btnSua_ChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/edit.png"))); // NOI18N
        btnSua_ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_ChucVuActionPerformed(evt);
            }
        });

        btnReset_ChucVu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/reset.png"))); // NOI18N
        btnReset_ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_ChucVuActionPerformed(evt);
            }
        });

        txtMaChucVu_ChucVu.setEditable(false);
        txtMaChucVu_ChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaChucVu_ChucVuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27))
                        .addGap(66, 66, 66)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane13)
                            .addComponent(txtTenChucVu_ChucVu)
                            .addComponent(txtMaChucVu_ChucVu))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnThem_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addComponent(btnSua_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93)
                        .addComponent(btnReset_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtMaChucVu_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtTenChucVu_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua_ChucVu)
                    .addComponent(btnThem_ChucVu)
                    .addComponent(btnReset_ChucVu))
                .addGap(33, 33, 33))
        );

        tblNhanVien_ChucVu.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã Nhân Viên", "Tên Nhân Viên", "Chức Vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(tblNhanVien_ChucVu);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Chức Vụ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Nhân Viên");

        javax.swing.GroupLayout jPanelChucVuLayout = new javax.swing.GroupLayout(jPanelChucVu);
        jPanelChucVu.setLayout(jPanelChucVuLayout);
        jPanelChucVuLayout.setHorizontalGroup(
            jPanelChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelChucVuLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanelChucVuLayout.createSequentialGroup()
                .addGroup(jPanelChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelChucVuLayout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(jLabel7))
                    .addGroup(jPanelChucVuLayout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelChucVuLayout.createSequentialGroup()
                        .addGap(561, 561, 561)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelChucVuLayout.setVerticalGroup(
            jPanelChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelChucVuLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelChucVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jLabel8)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPaneNhanVien.addTab("Chức Vụ", jPanelChucVu);

        jPanelTaiKhoan.setBackground(new java.awt.Color(179, 230, 255));
        jPanelTaiKhoan.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelTaiKhoanComponentShown(evt);
            }
        });

        tblTaiKhoan_TaiKhoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "", "Mã Nhân Viên ", "Tên Nhân Viên ", "User", "Password", "Quyền", "Chú Thích"
            }
        ));
        tblTaiKhoan_TaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTaiKhoan_TaiKhoanMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblTaiKhoan_TaiKhoan);

        btnThem_TaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/add.png"))); // NOI18N
        btnThem_TaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem_TaiKhoanActionPerformed(evt);
            }
        });

        btnXoa_TaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/remove.png"))); // NOI18N
        btnXoa_TaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoa_TaiKhoanActionPerformed(evt);
            }
        });

        btnSua_TaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/edit.png"))); // NOI18N
        btnSua_TaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua_TaiKhoanActionPerformed(evt);
            }
        });

        btnReset_TaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/laptrinhjavaswing/images/reset.png"))); // NOI18N
        btnReset_TaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset_TaiKhoanActionPerformed(evt);
            }
        });

        jLabel18.setText("Tên Nhân Viên");

        jLabel21.setText("User");

        jLabel22.setText("Password");

        jLabel23.setText("Quyền");

        jLabel24.setText("Chú Thích");

        txtChuThich_TaiKhoan.setColumns(20);
        txtChuThich_TaiKhoan.setRows(5);
        jScrollPane9.setViewportView(txtChuThich_TaiKhoan);

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel41.setText("Tài Khoản");

        txtPassword_TaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassword_TaiKhoanActionPerformed(evt);
            }
        });

        jLabel55.setText("ID");

        txtID_TaiKhoan.setEditable(false);

        javax.swing.GroupLayout jPanelTaiKhoanLayout = new javax.swing.GroupLayout(jPanelTaiKhoan);
        jPanelTaiKhoan.setLayout(jPanelTaiKhoanLayout);
        jPanelTaiKhoanLayout.setHorizontalGroup(
            jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 779, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 65, Short.MAX_VALUE)
                .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelTaiKhoanLayout.createSequentialGroup()
                        .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbbTenNhanVien_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                                .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUser_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPassword_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addComponent(cbbQuyen_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(70, 70, 70))
                    .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                        .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                                .addComponent(btnThem_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(btnXoa_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                                .addComponent(btnSua_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnReset_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                .addGap(372, 372, 372)
                .addComponent(jLabel41)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTaiKhoanLayout.setVerticalGroup(
            jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(txtID_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbbTenNhanVien_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtUser_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtPassword_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(cbbQuyen_TaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnThem_TaiKhoan)
                    .addComponent(btnXoa_TaiKhoan))
                .addGap(18, 18, 18)
                .addGroup(jPanelTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSua_TaiKhoan)
                    .addComponent(btnReset_TaiKhoan))
                .addGap(173, 173, 173))
            .addGroup(jPanelTaiKhoanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel41)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPaneNhanVien.addTab("Tài Khoản", jPanelTaiKhoan);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1221, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPaneNhanVien))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 715, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jTabbedPaneNhanVien))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVien_NhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVien_NhanVienMouseClicked
        try {
            int row = tblNhanVien_NhanVien.getSelectedRow();
            txtMaNhanVien_NhanVien.setText(tblNhanVien_NhanVien.getValueAt(row, 1).toString());
            txtTenNhanVien_NhanVien.setText(tblNhanVien_NhanVien.getValueAt(row, 2).toString());

            Date date_NgaySinh_NhanVien, data_NgayVaoLam_NhanVien;
            date_NgaySinh_NhanVien = new SimpleDateFormat("yyyy-MM-dd").parse(tblNhanVien_NhanVien.getValueAt(row, 3).toString());
            txtNgaySinh_NhanVien.setDate(date_NgaySinh_NhanVien);

            data_NgayVaoLam_NhanVien = new SimpleDateFormat("yyyy-MM-dd").parse(tblNhanVien_NhanVien.getValueAt(row, 5).toString());
            txtNgayVaoLam_NhanVien.setDate(data_NgayVaoLam_NhanVien);

            String gioitinh = tblNhanVien_NhanVien.getValueAt(row, 4).toString().trim();
            if (gioitinh.equals("Nam")) {
                rbtnNam_NhanVien.setSelected(true);
                rbtnNu_NhanVien.setSelected(false);
            } else {
                rbtnNu_NhanVien.setSelected(true);
                rbtnNam_NhanVien.setSelected(false);
            }

            setSelectedCombobox(tblNhanVien_NhanVien.getValueAt(row, 6).toString(), cbbChucVu_NhanVien);
            txtDiaChi_NhanVien.setText(tblNhanVien_NhanVien.getValueAt(row, 7).toString());
            txtSoDT_NhanVien.setText(tblNhanVien_NhanVien.getValueAt(row, 8).toString());
            txtChuThich_NhanVien.setText(tblNhanVien_NhanVien.getValueAt(row, 9).toString());

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblNhanVien_NhanVienMouseClicked

    private void rbtnNam_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNam_NhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnNam_NhanVienActionPerformed

    private void btnThem_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_NhanVienActionPerformed

        if (txtTenNhanVien_NhanVien.equals("") || txtDiaChi_NhanVien.getText().equals("") || txtSoDT_NhanVien.getText().equals("")) {
            ThongBao("Bạn nhập bị thiếu", "Lỗi", 2);
            return;
        }
        if (txtSoDT_NhanVien.getText().matches("^\\d{10}$") == false) {
            ThongBao("Số điện thoại phải 10 số", "Lỗi", 2);
            return;
        }

        try {
            nhanVienService = new NhanVienService();
            NhanVienModel nv = new NhanVienModel();

            nv.setTenNhanVien(txtTenNhanVien_NhanVien.getText());

            String date_ngaySinh = new SimpleDateFormat("yyyy-MM-dd").format(txtNgaySinh_NhanVien.getDate());
            nv.setNgaySinh(date_ngaySinh);
            String date_ngayVaoLam = new SimpleDateFormat("yyyy-MM-dd").format(txtNgayVaoLam_NhanVien.getDate());
            nv.setNgayVaoLam(date_ngayVaoLam);
            if (rbtnNam_NhanVien.isSelected()) {
                nv.setGioiTinh(1);
            } else {
                nv.setGioiTinh(0);
            }
            nv.setMaChucVu(Integer.parseInt(GetCbbSelected(cbbChucVu_NhanVien.getSelectedItem(), (ArrayList<displayvalueModel>) chucvu_NhanVien_model)));
            nv.setDiaChi(txtDiaChi_NhanVien.getText());
            nv.setSoDT(txtSoDT_NhanVien.getText());
            nv.setGhiChu(txtChuThich_NhanVien.getText());
            Object index = nhanVienService.save(nv);
            ThongBao("Thêm thành công", "Thành công", 3);
            nhanVienRender();
            clearNhanVien();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnThem_NhanVienActionPerformed

    private void btnSua_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_NhanVienActionPerformed

        int row = tblNhanVien_NhanVien.getSelectedRow();

        if (row == -1) {
            ThongBao("Vui lòng chọn bản để chỉnh sửa", "Lỗi", 2);
            return;
        }
        if (txtTenNhanVien_NhanVien.equals("") || txtDiaChi_NhanVien.getText().equals("") || txtSoDT_NhanVien.getText().equals("")) {
            ThongBao("Bạn nhập bị thiếu", "Lỗi", 2);
            return;
        }
        if (txtSoDT_NhanVien.getText().matches("^\\d{10}$") == false) {
            ThongBao("Số điện thoại phải 10 số còn của bạn là" + txtSoDT_NhanVien.getText().trim().length() + " số", "Lỗi", 2);
            return;
        }

        try {
            nhanVienService = new NhanVienService();
            NhanVienModel nv = new NhanVienModel();

            nv.setMaNhanVien(Integer.parseInt(txtMaNhanVien_NhanVien.getText()));

            nv.setTenNhanVien(txtTenNhanVien_NhanVien.getText());

            String date_ngaySinh = new SimpleDateFormat("yyyy-MM-dd").format(txtNgaySinh_NhanVien.getDate());
            nv.setNgaySinh(date_ngaySinh);
            String date_ngayVaoLam = new SimpleDateFormat("yyyy-MM-dd").format(txtNgayVaoLam_NhanVien.getDate());
            nv.setNgayVaoLam(date_ngayVaoLam);
            if (rbtnNam_NhanVien.isSelected()) {
                nv.setGioiTinh(1);
            } else {
                nv.setGioiTinh(0);
            }
            nv.setMaChucVu(Integer.parseInt(GetCbbSelected(cbbChucVu_NhanVien.getSelectedItem(), (ArrayList<displayvalueModel>) chucvu_NhanVien_model)));
            nv.setDiaChi(txtDiaChi_NhanVien.getText());
            nv.setSoDT(txtSoDT_NhanVien.getText());
            nv.setGhiChu(txtChuThich_NhanVien.getText());
            nhanVienService.edit(nv, nv.getMaNhanVien());
            ThongBao("Sửa thành công", "Thành công", 3);
            nhanVienRender();
            clearNhanVien();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSua_NhanVienActionPerformed

    private void btnReset_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_NhanVienActionPerformed
        txtMaNhanVien_NhanVien.setText("");
        txtTenNhanVien_NhanVien.setText("");

        cbbChucVu_NhanVien.setSelectedIndex(1);

        txtDiaChi_NhanVien.setText("");
        txtSoDT_NhanVien.setText("");
        txtChuThich_NhanVien.setText("");
        nhanVienRender();
    }//GEN-LAST:event_btnReset_NhanVienActionPerformed

    private void txtDiaChi_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChi_NhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChi_NhanVienActionPerformed

    private void txtSoDT_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoDT_NhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoDT_NhanVienActionPerformed

    private void jPanelNhanVienComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelNhanVienComponentShown

    }//GEN-LAST:event_jPanelNhanVienComponentShown

    private void tblTaiKhoan_TaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTaiKhoan_TaiKhoanMouseClicked
        int viTriDongVuaBam = tblTaiKhoan_TaiKhoan.getSelectedRow();
        txtID_TaiKhoan.setText(tblTaiKhoan_TaiKhoan.getValueAt(viTriDongVuaBam, 1).toString());
        txtUser_TaiKhoan.setText(tblTaiKhoan_TaiKhoan.getValueAt(viTriDongVuaBam, 3).toString());
        txtPassword_TaiKhoan.setText(tblTaiKhoan_TaiKhoan.getValueAt(viTriDongVuaBam, 4).toString());
        txtChuThich_TaiKhoan.setText(tblTaiKhoan_TaiKhoan.getValueAt(viTriDongVuaBam, 6).toString());
        setSelectedCombobox(tblTaiKhoan_TaiKhoan.getValueAt(viTriDongVuaBam, 2).toString(), cbbTenNhanVien_TaiKhoan);
        setSelectedCombobox(tblTaiKhoan_TaiKhoan.getValueAt(viTriDongVuaBam, 5).toString(), cbbQuyen_TaiKhoan);

    }//GEN-LAST:event_tblTaiKhoan_TaiKhoanMouseClicked

    private void btnThem_TaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_TaiKhoanActionPerformed

        try {
            if (txtUser_TaiKhoan.getText().equals("") || String.valueOf(txtPassword_TaiKhoan.getPassword()).equals("")) {
                ThongBao("Bạn Nhập Thiếu", "Lỗi", 2);
                return;
            }
            userService = new UserService();
            UserModel user = new UserModel();
            user.setMaNhanVien(Integer.parseInt(GetCbbSelected(cbbTenNhanVien_TaiKhoan.getSelectedItem(), (ArrayList<displayvalueModel>) listNhanVien_model)));
            user.setTenDangNhap(txtUser_TaiKhoan.getText());
            user.setPassword(String.valueOf(txtPassword_TaiKhoan.getPassword()));
            user.setMaQuyen(Integer.parseInt(GetCbbSelected(cbbQuyen_TaiKhoan.getSelectedItem(), (ArrayList<displayvalueModel>) quyen_User_model)));
            user.setChuThich(txtChuThich_TaiKhoan.getText());
            Object index = userService.save(user);
            ThongBao("Thêm Thành Công", "Thành Công", 3);
            UserRender();
            clearUsers();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnThem_TaiKhoanActionPerformed

    private void btnSua_TaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_TaiKhoanActionPerformed
        try {
            int row = tblTaiKhoan_TaiKhoan.getSelectedRow();
            if (row == -1) {
                ThongBao("Mời Bạn Chọn Vào Bảng Để chỉnh Sửa", "Lỗi", 2);
                return;
            }

            if (txtUser_TaiKhoan.getText().equals("") || String.valueOf(txtPassword_TaiKhoan.getPassword()).equals("")) {
                ThongBao("Bạn Nhập Thiếu", "Lỗi", 2);
                return;
            }
            userService = new UserService();
            UserModel user = new UserModel();
            user.setiD(Integer.parseInt(txtID_TaiKhoan.getText()));
            user.setMaNhanVien(Integer.parseInt(GetCbbSelected(cbbTenNhanVien_TaiKhoan.getSelectedItem(), (ArrayList<displayvalueModel>) listNhanVien_model)));
            user.setTenDangNhap(txtUser_TaiKhoan.getText());
            user.setPassword(String.valueOf(txtPassword_TaiKhoan.getPassword()));
            user.setMaQuyen(Integer.parseInt(GetCbbSelected(cbbQuyen_TaiKhoan.getSelectedItem(), (ArrayList<displayvalueModel>) quyen_User_model)));
            user.setChuThich(txtChuThich_TaiKhoan.getText());
            userService.edit(user, user.getiD(), 1);
            ThongBao("Sửa Thành Công", "Sửa Thành Công", 3);
            UserRender();
            clearUsers();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnSua_TaiKhoanActionPerformed

    private void txtPassword_TaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassword_TaiKhoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassword_TaiKhoanActionPerformed

    private void jPanelTaiKhoanComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelTaiKhoanComponentShown
        return;
    }//GEN-LAST:event_jPanelTaiKhoanComponentShown

    private void tblChucVu_ChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChucVu_ChucVuMouseClicked
        try {
            int row = tblChucVu_ChucVu.getSelectedRow();
            txtMaChucVu_ChucVu.setText(tblChucVu_ChucVu.getValueAt(row, 1).toString());
            txtTenChucVu_ChucVu.setText(tblChucVu_ChucVu.getValueAt(row, 2).toString());
            txtChuThich_ChucVu.setText(tblChucVu_ChucVu.getValueAt(row, 3).toString());

            int maChucVu = Integer.parseInt(tblChucVu_ChucVu.getValueAt(row, 1).toString().trim());

            nhanVienService = new NhanVienService();

            modelNhanVien_ChucVu = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            tblNhanVien_ChucVu.setModel(modelNhanVien_ChucVu);

            modelNhanVien_ChucVu.addColumn("Stt");
            modelNhanVien_ChucVu.addColumn("Mã Nhân Viên");
            modelNhanVien_ChucVu.addColumn("Tên Nhân Viên");
            modelNhanVien_ChucVu.addColumn("Chức Vụ");

            List<NhanVienModel> listNhanVien = nhanVienService.findByCodeMaChucVu(maChucVu);

            if (listNhanVien != null) {
                for (int i = 0; i < listNhanVien.size(); i++) {
                    NhanVienModel get = listNhanVien.get(i);
                    modelNhanVien_ChucVu.addRow(new Object[]{
                        (i + 1),
                        get.getMaNhanVien(),
                        get.getTenNhanVien(),
                        get.getTenChucVu()
                    });
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_tblChucVu_ChucVuMouseClicked

    private void btnThem_ChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem_ChucVuActionPerformed
        if (txtTenChucVu_ChucVu.getText().equals("")) {
            ThongBao("Bạn nhập bị thiếu", "Lỗi", 2);
            return;
        }
        try {
            chucVuService = new ChucVuService();
            ChucVuModel newChucVu = new ChucVuModel();

            newChucVu.setTenChucVu(txtTenChucVu_ChucVu.getText());
            newChucVu.setGhiChu(txtChuThich_ChucVu.getText());

            String indexChucVu = chucVuService.save(newChucVu);
            ThongBao("Thành Công", "Thành Công", 3);

            ChucVuRender();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearChucVu();
        }
    }//GEN-LAST:event_btnThem_ChucVuActionPerformed

    private void btnSua_ChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua_ChucVuActionPerformed

        try {
            chucVuService = new ChucVuService();
            int row = tblChucVu_ChucVu.getSelectedRow();
            if (row == -1) {
                ThongBao("vui lòng chọn vào ô để chỉnh sửa", "lỗi", 2);
                return;
            }
            if (txtTenChucVu_ChucVu.getText().equals("")) {
                ThongBao("Bạn nhập bị thiếu", "Lỗi", 2);
                return;
            }
            ChucVuModel cv = new ChucVuModel();
            cv.setMaChucVu(Integer.parseInt(txtMaChucVu_ChucVu.getText()));
            cv.setTenChucVu((txtTenChucVu_ChucVu.getText()));
            cv.setGhiChu(txtChuThich_ChucVu.getText());
            chucVuService.edit(cv, cv.getMaChucVu());
            ThongBao("Sửa thành công", "thành công", 3);
            ChucVuRender();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            clearChucVu();
        }
    }//GEN-LAST:event_btnSua_ChucVuActionPerformed

    private void btnReset_ChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_ChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReset_ChucVuActionPerformed

    private void txtMaChucVu_ChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaChucVu_ChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaChucVu_ChucVuActionPerformed

    private void jPanelChucVuComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelChucVuComponentShown
        return;
    }//GEN-LAST:event_jPanelChucVuComponentShown

    private void jTabbedPaneNhanVienComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jTabbedPaneNhanVienComponentShown
        return;
    }//GEN-LAST:event_jTabbedPaneNhanVienComponentShown

    private void txtTimKiem_NhanVienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiem_NhanVienKeyPressed
        char c = evt.getKeyChar();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DefaultTableModel table = (DefaultTableModel) tblNhanVien_NhanVien.getModel();
            String search = txtTimKiem_NhanVien.getText().toLowerCase().trim();
            TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
            tblNhanVien_NhanVien.setRowSorter(tr);
            tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
        }
    }//GEN-LAST:event_txtTimKiem_NhanVienKeyPressed

    private void btnReset_TaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset_TaiKhoanActionPerformed
        // TODO add your handling code here:
        clearUsers();
        UserRender();
    }//GEN-LAST:event_btnReset_TaiKhoanActionPerformed

    private void btnXoa_TaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoa_TaiKhoanActionPerformed
        // TODO add your handling code here:
        try {
            int row = tblTaiKhoan_TaiKhoan.getSelectedRow();
            if (row == -1) {
                ThongBao("Mời Bạn Chọn Vào Bảng Để chỉnh Sửa", "Lỗi", 2);
                return;
            }
            userService = new UserService();
            UserModel user = new UserModel();
            user.setiD(Integer.parseInt(txtID_TaiKhoan.getText()));

            userService.edit(user, user.getiD(), 2);
            ThongBao("Xóa Thành Công", "Xóa Thành Công", 3);
            UserRender();
            clearUsers();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnXoa_TaiKhoanActionPerformed

    private void txtTimKiem_NhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiem_NhanVienActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtTimKiem_NhanVienActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel table = (DefaultTableModel) tblNhanVien_NhanVien.getModel();
        String search = txtTimKiem_NhanVien.getText().toLowerCase().trim();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(table);
        tblNhanVien_NhanVien.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + search));
    }//GEN-LAST:event_jToggleButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReset_ChucVu;
    private javax.swing.JButton btnReset_NhanVien;
    private javax.swing.JButton btnReset_TaiKhoan;
    private javax.swing.JButton btnSua_ChucVu;
    private javax.swing.JButton btnSua_NhanVien;
    private javax.swing.JButton btnSua_TaiKhoan;
    private javax.swing.JButton btnThem_ChucVu;
    private javax.swing.JButton btnThem_NhanVien;
    private javax.swing.JButton btnThem_TaiKhoan;
    private javax.swing.JButton btnXoa_TaiKhoan;
    private javax.swing.JComboBox<String> cbbChucVu_NhanVien;
    private javax.swing.JComboBox<String> cbbQuyen_TaiKhoan;
    private javax.swing.JComboBox<String> cbbTenNhanVien_TaiKhoan;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanelChucVu;
    private javax.swing.JPanel jPanelNhanVien;
    private javax.swing.JPanel jPanelTaiKhoan;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPaneNhanVien;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblDiaChi_NhanVien;
    private javax.swing.JLabel lblMaNhanVien_NhanVien;
    private javax.swing.JLabel lblSDT_NhanVien;
    private javax.swing.JLabel lblTenNhanVien_NhanVien;
    private javax.swing.JRadioButton rbtnNam_NhanVien;
    private javax.swing.JRadioButton rbtnNu_NhanVien;
    private javax.swing.JTable tblChucVu_ChucVu;
    private javax.swing.JTable tblNhanVien_ChucVu;
    private javax.swing.JTable tblNhanVien_NhanVien;
    private javax.swing.JTable tblTaiKhoan_TaiKhoan;
    private javax.swing.JTextArea txtChuThich_ChucVu;
    private javax.swing.JTextArea txtChuThich_NhanVien;
    private javax.swing.JTextArea txtChuThich_TaiKhoan;
    private javax.swing.JTextField txtDiaChi_NhanVien;
    private javax.swing.JTextField txtID_TaiKhoan;
    private javax.swing.JTextField txtMaChucVu_ChucVu;
    private javax.swing.JTextField txtMaNhanVien_NhanVien;
    private com.toedter.calendar.JDateChooser txtNgaySinh_NhanVien;
    private com.toedter.calendar.JDateChooser txtNgayVaoLam_NhanVien;
    private javax.swing.JPasswordField txtPassword_TaiKhoan;
    private javax.swing.JTextField txtSoDT_NhanVien;
    private javax.swing.JTextField txtTenChucVu_ChucVu;
    private javax.swing.JTextField txtTenNhanVien_NhanVien;
    private javax.swing.JTextField txtTimKiem_NhanVien;
    private javax.swing.JTextField txtUser_TaiKhoan;
    // End of variables declaration//GEN-END:variables
}
