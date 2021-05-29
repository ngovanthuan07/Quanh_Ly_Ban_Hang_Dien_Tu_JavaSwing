/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.HoaDonModel;
import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 * @author ngova
 */
public class HoaDonMapper implements RowMapper<HoaDonModel>{

    @Override
    public HoaDonModel mapRow(ResultSet rs) {
        try {
            HoaDonModel hoadon = new HoaDonModel();
            hoadon.setMaHoaDon(rs.getInt("MaHoaDon"));
            hoadon.setTenKhachHang(rs.getString("TenKhachHang"));
            hoadon.setTenNhanVien(rs.getString("TenNhanVien"));
            hoadon.setNgayLapHoaDon(rs.getString("NgayLapHoaDon"));
            hoadon.setTongTien(rs.getLong("TongTien"));
            hoadon.setGhiChu(rs.getString("GhiChu"));
//            hoadon.setMaNhanVien(rs.getInt("MaKhachHang"));
//            hoadon.setMaKhachHang(rs.getInt("MaKhachHang"));
            return hoadon;
        } catch (Exception e) {
            return null;
        }
    }
    
}
