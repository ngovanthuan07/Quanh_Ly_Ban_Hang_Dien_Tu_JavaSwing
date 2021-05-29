package com.laptrinhjavaswing.controller;

import com.laptrinhjavaswing.bean.DanhMucBean;

import com.laptrinhjavaswing.views.DoiTacJPanel;
import com.laptrinhjavaswing.views.HoaDonJPanel;
import com.laptrinhjavaswing.views.KhachHangJPanel;

import com.laptrinhjavaswing.views.NhanVienJPanel;
import com.laptrinhjavaswing.views.SanPhamJPanel;
import com.laptrinhjavaswing.views.ThongKeJPanel;
import com.laptrinhjavaswing.views.TrangChuJPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovePanelController {

    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null;

    public MovePanelController(JPanel jpnRoot) {
        this.root = jpnRoot;

    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "TrangChu";
        jpnItem.setBackground(new Color(96, 100, 191));
        jlbItem.setBackground(new Color(96, 199, 191));
        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChuJPanel());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LableEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LableEvent implements MouseListener {

        private JPanel node;

        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LableEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "HoaDon":
                    node = new HoaDonJPanel();
                    break;
                case "SanPham":
                    node = new SanPhamJPanel();
                    break;
                case "KhachHang":
                    node = new KhachHangJPanel();
                    break;
                case "NhanVien":
                    node = new NhanVienJPanel();
                    break;
                case "DoiTac":
                    node = new DoiTacJPanel();
                    break;
                case "DoanhThu":
                    node = new ThongKeJPanel();
                    break;
                default:
                    node = new TrangChuJPanel();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            setChangeBackgroud(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
           if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(102,204,255));
                jlbItem.setBackground(new Color(102,204,255));
            }
        }

    }

    private void setChangeBackgroud(String kind) {
        for (DanhMucBean item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJpn().setBackground(new Color(96, 100, 191));
                item.getJlb().setBackground(new Color(96, 100, 191));
            }else{
                item.getJpn().setBackground(new Color(102,204,255));
                item.getJlb().setBackground(new Color(102,204,255));  
            }
        }
    }
}
