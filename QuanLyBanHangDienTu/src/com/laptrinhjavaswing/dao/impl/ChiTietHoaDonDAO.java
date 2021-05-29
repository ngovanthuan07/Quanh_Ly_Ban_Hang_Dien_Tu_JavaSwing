/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.IChiTietHoaDonDAO;
import com.laptrinhjavaswing.mapper.ChiTietHoaDonMapper;
import com.laptrinhjavaswing.model.ChiTietHoaDonModel;
import com.laptrinhjavaswing.model.HoaDonModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class ChiTietHoaDonDAO extends AbstractDAO<ChiTietHoaDonModel> implements IChiTietHoaDonDAO {

    @Override
    public List<ChiTietHoaDonModel> findByCodeMaHoaHon(int MaHoaDon) {
        String sql = "select * from ChiTietHoaDon as ct, SanPham sp\n"
                + "where ct.MaSanPham = sp.MaSanPham  and MaHoaDon = ?";
        List<ChiTietHoaDonModel> listChiTietHoaDonModels = query(sql, new ChiTietHoaDonMapper(), MaHoaDon);

        return listChiTietHoaDonModels.isEmpty() ? null : listChiTietHoaDonModels;
    }

    @Override
    public void edit(ChiTietHoaDonModel chiTietHoaDonModel, int MaCTHD) {
        String sql = "update ChiTietHoaDon set MaSanPham= ?,SoLuong= ?,TongTien= ? ,GhiChu= ? where MaCTHD= ?";
        update(sql,
                chiTietHoaDonModel.getMaSanPham(),
                chiTietHoaDonModel.getSoLuong(),
                chiTietHoaDonModel.getTongTien(),
                chiTietHoaDonModel.getGhiChu(),
                MaCTHD);
    }

    @Override
    public void remove(int maChiTietHoaDonModel) {
        String sql = "delete ChiTietHoaDon where MaCTHD= ?";
        update(sql, maChiTietHoaDonModel);
    }

    @Override
    public int save(ChiTietHoaDonModel chiTietHoaDonModel) {
        String sql = "insert into ChiTietHoaDon (MaHoaDon,MaSanPham,SoLuong,TongTien,GhiChu) values (?,?,?,?,?)";
        return (int) insert(sql, chiTietHoaDonModel.getMaHoaDon(),chiTietHoaDonModel.getMaSanPham(),chiTietHoaDonModel.getSoLuong(),chiTietHoaDonModel.getTongTien(),chiTietHoaDonModel.getGhiChu());
    }

}
