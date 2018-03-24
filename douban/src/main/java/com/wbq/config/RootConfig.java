package com.wbq.config;

import com.wbq.job.SpiderJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Author 吴璧钦
 * @Date 2017-12-6 16:51
 * 配置类，用于管理ContextLoadListener创建的上下文的bean
 */
@Configuration
@ComponentScan(basePackages = {"com.wbq"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
public class RootConfig {
    private final static Logger logger= LoggerFactory.getLogger(RootConfig.class);
    @Bean
    /**
     * 使用BeanNameAutoProxyCreator来创建代理
     */
    public BeanNameAutoProxyCreator proxyCreator() {
        logger.info("BeanNameAutoProxyCreator来创建代理");
        BeanNameAutoProxyCreator proxyCreator = new BeanNameAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        //设置要创建代理的那些Bean的名字
        proxyCreator.setBeanNames("ServiceImpl");
        //设置拦截链名字(这些拦截器是有先后顺序的)
        proxyCreator.setInterceptorNames("transactionInterceptor");
        return proxyCreator;
    }

    /**
     * 定时任务爬取电影数据
     * @return
     */
    @Bean
    public JobDetail movieJob(){
        JobBuilder jobBuilder = JobBuilder.newJob(SpiderJob.class);
        JobDetail jobDetail = jobBuilder
                .usingJobData("path","E:\\doubanSpider\\doubanTop250")
                .build();
        return jobDetail;
    }
    /**
     * 定时任务爬取电影简介
     * @return
     */
    @Bean
    public JobDetail summaryJob(){
        JobBuilder jobBuilder = JobBuilder.newJob(SpiderJob.class);
        JobDetail jobDetail = jobBuilder
                .usingJobData("path","E:\\doubanSpider\\top250summary")
                .build();
        return jobDetail;
    }
    /**
     * 定时任务爬取 热门短评
     * @return
     */
    @Bean
    public JobDetail commentJob(){
        JobBuilder jobBuilder = JobBuilder.newJob(SpiderJob.class);
        JobDetail jobDetail = jobBuilder
                .usingJobData("path","E:\\doubanSpider\\getcomment")
                .build();
        return jobDetail;
    }
    /**
     * 触发器 每周五凌晨
     * @return
     */
    @Bean
    public Trigger movieCronTrigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 ? * FRI"))
                .build();
        return trigger;
    }
    @Bean
    public Trigger summaryCronTrigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 10 0 ? * FRI"))
                .build();
        return trigger;
    }
    @Bean
    public Trigger commentCronTrigger() {
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 ? * FRI"))
                .build();
        return trigger;
    }
    @Bean
    public Scheduler schedulerFactoryBean() throws SchedulerException {
        logger.info("配置定时任务");
        Scheduler scheduler= StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(movieJob(),movieCronTrigger());
        scheduler.scheduleJob(summaryJob(),summaryCronTrigger());
        scheduler.scheduleJob(commentJob(),commentCronTrigger());
        scheduler.start();
        return scheduler;
    }
}
