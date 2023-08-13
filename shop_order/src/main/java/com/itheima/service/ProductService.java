package com.itheima.service;

import com.itheima.domain.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-product")//声明调用的提供者的name
public interface ProductService {
    //指定调用提供者的哪个方法
    @GetMapping("/product/{pid}")
    Product findByPid(@PathVariable("pid") Integer pid);

}
