### 描述😃
火车售票系统管理端后端代码  

### 技术框架👨🏼‍💻
1️⃣springboot  
2️⃣jpa  
3️⃣mysql  
4️⃣springsecurity 
5️⃣maven 


### 点👇🏼
1️⃣全局统一返回  
```
{
    code:   状态码,0为成功
    msg:    错误消息
    data:   数据
}
```
2️⃣全局统一异常处理
通过拦截器对异常进行统一处理,来范围错误消息或者正确消息  
```
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
```

3️⃣使用方式
IDEA打开后右键`pom.xml`,然后选择maven中的`reimport`
