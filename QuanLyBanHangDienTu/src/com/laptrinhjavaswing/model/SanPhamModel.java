/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.model;

import com.laptrinhjavaswing.service.impl.LoaiSanPhamModel;

/**
 *
 * @author ngova
 */
public class SanPhamModel extends LoaiSanPhamModel{
    private int MaSanPham;
    private String TenSanPham;
    private String TenHangSanXuat;
    private int MaHangSanXuat;

    public int getMaHangSanXuat() {
        return MaHangSanXuat;
    }

    public void setMaHangSanXuat(int MaHangSanXuat) {
        this.MaHangSanXuat = MaHangSanXuat;
    }
    private Long GiaNhap;
    private Long GiaBan;
    private int TonKho;
    private String Image;
    private String ChuThich;
    
    public int getMaSanPham() {
        return MaSanPham;
    }

    public void setMaSanPham(int MaSanPham) {
        this.MaSanPham = MaSanPham;
    }

    public String getTenSanPham() {
        return TenSanPham;
    }

    public void setTenSanPham(String TenSanPham) {
        this.TenSanPham = TenSanPham;
    }

    public String getTenHangSanXuat() {
        return TenHangSanXuat;
    }

    public void setTenHangSanXuat(String TenHangSanXuat) {
        this.TenHangSanXuat = TenHangSanXuat;
    }

    public Long getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(Long GiaNhap) {
        this.GiaNhap = GiaNhap;
    }

    public Long getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Long GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getTonKho() {
        return TonKho;
    }

    public void setTonKho(int TonKho) {
        this.TonKho = TonKho;
    }


    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getChuThich() {
        return ChuThich;
    }

    public void setChuThich(String ChuThich) {
        this.ChuThich = ChuThich;
    }
    
    
}
