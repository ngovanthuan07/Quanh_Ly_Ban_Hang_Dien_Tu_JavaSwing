/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao;

import com.laptrinhjavaswing.model.SanPhamModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface ISanPhamDAO {
    List<SanPhamModel> findAll();
    List<SanPhamModel> findByCodeLoaiSanPham(int MaLoaiSanPham);
    List<SanPhamModel> findByCodeMaSanPham(int maSanPham);
    
    Object save (SanPhamModel hoaDonModel);
     
     
    void edit(SanPhamModel sanPham,int maSanPham, int HeSo);
}
