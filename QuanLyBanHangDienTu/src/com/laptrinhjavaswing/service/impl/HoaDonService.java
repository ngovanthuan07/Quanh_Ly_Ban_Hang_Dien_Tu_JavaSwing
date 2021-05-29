/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.IHoaDonDAO;
import com.laptrinhjavaswing.dao.impl.HoaDonDAO;
import com.laptrinhjavaswing.model.HoaDonModel;
import com.laptrinhjavaswing.service.IHoaDonService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class HoaDonService implements IHoaDonService{

    private IHoaDonDAO iHoaDonDAO;
    public HoaDonService(){
        iHoaDonDAO = new HoaDonDAO();
    }
    
    @Override
    public List<HoaDonModel> findAll() {
        return iHoaDonDAO.findAll();
    }

    @Override
    public void editTongTien(int maHoaDon) {
        iHoaDonDAO.editTongTien(maHoaDon);
    }

    @Override
    public int save(HoaDonModel hoaDonModel) {
        return iHoaDonDAO.save(hoaDonModel);
    }

    @Override
    public void remove(int maHoaDon) {
        iHoaDonDAO.remove(maHoaDon);
    }

    @Override
    public void edit(HoaDonModel hoadon, int maHoaDon) {
        iHoaDonDAO.edit(hoadon, maHoaDon);
    }

    @Override
    public List<HoaDonModel> findAllByMaKhachHang(int MaHoaDon) {
        return iHoaDonDAO.findAllByMaKhachHang(MaHoaDon);
    }
    
}
