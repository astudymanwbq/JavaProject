package com.wbq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @Author 吴璧钦
 * @Date 2017-12-6 16:42
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = "com.wbq.web")
public class WebConfig extends WebMvcConfigurerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Bean
    public ViewResolver viewResolver() {
        logger.info("jsp视图解析器");
        //jsp视图解析器
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        //设置视图解析器的优先级
        internalResourceViewResolver.setOrder(1);
        //可以在JSP页面中通过${}访问beans
        internalResourceViewResolver.setExposeContextBeansAsAttributes(true);
        return internalResourceViewResolver;
    }

    /**
     * 文件上传
     *
     * @return
     */
    @Bean(name = "multipartResolver")
    protected CommonsMultipartResolver multipartResolver() {
        logger.info("文件上传");
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(2097152);//上传文件的总大小不能超过2m
        commonsMultipartResolver.setMaxInMemorySize(0);//设置在文件上传时允许写到内存中的最大值，以字节为单位计算，默认是10240 上传文件大小若小于此参数，则不会生成临时文件
        commonsMultipartResolver.setDefaultEncoding("utf-8");
        return commonsMultipartResolver;
    }

    /**
     * 配置静态资源的处理
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        logger.info("配置静态资源的处理");
        configurer.enable();
    }

    /* *//**
     * addResourceHandler对外曝露的访问路劲
     * addResourceLocations 文件放置的路径
     * @param registry
     *//*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("").addResourceLocations("classpath:");
    }*/
    /*public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
        myServlet.addMapping("/custom/**");
    }*/
    //配置拦截器的bean
   /* @Bean
    public InterceptorDemo interceptorDemo(){
        return new InterceptorDemo();
    }
    //注册拦截器
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(interceptorDemo);
    }*/

    /**
     *
     * @param registry
     *//*
    @Override
   public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("").setViewName("");
   }*/

    /**
     * 不忽略.后面的参数
     * @param configurer
     */
    /*@Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        configurer.setUseSuffixPatternMatch(false);
    }*//*
    /**
    开启异步方法支持
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){

    }*/
}
