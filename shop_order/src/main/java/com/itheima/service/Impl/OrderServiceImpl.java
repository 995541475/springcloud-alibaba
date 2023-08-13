package com.itheima.service.Impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.itheima.Dao.OrderDao;
import com.itheima.domain.Order;
import com.itheima.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }
    @SentinelResource("message")
    public void message(){
        System.out.println("message");
    }
}
