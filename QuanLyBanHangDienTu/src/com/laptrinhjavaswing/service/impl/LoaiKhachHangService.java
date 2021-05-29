/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.ILoaiKhachHangDAO;
import com.laptrinhjavaswing.dao.impl.LoaiKhachHangDAO;
import com.laptrinhjavaswing.model.LoaiKhachHangModel;
import com.laptrinhjavaswing.service.ILoaiKhachHangService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class LoaiKhachHangService implements ILoaiKhachHangService{
    
    public ILoaiKhachHangDAO iLoaiKhachHangDAO;
    public LoaiKhachHangService(){
        iLoaiKhachHangDAO = new LoaiKhachHangDAO();
    }
    
    
    @Override
    public List<LoaiKhachHangModel> findAll() {
        return iLoaiKhachHangDAO.findAll();
    }
    
}
