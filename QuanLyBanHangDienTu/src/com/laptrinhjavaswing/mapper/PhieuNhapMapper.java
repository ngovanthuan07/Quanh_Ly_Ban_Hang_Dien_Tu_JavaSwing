/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.PhieuNhapModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class PhieuNhapMapper implements RowMapper<PhieuNhapModel>{

    @Override
    public PhieuNhapModel mapRow(ResultSet rs) {
        try {
            PhieuNhapModel pn = new PhieuNhapModel();
            pn.setMaPhieuNhap(rs.getInt("MaPhieuNhap"));
            pn.setTenNhanVien(rs.getString("TenNhanVien"));
            pn.setTenNhaPhanPhoi(rs.getString("TenNhaPhanPhoi"));
            pn.setTongTien(rs.getLong("TongTien"));
            pn.setNgayNhap(rs.getTimestamp("NgayNhap"));
            pn.setChuThich(rs.getString("ChuThich"));
            return pn;
        } catch (Exception e) {
            return null;
        }
    }
    
}
