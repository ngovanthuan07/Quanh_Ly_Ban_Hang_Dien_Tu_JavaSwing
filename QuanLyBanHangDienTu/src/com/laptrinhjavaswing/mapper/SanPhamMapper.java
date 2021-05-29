/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.SanPhamModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class SanPhamMapper implements RowMapper<SanPhamModel>{

    @Override
    public SanPhamModel mapRow(ResultSet rs) {
         try {
            SanPhamModel sp = new SanPhamModel();
            sp.setMaSanPham(rs.getInt("MaSanPham"));
            sp.setTenSanPham(rs.getString("TenSanPham"));
            sp.setTenHangSanXuat(rs.getString("TenHangSanXuat"));
            sp.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
            sp.setGiaNhap(rs.getLong("GiaNhap"));
            sp.setGiaBan(rs.getLong("GiaBan"));
            sp.setTonKho(rs.getInt("TonKho"));
            sp.setImage(rs.getString("Image"));
            sp.setChuThich(rs.getString("ChuThich"));
            sp.setMaLoaiSanPham(rs.getInt("MaLoaiSanPham"));
            return sp;
        } catch (Exception e) {
            return null;
        }
    }
    
}
