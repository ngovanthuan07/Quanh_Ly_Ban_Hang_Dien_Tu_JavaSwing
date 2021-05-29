/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.INhaPhanPhoiDAO;
import com.laptrinhjavaswing.mapper.NhaPhanPhoiMapper;
import com.laptrinhjavaswing.model.NhaPhanPhoiModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class NhaPhanPhoiDAO extends AbstractDAO<NhaPhanPhoiModel> implements INhaPhanPhoiDAO{

    @Override
    public List<NhaPhanPhoiModel> findAll() {
        String sql = "select * from NhaPhanPhoi";
        return query(sql, new NhaPhanPhoiMapper());
    }
    
}
