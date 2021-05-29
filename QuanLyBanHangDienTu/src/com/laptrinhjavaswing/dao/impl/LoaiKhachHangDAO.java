/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.ILoaiKhachHangDAO;
import com.laptrinhjavaswing.mapper.LoaiKhachHangMapper;
import com.laptrinhjavaswing.model.LoaiKhachHangModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class LoaiKhachHangDAO extends AbstractDAO<LoaiKhachHangModel> implements ILoaiKhachHangDAO{

    @Override
    public List<LoaiKhachHangModel> findAll() {
        String sql = "select * from LoaiKhachHang";
        
        return query(sql, new LoaiKhachHangMapper());
    }
    
}
