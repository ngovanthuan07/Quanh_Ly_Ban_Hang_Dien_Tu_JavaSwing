/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.views;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ngova
 */
public class HinhAnh {
    List<String> hinhAnh;
    
    public HinhAnh(){
        hinhAnh = new ArrayList<>();
        hinhAnh.add("applewatch.jpg");
        hinhAnh.add("dienthoaisamsung.jpg");
        hinhAnh.add("htc.jpg");
        hinhAnh.add("ipod.png");
        hinhAnh.add("iphone.jpg");
        
    }

    public List<String> getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(List<String> hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
    
}
