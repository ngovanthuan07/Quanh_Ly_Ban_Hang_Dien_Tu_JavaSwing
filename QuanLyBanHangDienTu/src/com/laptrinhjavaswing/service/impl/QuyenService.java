/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.IQuyenDAO;
import com.laptrinhjavaswing.dao.impl.QuyenDAO;
import com.laptrinhjavaswing.model.QuyenModel;
import com.laptrinhjavaswing.service.IQuyenService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class QuyenService implements IQuyenService{
    
    public IQuyenDAO iQuyenDAO;
    public QuyenService(){
        iQuyenDAO = new QuyenDAO();
    }
           

    @Override
    public List<QuyenModel> findAll() {
        return iQuyenDAO.findAll();
    }
    
}
