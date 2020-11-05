package com.deyuan.service;

import com.deyuan.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IProductService {
    List<Product> findAll(int page,int size);

    void save(Product product);
}
