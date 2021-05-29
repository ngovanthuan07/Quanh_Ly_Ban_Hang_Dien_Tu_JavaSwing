package com.laptrinhjavaswing.service;

import com.laptrinhjavaswing.model.UserModel;
import java.util.List;

public interface IUserService {

    List<UserModel> findAll();

    boolean findDangNhap(String tenDangNhap, String passWord);

    Object save(UserModel user);

    void edit(UserModel user, int id, int heso);
}
