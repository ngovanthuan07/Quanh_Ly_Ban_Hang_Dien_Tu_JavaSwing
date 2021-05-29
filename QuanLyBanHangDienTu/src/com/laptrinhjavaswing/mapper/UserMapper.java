/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.mapper;

import com.laptrinhjavaswing.model.UserModel;
import java.sql.ResultSet;

/**
 *
 * @author ngova
 */
public class UserMapper implements RowMapper<UserModel>{

    @Override
    public UserModel mapRow(ResultSet rs) {        
        try {
            UserModel user = new UserModel();
            user.setiD(rs.getInt("ID"));
            user.setTenNhanVien(rs.getString("TenNhanVien"));
            user.setTenDangNhap(rs.getString("TenDangNhap"));
            user.setPassword(rs.getString("Password"));
            user.setTenQuyen(rs.getString("TenQuyen"));
            user.setChuThich(rs.getString("ChuThich"));
            return user;
        } catch (Exception e) {
            return null;
        }
    }
    
}
