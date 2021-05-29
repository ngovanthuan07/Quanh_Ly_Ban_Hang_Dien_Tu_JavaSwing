/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.ChiTietHoaDonModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class ChiTietHoaDonMapper implements RowMapper<ChiTietHoaDonModel> {

    @Override
    public ChiTietHoaDonModel mapRow(ResultSet rs) {
        try {
            ChiTietHoaDonModel cthd = new ChiTietHoaDonModel();
            cthd.setMaCTHD(rs.getInt("MaCTHD"));
            cthd.setMaHoaDon(rs.getInt("MaHoaDon"));
            cthd.setTenSanPham(rs.getString("TenSanPham"));
            cthd.setSoLuong(rs.getInt("SoLuong"));
            cthd.setTongTien(rs.getLong("TongTien"));
            cthd.setGhiChu(rs.getString("GhiChu"));
            return cthd;
        } catch (Exception e) {
            return null;
        }
    }

}
