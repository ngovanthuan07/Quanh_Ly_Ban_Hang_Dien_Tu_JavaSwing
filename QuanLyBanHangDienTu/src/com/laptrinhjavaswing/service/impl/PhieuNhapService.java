/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.IPhieuNhapDAO;
import com.laptrinhjavaswing.dao.impl.PhieuNhapDAO;
import com.laptrinhjavaswing.model.PhieuNhapModel;
import com.laptrinhjavaswing.service.IPhieuNhapService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class PhieuNhapService implements IPhieuNhapService{
    
    public IPhieuNhapDAO iPhieuNhapDAO;
    
    public PhieuNhapService(){
        iPhieuNhapDAO = new PhieuNhapDAO();
    }
    
    @Override
    public List<PhieuNhapModel> findAll() {
        return iPhieuNhapDAO.findAll();
    }
    
}
