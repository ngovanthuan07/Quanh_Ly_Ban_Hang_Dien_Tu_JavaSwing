/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service;

import com.laptrinhjavaswing.model.HoaDonModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface IHoaDonService {

    List<HoaDonModel> findAll();

    void editTongTien(int maHoaDon);

    int save(HoaDonModel hoaDonModel);

    void remove(int maHoaDon);

    void edit(HoaDonModel hoadon, int maHoaDon);
    
    List<HoaDonModel> findAllByMaKhachHang(int MaHoaDon);
}
