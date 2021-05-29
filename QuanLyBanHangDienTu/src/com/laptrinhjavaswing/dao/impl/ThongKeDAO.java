/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.IThongKeDAO;
import com.laptrinhjavaswing.mapper.ThongKeMapper;
import com.laptrinhjavaswing.model.ThongKeModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class ThongKeDAO extends AbstractDAO<ThongKeModel> implements IThongKeDAO {

    @Override
    public List<ThongKeModel> findAll() {
        String sql = "select year(NgayLapHoaDon) as Nam, sum(TongTien) as DoanhThu from HoaDon group by year(NgayLapHoaDon)";
        return query(sql, new ThongKeMapper());
    }

}
