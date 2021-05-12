package com.logibeat.cloud;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.logibeat.cloud.mvc.Resolver.RequestHandlerMethodArgumentResolver;
//import com.logibeat.cloud.mvc.Resolver.ServiceHandlerExceptionResolver;
import com.logibeat.cloud.mvc.Resolver.RequestHandlerMethodArgumentResolver;
import com.logibeat.cloud.mvc.interceptor.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.HibernateValidator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.OrderComparator;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Configuration
@Slf4j
@ComponentScan(basePackages = { "com.logibeat.cloud" })
@MapperScan(basePackages = {"com.logibeat.cloud.persistent.dao"})
public class AutobotsConfig extends WebMvcConfigurationSupport {
    @Autowired
    private RequestInterceptor requestInterceptor;

    @Autowired
    private RequestHandlerMethodArgumentResolver requestHandlerMethodArgumentResolve;

    @Bean
    public Validator validator(){
        ValidatorFactory validatorFactory = Validation.byProvider( HibernateValidator.class )
                .configure()
                .addProperty( "hibernate.validator.fail_fast", "true" )
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        return validator;
    }


    /**
     * 初始化线程池
     *
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10);
        taskExecutor.setMaxPoolSize(100);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers){
        argumentResolvers.add(requestHandlerMethodArgumentResolve);
        OrderComparator.sort(argumentResolvers);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SimpleDateFormat simpleFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(simpleFormat);

        MappingJackson2HttpMessageConverter jscksonConverter = new MappingJackson2HttpMessageConverter();
        jscksonConverter.setObjectMapper(objectMapper);

        converters.add(jscksonConverter);
    }

    /**
     * 解决后缀加htm报错问题
     * @param configurer
     */
    @Override
    protected void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    protected void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);
    }
}
