/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.ISanPhamDAO;
import com.laptrinhjavaswing.mapper.SanPhamMapper;
import com.laptrinhjavaswing.model.SanPhamModel;
import java.util.List;

/**
 *
 * @author ngova
 */
public class SanPhamDAO extends AbstractDAO<SanPhamModel> implements ISanPhamDAO {

    @Override
    public List<SanPhamModel> findAll() {
        String sql = " select * from SanPham as sp, LoaiSanPham as lsp, HangSanXuat as hsx\n"
                + " where sp.LoaiSanPham = lsp.MaLoaiSanPham and sp.HangSanXuat = hsx.MaHangSanXuat and sp.TrangThai = 1";

        return query(sql, new SanPhamMapper());
    }

    @Override
    public List<SanPhamModel> findByCodeLoaiSanPham(int MaLoaiSanPham) {
        String sql = "select * from SanPham as sp, LoaiSanPham as lsp, HangSanXuat as hsx\n"
                + "where sp.LoaiSanPham = lsp.MaLoaiSanPham and sp.HangSanXuat = hsx.MaHangSanXuat and lsp.MaLoaiSanPham = ?";
        List<SanPhamModel> listSanPham = query(sql, new SanPhamMapper(), MaLoaiSanPham);
        return listSanPham.isEmpty() ? null : listSanPham;
    }

    @Override
    public List<SanPhamModel> findByCodeMaSanPham(int maSanPham) {
        String sql = "select * from SanPham where SanPham.MaSanPham = ?";
        sql = " select * from SanPham as sp, LoaiSanPham as lsp, HangSanXuat as hsx\n"
                + " where sp.LoaiSanPham = lsp.MaLoaiSanPham and sp.HangSanXuat = hsx.MaHangSanXuat and sp.MaSanPham = ?";
        List<SanPhamModel> listSanPham = query(sql, new SanPhamMapper(), maSanPham);
        return listSanPham.isEmpty() ? null : listSanPham;
    }

    @Override
    public Object save(SanPhamModel hoaDonModel) {
        String sql = "insert into SanPham(TenSanPham,LoaiSanPham,HangSanXuat,GiaNhap,GiaBan,TonKho,TrangThai,Image,ChuThich)"
                + "values (?,?,?,?,?,?,?,?,?)";

        return insert(sql,
                hoaDonModel.getTenSanPham(),
                hoaDonModel.getMaLoaiSanPham(),
                hoaDonModel.getMaHangSanXuat(),
                hoaDonModel.getGiaNhap(),
                hoaDonModel.getGiaBan(),
                hoaDonModel.getTonKho(),
                1,
                hoaDonModel.getImage(),
                hoaDonModel.getChuThich()
        );
    }

    @Override
    public void edit(SanPhamModel sanPham, int maSanPham, int HeSo) {
        String sql = sql = "update SanPham set TenSanPham =?,LoaiSanPham = ?,HangSanXuat = ?,GiaNhap = ?,GiaBan = ?,TonKho = ?,TrangThai = ?,Image = ?,ChuThich = ? where MaSanPham = ?";;
        switch (HeSo) {
            case 1:
                update(sql,
                        sanPham.getTenSanPham(),
                        sanPham.getMaLoaiSanPham(),
                        sanPham.getMaHangSanXuat(),
                        sanPham.getGiaNhap(),
                        sanPham.getGiaBan(),
                        sanPham.getTonKho(),
                        1,
                        sanPham.getImage(),
                        sanPham.getChuThich(),
                        maSanPham
                );
                break;
            case 2:
                update(sql,
                        sanPham.getTenSanPham(),
                        sanPham.getMaLoaiSanPham(),
                        sanPham.getMaHangSanXuat(),
                        sanPham.getGiaNhap(),
                        sanPham.getGiaBan(),
                        sanPham.getTonKho(),
                        0,
                        sanPham.getImage(),
                        sanPham.getChuThich(),
                        maSanPham
                );
                break;
        }
    }

}
