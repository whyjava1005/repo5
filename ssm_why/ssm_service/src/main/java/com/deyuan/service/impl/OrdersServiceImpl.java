package com.deyuan.service.impl;

import com.deyuan.dao.OrdersDao;
import com.deyuan.pojo.Orders;
import com.deyuan.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {
//        配置分页，从第几页开始，显示几条数据
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findByID(String orderId)throws Exception {
        return ordersDao.findByOrdersId(orderId);
    }
}
