/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.IQuyenDAO;
import com.laptrinhjavaswing.mapper.QuyenMapper;
import com.laptrinhjavaswing.model.QuyenModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class QuyenDAO extends AbstractDAO<QuyenModel> implements IQuyenDAO{

    @Override
    public List<QuyenModel> findAll() {
       String sql = "select * from Quyen";
       return query(sql, new QuyenMapper());
    }
    
}
