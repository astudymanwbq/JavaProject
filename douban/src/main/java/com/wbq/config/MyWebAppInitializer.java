package com.wbq.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 配置DispatchServlet
 */
@Configuration
public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final static Logger logger= LoggerFactory.getLogger(MyWebAppInitializer.class);
    @Override
    protected Class<?>[] getRootConfigClasses() {
        logger.info("root配置初始化");
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        logger.info("web配置初始化");
        return new Class[]{WebConfig.class};//指定配置类
    }

    @Override
    protected String[] getServletMappings() {
        logger.info("映射路径初始化");
        return new String[]{"/"};//将dispatchServlet映射到"/"
    }
}
