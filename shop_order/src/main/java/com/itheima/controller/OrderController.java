package com.itheima.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSON;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.OrderService;
import com.itheima.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid){
        log.info("接收到{}号商品的下单请求,接下来调用商品微服务查询此商品信息", pid);
        //通过fegin调用商品微服务
        Product product = productService.findByPid(pid);
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
        //模拟微服务调用需要2S时间
        try {
            Thread.sleep(2001);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //下单(创建订单)
        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(product.getPid());
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);
        //为了不产生太多垃圾数据,暂时不做订单保存
        //orderService.save(order);
        log.info("创建订单成功,订单信息为{}", JSON.toJSONString(order));
        return order;
    }

    @RequestMapping("/order/message")
    public String message() {
        return "高并发下的问题测试";
    }

    @RequestMapping("/order/message1")
    public String message1() {
        orderService.message();
        return "message1";
    }
    int i = 0;
    @RequestMapping("/order/message2")
    public String message2() {
        i++;
        //异常比例为0.333
        if (i % 3 == 0){
            throw new RuntimeException();
        }
        //orderService.message();
        return "message2";
    }
    @RequestMapping("/order/message3")
    @SentinelResource("message3")//注意这里必须使用这个注解标识,热点规则不生效
    public String message3(String name, Integer age) {
        return name + age;
    }
}
