/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.ISanPhamDAO;
import com.laptrinhjavaswing.dao.impl.SanPhamDAO;
import com.laptrinhjavaswing.model.SanPhamModel;
import com.laptrinhjavaswing.service.ISanPhamService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class SanPhamService implements ISanPhamService{

    public ISanPhamDAO iSanPhamDAO;
    public SanPhamService(){
        iSanPhamDAO = new SanPhamDAO();
    }
    
    @Override
    public List<SanPhamModel> findAll() {
        return iSanPhamDAO.findAll();
    }

    @Override
    public List<SanPhamModel> findByCodeLoaiSanPham(int MaLoaiSanPham) {
        return iSanPhamDAO.findByCodeLoaiSanPham(MaLoaiSanPham);
    }

    @Override
    public List<SanPhamModel> findByCodeMaSanPham(int maSanPham) {
        return iSanPhamDAO.findByCodeMaSanPham(maSanPham);
    }

    @Override
    public Object save(SanPhamModel hoaDonModel) {
        return iSanPhamDAO.save(hoaDonModel);
    }

    @Override
    public void edit(SanPhamModel sanPham, int maSanPham, int HeSo) {
        iSanPhamDAO.edit(sanPham, maSanPham, HeSo);
    }
    
}
