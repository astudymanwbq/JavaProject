package com.wbq.config;

import com.github.pagehelper.PageHelper;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author wubiqin
 * @Date 2017-12-9 11:16
 * @Description
 */
@Configuration
//加载资源文件
@PropertySource({"classpath:jdbc.properties"})
//支持事务
@EnableTransactionManagement
@MapperScan(basePackages = {"com.wbq.dao"})
public class MybatisConfig {
    private final static Logger logger = LoggerFactory.getLogger(MybatisConfig.class);
    /**
     * 绑定资源属性
     * initialSize=0
     * maxActive=20
     * maxIdle=20
     * minIdle=1
     * maxWait=60000
     */
    @Value("${jdbc.driver}")
    private String driverClass;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${initialSize}")
    private int initialSize;
    @Value("${maxActive}")
    private int maxActive;
    @Value("${maxIdle}")
    private int maxIdle;
    @Value("${minIdle}")
    private int minIdle;
    @Value("${maxWait}")
    private Long maxWait;

    /**
     * 引入配置文件
     *
     * @return
     */
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        logger.info("propertySourcesPlaceholderConfigurer:引入配置文件");
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * dbcp数据源
     *
     * @return
     */
    @Bean(destroyMethod = "close")
    public BasicDataSource basicDataSource() {
        logger.info("dbcp数据源");
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driverClass);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        basicDataSource.setInitialSize(initialSize);
        basicDataSource.setMaxActive(maxActive);
        basicDataSource.setMaxIdle(maxIdle);
        basicDataSource.setMinIdle(minIdle);
        basicDataSource.setMaxWait(maxWait);
        return basicDataSource;
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(DataSource dataSource) {
        logger.info("platformTransactionManager");
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        logger.info("sqlSessionFactory");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver
                .getResources("classpath:mybatis/mapper/*.xml"));
        //配置pageHelper
        /* sqlSessionFactoryBean.setPlugins(new Interceptor[]{(Interceptor) pageHelper()});*/
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * mybatis 的分页插件
     * @return
     */
   /* @Bean
    public PageHelper pageHelper(){
        logger.info("pageHelper");
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();

        properties.setProperty("dialect","mysql");
        *//*将RowBounds第一个参数offset当成pageNum页码使用*//*
        properties.setProperty("offsetAsPageNum","true");
        *//*如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果*//*
        properties.setProperty("pageSizeZero","true");
        *//*使用rowcounts分页会进行count查询*//*
        properties.setProperty("rowBoundsWithCount","true");
        *//*启用合理化 如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页*//*
        properties.setProperty("reasonable","true");
        properties.setProperty("params","pageNum=start;pageSize=limit;");
        pageHelper.setProperties(properties);
        return pageHelper;
    }*/

}
