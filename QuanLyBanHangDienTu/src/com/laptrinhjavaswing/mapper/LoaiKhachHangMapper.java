/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.LoaiKhachHangModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class LoaiKhachHangMapper implements RowMapper<LoaiKhachHangModel>{

    @Override
    public LoaiKhachHangModel mapRow(ResultSet rs) {
        try {
            LoaiKhachHangModel lkhmd = new LoaiKhachHangModel();
            lkhmd.setMaLoaiKhachHang(rs.getInt("MaLoaiKhachHang"));
            lkhmd.setTenLoaiKhachHang(rs.getString("TenLoaiKhachHang"));
            lkhmd.setGhiChu(rs.getString("GhiChu"));
            return lkhmd;
        } catch (Exception e) {
            return null;
        }
    }
    
}
