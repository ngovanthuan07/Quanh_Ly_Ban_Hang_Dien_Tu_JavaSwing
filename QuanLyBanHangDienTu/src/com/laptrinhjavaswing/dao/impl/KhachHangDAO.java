package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.IKhachHangDAO;
import com.laptrinhjavaswing.mapper.KhachHangMapper;
import com.laptrinhjavaswing.model.KhachHangModel;
import java.util.List;

public class KhachHangDAO extends AbstractDAO<KhachHangModel> implements IKhachHangDAO {

    @Override
    public List<KhachHangModel> findAll() {
        String sql = "SELECT * \n"
                + "FROM KhachHang AS KH, LoaiKhachHang AS LKH\n"
                + "WHERE KH.LoaiKhachHang = LKH.MaLoaiKhachHang";
        return query(sql, new KhachHangMapper());
    }

    @Override
    public Object save(KhachHangModel khachHangModel) {
        String sql = "insert into KhachHang(TenKhachHang,Ngaysinh,GioiTinh,DiaChi,SDT,LoaiKhachHang,GhiChu)\n"
                + "	values (?,?,?,?,?,?,?)";

        return insert(sql,
                khachHangModel.getTenKhachHang(),
                khachHangModel.getNgaySinh(),
                khachHangModel.getGioiTinh(),
                khachHangModel.getDiaChi(),
                khachHangModel.getSdt(),
                khachHangModel.getMaLoaiKhachHang(),
                khachHangModel.getGhiChu()
        );
    }

    @Override
    public void edit(KhachHangModel khachHangModel, int maKhachHang, int heSo) {
        String sql = null;
        switch (heSo) {
            case 1:
                sql = "update KhachHang set TenKhachHang = ? ,Ngaysinh = ?,GioiTinh = ?,DiaChi = ?,SDT = ?,LoaiKhachHang = ?,GhiChu = ?\n"
                        + "where MaKhachHang = ?";
                update(sql,
                        khachHangModel.getTenKhachHang(),
                        khachHangModel.getNgaySinh(),
                        khachHangModel.getGioiTinh(),
                        khachHangModel.getDiaChi(),
                        khachHangModel.getSdt(),
                        khachHangModel.getMaLoaiKhachHang(),
                        khachHangModel.getGhiChu(),
                        maKhachHang
                );
                break;
            case 2:
                sql = "delete KhachHang where MaKhachHang = ?";
                update(sql, maKhachHang);
                break;

        }
    }

}
