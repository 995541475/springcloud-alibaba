package com.itheima.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ExceptionHandlerPage implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        response.setContentType("application/json;charset=utf-8");
        ///BlockException 异常接口,包含Sentinel的五个异常
            //AuthorityException 授权异常
            //DegradeException 降级异常
            //FlowException 限流异常
            //ParamFlowException 参数限流异常
            //SystemBlockException 系统负载异常
        ResponseData data = null;
        if(e instanceof FlowException){
            data = new ResponseData(-1, "接口被限流了...");
        }else if(e instanceof DegradeException){
            data = new ResponseData(-2, "接口被降级了...");
        }
        response.getWriter().write(JSON.toJSONString(data));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class ResponseData{
        private int code;
        private String message;
    }

}
