/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.IPhieuNhapDAO;
import com.laptrinhjavaswing.mapper.PhieuNhapMapper;
import com.laptrinhjavaswing.model.PhieuNhapModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class PhieuNhapDAO extends AbstractDAO<PhieuNhapModel> implements IPhieuNhapDAO{

    @Override
    public List<PhieuNhapModel> findAll() {
        String sql = "select * from PhieuNhap as pn, NhanVien as nv, NhaPhanPhoi as npp\n" +
        "where pn.MaNhanVien = nv.MaNhanVien and pn.MaNhaPhanPhoi = npp.MaNhaPhanPhoi";
        
        return query(sql, new PhieuNhapMapper());
    }
    
}
