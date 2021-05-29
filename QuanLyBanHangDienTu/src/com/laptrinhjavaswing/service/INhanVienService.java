/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service;

import com.laptrinhjavaswing.service.impl.NhanVienModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface INhanVienService {

    List<NhanVienModel> findAll();

    Object save(NhanVienModel nhanVienModel);
    
    List<NhanVienModel> findByCodeMaChucVu(int MaChucVu);

    void edit(NhanVienModel nhanVienModel, int maNhanVien);

    void remove(NhanVienModel nhanVienModel, int maNhanVien);
}
