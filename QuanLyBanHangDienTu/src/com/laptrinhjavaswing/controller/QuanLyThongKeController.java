/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laptrinhjavaswing.controller;

import com.laptrinhjavaswing.model.ThongKeModel;
import com.laptrinhjavaswing.service.impl.ThongKeService;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ngova
 */
public class QuanLyThongKeController {
    private ThongKeService thongKeService = null;
    
    public QuanLyThongKeController(){
        thongKeService = new ThongKeService();
    }
    public void setDateChart1(JPanel jpnItem){
        List<ThongKeModel> listThongKe = thongKeService.findAll();
        if(listThongKe != null){
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for(ThongKeModel item : listThongKe){
                dataset.addValue(item.getDoanhThu(), "Doanh Thu", item.getNam());
            }
            
            JFreeChart chart = ChartFactory.createBarChart("Thống Kê Doanh Thu Theo Năm", "Thời gian", "Tiền", dataset);
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(),300));
            
            jpnItem.removeAll();    
            jpnItem.setLayout(new CardLayout());
            jpnItem.add(chartPanel);
            jpnItem.validate();
            jpnItem.repaint();
        }
        
    }
}
