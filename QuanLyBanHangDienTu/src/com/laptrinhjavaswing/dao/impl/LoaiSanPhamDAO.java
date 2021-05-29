/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.ILoaiSanPhamDAO;
import com.laptrinhjavaswing.mapper.LoaiSanPhamMapper;
import com.laptrinhjavaswing.service.impl.LoaiSanPhamModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class LoaiSanPhamDAO extends AbstractDAO<LoaiSanPhamModel> implements ILoaiSanPhamDAO{

    @Override
    public List<LoaiSanPhamModel> findAll() {
        String sql = "select * from LoaiSanPham ";
        return query(sql, new LoaiSanPhamMapper());
    }

    @Override
    public Object save(LoaiSanPhamModel lsp) {
        String sql = "insert into LoaiSanPham(TenLoaiSanPham) values(?)";
        return insert(sql, lsp.getTenLoaiSanPham());
    }

    @Override
    public void edit(LoaiSanPhamModel lsp, int maLoaiSanPham) {
        String sql = "update LoaiSanPham set TenLoaiSanPham = ? where MaLoaiSanPham = ?";
        update(sql, lsp.getTenLoaiSanPham(),maLoaiSanPham);
    }
    
}
