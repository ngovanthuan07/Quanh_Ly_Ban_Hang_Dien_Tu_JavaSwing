/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.NhaPhanPhoiModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class NhaPhanPhoiMapper implements RowMapper<NhaPhanPhoiModel>{

    @Override
    public NhaPhanPhoiModel mapRow(ResultSet rs) {
    try {
            NhaPhanPhoiModel npp = new NhaPhanPhoiModel();
            npp.setMaNhaPhanPhoi(rs.getInt("MaNhaPhanPhoi"));
            npp.setTenNhaPhanPhoi(rs.getString("TenNhaPhanPhoi"));
            npp.setDiaChi(rs.getString("DiaChi"));
            npp.setSDT(rs.getString("SDT"));
            npp.setEmail(rs.getString("Email"));
            npp.setChuThich(rs.getString("ChuThich"));
            return npp;
        } catch (Exception e) {
            return null;
        }
    }
    
}
