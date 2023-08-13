//package com.itheima.service.Impl;
//
//import com.alibaba.csp.sentinel.annotation.SentinelResource;
//import com.sun.deploy.security.BlockedException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
///**
// * @author hl
// * @version 1.0
// * @description
// * @date 2023/8/13 23:35
// */
//@Service
//@Slf4j
//public class OrderServiceImpl3 {
//    int i = 0;
//    @SentinelResource(value = "message" ,
//                    blockHandler = "blockHandler" ,//指定发生BlockException时进入的方法
//                    fallback = "fallback" //指定发生Throwable时进入的方法
//                    )
//    public String message(){
//        i++;
//        if (i % 3 == 0){
//            throw  new RuntimeException();
//        }
//        return "message";
//    }
//
//    public String blockHandler(BlockedException e){
//        log.error("{}",e);
//        return "接口被限流或者降级了...";
//    }
//
//    public String fallback(Throwable throwable){
//        log.error("{}",throwable);
//        return "接口发生异常了...";
//    }
//}
