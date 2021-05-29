package com.laptrinhjavaswing.dao.impl;

import com.laptrinhjavaswing.dao.IUserDAO;
import com.laptrinhjavaswing.mapper.UserMapper;
import com.laptrinhjavaswing.model.UserModel;
import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

    @Override
    public boolean findDangNhap(String tenDangNhap, String passWord) {
        String sql = "SELECT TenDangNhap,Password FROM Users where TenDangNhap = ? and Password = ?";
        List<UserModel> user = query(sql, new UserMapper(), tenDangNhap, passWord);
        return user.isEmpty() ? false : true;
    }

    @Override
    public List<UserModel> fillAll() {
        String sql = "select * from Users as users, NhanVien as nv, Quyen as q\n"
                + "where users.MaNhanVien = nv.MaNhanVien and users.Quyen = q.MaQuyen";
        return query(sql, new UserMapper());
    }

    @Override
    public Object save(UserModel user) {
        String sql = "insert into Users(MaNhanVien,TenDangNhap,Password,Quyen,ChuThich)\n"
                + "values (?,?,?,?,?)";
        return insert(sql, user.getMaNhanVien(),user.getTenDangNhap(),user.getPassword(),user.getMaQuyen(),user.getChuThich());
    }

    @Override
    public void edit(UserModel user, int id, int heso) {
        String sql = null;
        switch(heso){
            case 1:
                sql = "update Users set MaNhanVien =? ,TenDangNhap = ?,Password = ?,Quyen = ?,ChuThich = ? where ID = ?";
                update(sql, user.getMaNhanVien(),user.getTenDangNhap(),user.getPassword(),user.getMaQuyen(),user.getChuThich(),id);
                break;
            case 2:
                sql = "delete Users where ID = ?";
                update(sql, id);
                break;
        }
    }
}
