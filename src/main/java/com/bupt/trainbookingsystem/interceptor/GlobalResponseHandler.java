package com.bupt.trainbookingsystem.bean;


import com.alibaba.fastjson.JSON;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "com.bupt.trainbookingsystem.controller")
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {
    //判断支持的类型
//    @Override
//    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
//        // 检查注解是否存在，存在则忽略拦截
//        if (methodParameter.getDeclaringClass().isAnnotationPresent(IgnorReponseAdvice.class)) {
//            return false;
//        }
//        if (methodParameter.getMethod().isAnnotationPresent(IgnorReponseAdvice.class)) {
//            return false;
//        }
//        return true;
//    }


    //判断支持的类型,true表示都要实行拦截
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 判断为null构建ResponseData对象进行返回
        if (o == null) {
            return ResponseData.success();
        }
        // 判断是ResponseData子类或其本身就返回Object o本身，因为有可能是接口返回时创建了ResponseData,这里避免再次封装
        if (o instanceof ResponseData) {
            return (ResponseData<Object>) o;
        }
        // String特殊处理，否则会抛异常
        if (o instanceof String) {
            return JSON.toJSON(ResponseData.success(o)).toString();
        }
        return ResponseData.success(o);
    }
}

