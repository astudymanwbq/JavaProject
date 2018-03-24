package com.wbq.config;

import net.sf.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author 吴璧钦
 * @Date 2017-12-9 13:59
 * @Description
 */
@Configuration
//启用缓存注解
@EnableCaching
public class CacheConfig {
    private final static Logger logger = LoggerFactory.getLogger(CacheConfig.class);

    /**
     * ehCacheManagerFactoryBean
     *
     * @return
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        logger.info("ehCacheManagerFactoryBean");
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("ehcache/ehcache.xml"));
        return bean;
    }

    /**
     * EhCacheCacheManager
     *
     * @return
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(CacheManager cacheManager) {
        logger.info("ehCacheCacheManager");
        return new EhCacheCacheManager(cacheManager);
    }

}
