/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.IThongKeDAO;
import com.laptrinhjavaswing.dao.impl.ThongKeDAO;
import com.laptrinhjavaswing.model.ThongKeModel;
import com.laptrinhjavaswing.service.IThongKeService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class ThongKeService implements IThongKeService{
    
    public IThongKeDAO iThongKeDAO;
    
    public ThongKeService(){
        iThongKeDAO = new ThongKeDAO();
    }
    
    
    @Override
    public List<ThongKeModel> findAll() {
        return iThongKeDAO.findAll();
    }

}
