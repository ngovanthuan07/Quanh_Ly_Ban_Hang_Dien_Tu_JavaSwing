/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.ILoaiSanPhamDAO;
import com.laptrinhjavaswing.dao.impl.LoaiSanPhamDAO;
import com.laptrinhjavaswing.service.ILoaiSanPhamService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class LoaiSanPhamService implements ILoaiSanPhamService{

    private ILoaiSanPhamDAO iLoaiSanPhamDAO;
    public LoaiSanPhamService(){
        iLoaiSanPhamDAO = new LoaiSanPhamDAO();
    }
    
    @Override
    public List<LoaiSanPhamModel> findAll() {
        return iLoaiSanPhamDAO.findAll();
    }

    @Override
    public Object save(LoaiSanPhamModel lsp) {
        return iLoaiSanPhamDAO.save(lsp);
    }

    @Override
    public void edit(LoaiSanPhamModel lsp, int maLoaiSanPham) {
        iLoaiSanPhamDAO.edit(lsp, maLoaiSanPham);
    }
    
}
