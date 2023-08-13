package com.itheima.service.impl;

import com.itheima.Dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;


    @Override
    public Product findByPid(Integer pid) {
        return productDao.findById(pid).get();
    }
}
