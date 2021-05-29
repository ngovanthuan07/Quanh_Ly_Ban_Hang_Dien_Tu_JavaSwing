/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.INhanVienDAO;
import com.laptrinhjavaswing.mapper.NhanVienMapper;
import com.laptrinhjavaswing.service.impl.NhanVienModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class NhanVienDAO extends AbstractDAO<NhanVienModel> implements INhanVienDAO {

    @Override
    public List<NhanVienModel> findAll() {
        String sql = "select * from NhanVien,ChucVu "
                + "where NhanVien.ChucVu=ChucVu.MaChucVu";
        return query(sql, new NhanVienMapper());
    }

    @Override
    public Object save(NhanVienModel nhanVienModel) {
        String sql = "insert into NhanVien(TenNhanVien,NgaySinh,GioiTinh,NgayVaoLam,ChucVu,DiaChi,SoDT,GhiChu)	\n"
                + "	values(?,?,?,?,?,?,?,?)";

        Object addNV = (String) insert(sql,
                nhanVienModel.getTenNhanVien(),
                nhanVienModel.getNgaySinh(),
                nhanVienModel.getGioiTinh(),
                nhanVienModel.getNgayVaoLam(),
                nhanVienModel.getMaChucVu(),
                nhanVienModel.getDiaChi(),
                nhanVienModel.getSoDT(),
                nhanVienModel.getGhiChu()
        );
        return addNV;
    }

    @Override
    public void edit(NhanVienModel nhanVienModel, int maNhanVien) {
        String sql = "update NhanVien set TenNhanVien = ?, NgaySinh = ?, GioiTinh = ?,NgayVaoLam = ?,ChucVu = ?,DiaChi = ?,SoDT = ?,GhiChu = ? where MaNhanVien = ?";

        update(sql,
                nhanVienModel.getTenNhanVien(),
                nhanVienModel.getNgaySinh(),
                nhanVienModel.getGioiTinh(),
                nhanVienModel.getNgayVaoLam(),
                nhanVienModel.getMaChucVu(),
                nhanVienModel.getDiaChi(),
                nhanVienModel.getSoDT(),
                nhanVienModel.getGhiChu(),
                maNhanVien
        );
    }

    @Override
    public void remove(NhanVienModel nhanVienModel, int maNhanVien) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NhanVienModel> findByCodeMaChucVu(int MaChucVu) {
        String sql = "select * from NhanVien,ChucVu "
                + "where NhanVien.ChucVu=ChucVu.MaChucVu and NhanVien.ChucVu = ?";
        return query(sql, new NhanVienMapper(), MaChucVu);
    }

}
