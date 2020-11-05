package com.deyuan.service.impl;

import com.deyuan.dao.IProductDao;
import com.deyuan.pojo.Product;
import com.deyuan.service.IProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao productDao;


    @Override
    public List<Product> findAll(int page,int size) {
        //        配置分页，从第几页开始，显示几条数据
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }
}
