/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.service.impl.LoaiSanPhamModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class LoaiSanPhamMapper implements RowMapper<LoaiSanPhamModel>{

    @Override
    public LoaiSanPhamModel mapRow(ResultSet rs) {
          try {
            LoaiSanPhamModel loaiSanPhamModel = new LoaiSanPhamModel();
            loaiSanPhamModel.setMaLoaiSanPham(rs.getInt("MaLoaiSanPham"));
            loaiSanPhamModel.setTenLoaiSanPham(rs.getString("TenLoaiSanPham"));
            return loaiSanPhamModel;
        } catch (Exception e) {
            return null;
        }
    }
    
}
