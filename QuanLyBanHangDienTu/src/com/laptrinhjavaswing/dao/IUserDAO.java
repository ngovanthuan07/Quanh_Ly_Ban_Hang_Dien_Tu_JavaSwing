package com.laptrinhjavaswing.dao;

import com.laptrinhjavaswing.model.UserModel;
import java.util.List;


public interface IUserDAO {
    List<UserModel> fillAll();
    
    boolean findDangNhap(String tenDangNhap, String passWord);
    
    Object save(UserModel user);
    
    void edit(UserModel user, int id,int heso);
}
