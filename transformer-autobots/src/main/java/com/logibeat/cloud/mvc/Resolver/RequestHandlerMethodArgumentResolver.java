package com.logibeat.cloud.mvc.Resolver;

import com.logibeat.cloud.mvc.annotation.RequestModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
@Slf4j
public class RequestHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver, ApplicationListener {
    //表单数据解析,springMVC中已经实现
    private ServletModelAttributeMethodProcessor servletModelAttributeMethodProcessor;

    //json数据解析,springMVC中已经实现
    private RequestResponseBodyMethodProcessor requestResponseBodyMethodProcessor;


    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(RequestModel.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();
        String contentType = request.getContentType();

        if (contentType.startsWith("application/x-www-form-urlencoded")) {
            log.info("this contentType is application/x-www-form-urlencoded");
            return servletModelAttributeMethodProcessor.resolveArgument(methodParameter, modelAndViewContainer,
                    nativeWebRequest, webDataBinderFactory);
        }

        if (contentType.startsWith("application/json")) {
            log.info("this contentType is application/json");
            return requestResponseBodyMethodProcessor.resolveArgument(methodParameter, modelAndViewContainer,
                    nativeWebRequest, webDataBinderFactory);
        }

        //其它的默认表单提交
        return servletModelAttributeMethodProcessor.resolveArgument(methodParameter, modelAndViewContainer,
                nativeWebRequest, webDataBinderFactory);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //响应applicationReadyEvent事件,表明tomcat(jetty)容器已将上下文填充完毕,从而从容器中获取json和表单数据解析器
        if (applicationEvent instanceof ApplicationReadyEvent) {
            RequestMappingHandlerAdapter requestMappingHandlerAdapter =
                    (RequestMappingHandlerAdapter) ((ApplicationReadyEvent) applicationEvent).getApplicationContext().getBean("requestMappingHandlerAdapter");

            if (Objects.isNull(requestMappingHandlerAdapter)) {
                throw new RuntimeException("自定义参数解析加载失败");
            }

            for (HandlerMethodArgumentResolver methodArgumentResolver :
                    requestMappingHandlerAdapter.getArgumentResolvers()) {
                //获取json形式解析器
                if(methodArgumentResolver instanceof RequestResponseBodyMethodProcessor){
                    this.requestResponseBodyMethodProcessor = (RequestResponseBodyMethodProcessor) methodArgumentResolver;
                    continue;
                }
                //获取表单开式解析器
                if(methodArgumentResolver instanceof ServletModelAttributeMethodProcessor){
                    this.servletModelAttributeMethodProcessor = (ServletModelAttributeMethodProcessor) methodArgumentResolver;
                }
            }
        }
    }

}
