/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.service.impl.NhanVienModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class NhanVienMapper implements RowMapper<NhanVienModel>{

    @Override
    public NhanVienModel mapRow(ResultSet rs) {
        try {
            NhanVienModel nhanvien = new NhanVienModel();
            nhanvien.setMaNhanVien(rs.getInt("MaNhanVien"));
            nhanvien.setTenNhanVien(rs.getString("TenNhanVien"));
            nhanvien.setNgaySinh(rs.getString("NgaySinh"));
            nhanvien.setGioiTinh(rs.getByte("GioiTinh"));
            nhanvien.setNgayVaoLam(rs.getString("NgayVaoLam"));
            nhanvien.setTenChucVu(rs.getString("TenChucVu"));
            nhanvien.setDiaChi(rs.getString("DiaChi"));
            nhanvien.setSoDT(rs.getString("SoDT"));
            return nhanvien;
        } catch (Exception e) {
            return null;
        }
    }
    
}
