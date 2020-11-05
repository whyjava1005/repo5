package com.deyuan.service;

import com.deyuan.pojo.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAll(int page,int size) throws Exception;

    Orders findByID(String ordersId)throws Exception;
}
