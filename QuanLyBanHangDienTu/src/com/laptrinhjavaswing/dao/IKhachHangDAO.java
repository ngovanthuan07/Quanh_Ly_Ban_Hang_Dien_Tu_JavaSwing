/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao;

import com.laptrinhjavaswing.model.KhachHangModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface IKhachHangDAO {
    List<KhachHangModel> findAll();
    
    Object save(KhachHangModel khachHangModel);
    
    void edit(KhachHangModel khachHangModel, int maKhachHang, int heSo);
}
