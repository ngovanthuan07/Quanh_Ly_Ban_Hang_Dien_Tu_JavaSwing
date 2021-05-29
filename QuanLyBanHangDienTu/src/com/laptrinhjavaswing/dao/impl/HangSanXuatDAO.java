/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.IHangSanXuatDAO;
import com.laptrinhjavaswing.mapper.HangSanXuatMapper;
import com.laptrinhjavaswing.model.HangSanXuatModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class HangSanXuatDAO extends AbstractDAO<HangSanXuatModel> implements IHangSanXuatDAO{

    @Override
    public List<HangSanXuatModel> findAll() {
        String sql = "select * from HangSanXuat";
        
        return query(sql, new HangSanXuatMapper());
    }
    
}
