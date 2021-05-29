/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao;

import com.laptrinhjavaswing.model.ChiTietHoaDonModel;
import com.laptrinhjavaswing.model.HoaDonModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface IChiTietHoaDonDAO {
    List<ChiTietHoaDonModel> findByCodeMaHoaHon(int MaHoaDon);
    
    void edit(ChiTietHoaDonModel chiTietHoaDonModel, int MaCTHD);
    
    void remove(int maCTHD);
    
    int save(ChiTietHoaDonModel chiTietHoaDonModel);
}
