package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.IHoaDonDAO;
import com.laptrinhjavaswing.mapper.HoaDonMapper;
import com.laptrinhjavaswing.mapper.HoaDonMapper_Count;
import com.laptrinhjavaswing.model.ChiTietHoaDonModel;
import com.laptrinhjavaswing.model.HoaDonModel;
import java.util.List;

public class HoaDonDAO extends AbstractDAO<HoaDonModel> implements IHoaDonDAO{

    @Override
    public List<HoaDonModel> findAll() {
        String sql = ""
                + "SELECT MaHoaDon,kh.TenKhachHang,nv.TenNhanVien,NgayLapHoaDon,TongTien,hd.GhiChu \n" +
                   "FROM HoaDon AS hd, KhachHang AS kh, NhanVien AS nv\n" +
                   "WHERE hd.MaKhachHang = kh.MaKhachHang AND hd.MaNhanVien = nv.MaNhanVien";
        return query(sql, new HoaDonMapper());
    }

    @Override
    public void editTongTien(int maHoaDon) {
        String sql = "update HoaDon\n" +
                    "set TongTien = (select sum(ct.TongTien) FROM ChiTietHoaDon as ct where ct.MaHoaDon = hd.MaHoaDon)\n" +
                    "from  HoaDon as hd\n" +
                    "where hd.MaHoaDon = ?";
        update(sql, maHoaDon);
    }

    @Override
    public int save(HoaDonModel hoaDonModel) {
        String sql = "insert into HoaDon(MaKhachHang,MaNhanVien,TongTien,GhiChu) values(?,?,?,?)";
        
        return (int) insert(sql, hoaDonModel.getMaKhachHang(),hoaDonModel.getMaNhanVien(),hoaDonModel.getTongTien(),hoaDonModel.getGhiChu());
    }

    @Override
    public void remove(int maHoaDon) {
        String sql = "delete HoaDon where MaHoaDon= ?";

        update(sql, maHoaDon);
    }

    @Override
    public void edit(HoaDonModel hoadon, int maHoaDon) {
        String sql = "update HoaDon set MaNhanVien = ? , MaKhachHang = ?, NgayLapHoaDon = ?, GhiChu = ? where MaHoaDon = ? ";
        update(sql, hoadon.getMaNhanVien(),hoadon.getMaKhachHang(),hoadon.getNgayLapHoaDon(),hoadon.getGhiChu(),maHoaDon);
    }

    @Override
    public List<HoaDonModel> findAllByMaKhachHang(int maKhacHang) {
        String sql = "select count(MaHoaDon) as SoPhieuMua from KhachHang,HoaDon where KhachHang.MaKhachHang=HoaDon.MaKhachHang and  KhachHang.MaKhachHang=?"; 
        
        return query(sql, new HoaDonMapper_Count(),maKhacHang);
    }

}
