/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao;

import com.laptrinhjavaswing.service.impl.NhanVienModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface INhanVienDAO {
    List<NhanVienModel> findAll();
    List<NhanVienModel> findByCodeMaChucVu(int MaChucVu);
    Object save(NhanVienModel nhanVienModel);
    void edit(NhanVienModel nhanVienModel,int maNhanVien);
    void remove(NhanVienModel nhanVienModel,int maNhanVien);
}
