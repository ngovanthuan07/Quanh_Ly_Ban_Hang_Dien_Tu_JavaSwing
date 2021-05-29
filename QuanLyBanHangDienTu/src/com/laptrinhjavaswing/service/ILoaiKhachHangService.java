/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.service;

import com.laptrinhjavaswing.model.LoaiKhachHangModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface ILoaiKhachHangService {
    List<LoaiKhachHangModel> findAll();
}
