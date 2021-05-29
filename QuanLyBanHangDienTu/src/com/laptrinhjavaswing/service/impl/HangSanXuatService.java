/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.IHangSanXuatDAO;
import com.laptrinhjavaswing.dao.impl.HangSanXuatDAO;
import com.laptrinhjavaswing.model.HangSanXuatModel;
import com.laptrinhjavaswing.service.IHangSanXuatService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class HangSanXuatService implements IHangSanXuatService{

    public IHangSanXuatDAO iHangSanXuatDAO;

    public HangSanXuatService() {
        iHangSanXuatDAO= new HangSanXuatDAO();
    }
    
    
    
    @Override
    public List<HangSanXuatModel> findAll() {
       return iHangSanXuatDAO.findAll();
    }
    
}
