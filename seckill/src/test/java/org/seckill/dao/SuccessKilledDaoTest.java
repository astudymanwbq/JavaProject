package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by BBQ on 2017/5/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
    @Resource
    private SuccessKilledDao successKilledDao;
    @Test
    public void insertSuccessKilled() throws Exception {
        long id=1001L;
        long userPhone=13129038323L;
        int insertCount=successKilledDao.insertSuccessKilled(id,userPhone);
        System.out.println("insertCount="+insertCount);


    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id=1001L;
        long userPhone=13129038323L;
        SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(id,userPhone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}