/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.model;

import java.sql.Timestamp;

/**
 *
 * @author ngova
 */
public class PhieuNhapModel {
    private int MaPhieuNhap;
    private String TenNhanVien;
    private String TenNhaPhanPhoi;
    private long TongTien;
    private Timestamp NgayNhap;
    private String ChuThich;

    public int getMaPhieuNhap() {
        return MaPhieuNhap;
    }

    public void setMaPhieuNhap(int MaPhieuNhap) {
        this.MaPhieuNhap = MaPhieuNhap;
    }

    public String getTenNhanVien() {
        return TenNhanVien;
    }

    public void setTenNhanVien(String TenNhanVien) {
        this.TenNhanVien = TenNhanVien;
    }

    public String getTenNhaPhanPhoi() {
        return TenNhaPhanPhoi;
    }

    public void setTenNhaPhanPhoi(String TenNhaPhanPhoi) {
        this.TenNhaPhanPhoi = TenNhaPhanPhoi;
    }

    public long getTongTien() {
        return TongTien;
    }

    public void setTongTien(long TongTien) {
        this.TongTien = TongTien;
    }

    public Timestamp getNgayNhap() {
        return NgayNhap;
    }

    public void setNgayNhap(Timestamp NgayNhap) {
        this.NgayNhap = NgayNhap;
    }

    public String getChuThich() {
        return ChuThich;
    }

    public void setChuThich(String ChuThich) {
        this.ChuThich = ChuThich;
    }
    
    
    
    
}
