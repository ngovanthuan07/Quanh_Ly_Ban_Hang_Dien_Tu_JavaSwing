/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.INhaPhanPhoiDAO;
import com.laptrinhjavaswing.dao.impl.NhaPhanPhoiDAO;
import com.laptrinhjavaswing.model.NhaPhanPhoiModel;
import com.laptrinhjavaswing.service.INhaPhanPhoiService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class NhaPhanPhoiService implements INhaPhanPhoiService{
    
    public INhaPhanPhoiDAO iNhaPhanPhoiDAO;
    
    public NhaPhanPhoiService(){
        iNhaPhanPhoiDAO = new NhaPhanPhoiDAO();
    }

    @Override
    public List<NhaPhanPhoiModel> findAll() {
        return iNhaPhanPhoiDAO.findAll();
    }
    
}
