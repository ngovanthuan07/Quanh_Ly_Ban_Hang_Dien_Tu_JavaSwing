/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.HoaDonModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class HoaDonMapper_Count implements RowMapper<HoaDonModel>{

    @Override
    public HoaDonModel mapRow(ResultSet rs) {
        try {
            HoaDonModel hd = new HoaDonModel();
            hd.setSoPhieuMua(rs.getInt("SoPhieuMua"));
            return hd;
        } catch (Exception e) {
            return null;
        }
    }
    
}
