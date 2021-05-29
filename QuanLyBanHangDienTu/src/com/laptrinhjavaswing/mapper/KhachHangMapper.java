/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.KhachHangModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class KhachHangMapper implements RowMapper<KhachHangModel>{

    @Override
    public KhachHangModel mapRow(ResultSet rs) {
        try {
            KhachHangModel khachhang = new KhachHangModel();
            khachhang.setMaKhachHang(rs.getInt("MaKhachHang"));
            khachhang.setTenKhachHang(rs.getString("TenKhachHang"));
            khachhang.setNgaySinh(rs.getString("Ngaysinh"));
            khachhang.setGioiTinh(rs.getInt("GioiTinh"));
            khachhang.setDiaChi(rs.getString("DiaChi"));
            khachhang.setSdt(rs.getString("SDT"));
            khachhang.setLoaiKhachHang(rs.getString("TenLoaiKhachHang"));
            khachhang.setGhiChu(rs.getString("GhiChu"));
            khachhang.setMaLoaiKhachHang(rs.getInt("LoaiKhachHang"));
            return khachhang;
        } catch (Exception e) {
            return null;
        }
    }
    
}
