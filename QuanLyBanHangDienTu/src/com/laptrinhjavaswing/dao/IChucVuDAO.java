/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao;

import com.laptrinhjavaswing.model.ChucVuModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface IChucVuDAO {

    List<ChucVuModel> findAll();

    String save(ChucVuModel chucVuModel);

    void edit(ChucVuModel chucVuModel, int maChucVu);
}
