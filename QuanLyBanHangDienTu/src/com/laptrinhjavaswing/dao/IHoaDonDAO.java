package com.laptrinhjavaswing.dao;

import com.laptrinhjavaswing.model.ChiTietHoaDonModel;
import com.laptrinhjavaswing.model.HoaDonModel;
import java.util.List;

public interface IHoaDonDAO {
     List<HoaDonModel> findAll();
     
     List<HoaDonModel> findAllByMaKhachHang(int maKhacHang);
     
     
     void editTongTien(int maHoaDon);
     
     int save (HoaDonModel hoaDonModel);
     
     void remove(int maHoaDon);
     
     void edit(HoaDonModel hoadon,int maHoaDon);
}
