package com.laptrinhjavaswing.service.impl;

import com.laptrinhjavaswing.dao.IUserDAO;
import com.laptrinhjavaswing.dao.impl.UserDAO;
import com.laptrinhjavaswing.model.UserModel;
import com.laptrinhjavaswing.service.IUserService;
import java.util.List;


public class UserService implements IUserService{

    private IUserDAO iUserDAO;

    public UserService() {
        iUserDAO = new UserDAO();
    }
    
    @Override
    public boolean findDangNhap(String tenDangNhap, String passWord) {
        return iUserDAO.findDangNhap(tenDangNhap, passWord);
    }

    @Override
    public List<UserModel> findAll() {
        return iUserDAO.fillAll();
    }

    @Override
    public Object save(UserModel user) {
        return iUserDAO.save(user);
    }

    @Override
    public void edit(UserModel user, int id, int heso) {
        iUserDAO.edit(user, id, heso);
    }
}
