/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao;

import com.laptrinhjavaswing.service.impl.LoaiSanPhamModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface ILoaiSanPhamDAO {
    List<LoaiSanPhamModel> findAll();
    
    Object save(LoaiSanPhamModel lsp);
    void edit(LoaiSanPhamModel lsp, int maLoaiSanPham);

}
