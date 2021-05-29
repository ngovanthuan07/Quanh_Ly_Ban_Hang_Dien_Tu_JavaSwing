/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.model;

/**
 *
 * @author ngova
 */
public class ChiTietHoaDonModel extends SanPhamModel{
    private int MaCTHD;
    private int MaHoaDon;
    private int SoLuong;
    private long TongTien;
    private String GhiChu;

    public int getMaCTHD() {
        return MaCTHD;
    }

    public void setMaCTHD(int MaCTHD) {
        this.MaCTHD = MaCTHD;
    }

    public int getMaHoaDon() {
        return MaHoaDon;
    }

    public void setMaHoaDon(int MaHoaDon) {
        this.MaHoaDon = MaHoaDon;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public long getTongTien() {
        return TongTien;
    }

    public void setTongTien(long TongTien) {
        this.TongTien = TongTien;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
    
}
