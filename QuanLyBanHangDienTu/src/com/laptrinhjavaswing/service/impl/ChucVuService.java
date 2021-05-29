/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.IChucVuDAO;
import com.laptrinhjavaswing.dao.impl.ChucVuDAO;
import com.laptrinhjavaswing.model.ChucVuModel;
import com.laptrinhjavaswing.service.IChucVuService;
import java.util.List;

/**
 *
 * @author ngova
 */
public class ChucVuService implements IChucVuService{
    private IChucVuDAO iChucVuDAO;
    
    public ChucVuService(){
        iChucVuDAO = new ChucVuDAO();
    }
    
    
    @Override
    public List<ChucVuModel> findAll() {
        return iChucVuDAO.findAll();
    }

    @Override
    public String save(ChucVuModel chucVuModel) {
        return iChucVuDAO.save(chucVuModel);
    }

    @Override
    public void edit(ChucVuModel chucVuModel, int maChucVu) {
        iChucVuDAO.edit(chucVuModel, maChucVu);
    }
    
}
