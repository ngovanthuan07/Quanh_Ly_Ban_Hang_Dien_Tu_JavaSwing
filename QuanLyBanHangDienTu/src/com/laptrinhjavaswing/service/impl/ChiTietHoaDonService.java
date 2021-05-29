/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.IChiTietHoaDonDAO;
import com.laptrinhjavaswing.dao.impl.ChiTietHoaDonDAO;
import com.laptrinhjavaswing.model.ChiTietHoaDonModel;
import com.laptrinhjavaswing.service.IChiTietHoaDonService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class ChiTietHoaDonService implements IChiTietHoaDonService{

    public IChiTietHoaDonDAO iChiTietHoaDonDAO;
    
    public ChiTietHoaDonService (){
        iChiTietHoaDonDAO = new ChiTietHoaDonDAO();
    }
    
    
    @Override
    public List<ChiTietHoaDonModel> findAllByCodeMaHoaDon(int MaHoaDon) {
       return iChiTietHoaDonDAO.findByCodeMaHoaHon(MaHoaDon);
    }

    @Override
    public void edit(ChiTietHoaDonModel chiTietHoaDonModel, int MaCTHD) {
        iChiTietHoaDonDAO.edit(chiTietHoaDonModel, MaCTHD);
    }

    @Override
    public void remove(int maCTHD) {
        iChiTietHoaDonDAO.remove(maCTHD);
    }

    @Override
    public int save(ChiTietHoaDonModel chiTietHoaDonModel) {
        return iChiTietHoaDonDAO.save(chiTietHoaDonModel);
    }
    
}
