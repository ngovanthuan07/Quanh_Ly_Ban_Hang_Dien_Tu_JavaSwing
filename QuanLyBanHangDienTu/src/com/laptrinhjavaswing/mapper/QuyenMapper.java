/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.QuyenModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class QuyenMapper implements RowMapper<QuyenModel>{

    @Override
    public QuyenModel mapRow(ResultSet rs) {
        try {
            QuyenModel q = new QuyenModel();
            q.setMaQuyen(rs.getInt("MaQuyen"));
            q.setTenQuyen(rs.getString("TenQuyen"));
            q.setChuThich(rs.getString("ChuThich"));
            return q;
        } catch (Exception e) {
            return null;
        }
    }
    
}
