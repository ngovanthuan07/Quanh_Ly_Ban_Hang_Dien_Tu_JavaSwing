/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao;

import com.laptrinhjavaswing.model.NhaPhanPhoiModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public interface INhaPhanPhoiDAO {
    List<NhaPhanPhoiModel> findAll();
}
