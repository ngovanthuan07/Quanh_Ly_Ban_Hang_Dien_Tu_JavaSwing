/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.IChucVuDAO;
import com.laptrinhjavaswing.mapper.ChucVuMapper;
import com.laptrinhjavaswing.model.ChucVuModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class ChucVuDAO extends AbstractDAO<ChucVuModel> implements IChucVuDAO {

    @Override
    public List<ChucVuModel> findAll() {
        String sql = "select * from ChucVu";
        return query(sql, new ChucVuMapper());
    }

    @Override
    public String save(ChucVuModel chucVuModel) {
        String sql = "insert into ChucVu(TenChucVu,GhiChu) values (?,?)";
        return (String) insert(sql, chucVuModel.getTenChucVu(),chucVuModel.getGhiChu());
    }

    @Override
    public void edit(ChucVuModel chucVuModel, int maChucVu) {
        String sql = "update ChucVu set TenChucVu = ?, GhiChu = ? where MaChucVu = ?";
        update(sql, chucVuModel.getTenChucVu(), chucVuModel.getGhiChu(), maChucVu);
    }

}
