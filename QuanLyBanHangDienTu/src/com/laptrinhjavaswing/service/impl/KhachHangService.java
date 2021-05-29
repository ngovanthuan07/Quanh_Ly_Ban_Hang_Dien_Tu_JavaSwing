/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.IKhachHangDAO;
import com.laptrinhjavaswing.dao.impl.KhachHangDAO;
import com.laptrinhjavaswing.model.KhachHangModel;
import com.laptrinhjavaswing.service.IKhachHangService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class KhachHangService implements IKhachHangService{

    public IKhachHangDAO iKhachHangDAO;
    
    public KhachHangService(){
        iKhachHangDAO = new KhachHangDAO();
    }
    
    @Override
    public List<KhachHangModel> findAll() {
        return iKhachHangDAO.findAll();
    }

    @Override
    public Object save(KhachHangModel khachHangModel) {
        return iKhachHangDAO.save(khachHangModel);
    }

    @Override
    public void edit(KhachHangModel khachHangModel, int maKhachHang, int heSo) {
        iKhachHangDAO.edit(khachHangModel, maKhachHang, heSo);
    }
    
}
