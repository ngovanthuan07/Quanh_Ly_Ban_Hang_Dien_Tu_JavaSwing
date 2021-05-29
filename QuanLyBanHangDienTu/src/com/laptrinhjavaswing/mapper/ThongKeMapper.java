/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.ThongKeModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class ThongKeMapper implements RowMapper<ThongKeModel> {

    @Override
    public ThongKeModel mapRow(ResultSet rs) {
        try {
            ThongKeModel thongKe = new ThongKeModel();
            thongKe.setNam(rs.getString("Nam"));
            thongKe.setDoanhThu(rs.getLong("DoanhThu"));
            return thongKe;
        } catch (Exception e) {
            return null;
        }
    }

}
