/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.HangSanXuatModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class HangSanXuatMapper implements RowMapper<HangSanXuatModel>{

    @Override
    public HangSanXuatModel mapRow(ResultSet rs) {
        try {
            HangSanXuatModel hsx = new HangSanXuatModel();
            hsx.setMaHangSanXuat(rs.getInt("MaHangSanXuat"));
            hsx.setTenHangSanXuat(rs.getString("TenHangSanXuat"));
            
            return hsx;
            
        } catch (Exception e) {
            return null;
        }
    }


    
}
