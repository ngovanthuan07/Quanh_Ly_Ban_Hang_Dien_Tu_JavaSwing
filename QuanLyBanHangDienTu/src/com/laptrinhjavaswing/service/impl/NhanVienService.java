/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.INhanVienDAO;
import com.laptrinhjavaswing.dao.impl.NhanVienDAO;
import com.laptrinhjavaswing.service.INhanVienService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class NhanVienService implements INhanVienService{

    private INhanVienDAO inhanVienDAO;
    
    public NhanVienService(){
        inhanVienDAO = new NhanVienDAO();
    }
    
    @Override
    public List<NhanVienModel> findAll() {
        return inhanVienDAO.findAll();
    }

    @Override
    public Object save(NhanVienModel nhanVienModel) {
        return inhanVienDAO.save(nhanVienModel);
    }

    @Override
    public void edit(NhanVienModel nhanVienModel, int maNhanVien) {
        inhanVienDAO.edit(nhanVienModel, maNhanVien);
    }

    @Override
    public void remove(NhanVienModel nhanVienModel, int maNhanVien) {
        inhanVienDAO.remove(nhanVienModel, maNhanVien);
    }

    @Override
    public List<NhanVienModel> findByCodeMaChucVu(int MaChucVu) {
        return inhanVienDAO.findByCodeMaChucVu(MaChucVu);
    }
    
}
