/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service;

import com.laptrinhjavaswing.model.ChucVuModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface IChucVuService {

    List<ChucVuModel> findAll();

    String save(ChucVuModel chucVuModel);

    void edit(ChucVuModel chucVuModel, int maChucVu);
}
