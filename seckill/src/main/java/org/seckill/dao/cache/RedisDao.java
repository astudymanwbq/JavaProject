package org.seckill.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by BBQ on 2017/5/11.
 */
public class RedisDao {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    private JedisPool jedisPool;

    public RedisDao(String ip,int port){
        jedisPool=new JedisPool(ip,port);
    }
    private RuntimeSchema<Seckill> schema=RuntimeSchema.createFrom(Seckill.class);
    public Seckill getSeckill(long seckillId){
        //redis操作逻辑
        try {
            Jedis jedis=jedisPool.getResource();
            try{
                String key="seckill:"+seckillId;
                //jedis redis 没有实现内部序列化
                //get->byte[]->反序列化->对象   从redis中取得
                //自定义序列化
                //protostuff: 需要pojo 就是有get和set方法的对象
                byte[] bytes=jedis.get(key.getBytes());
                //缓存中获取到
                if(bytes!=null){
                    //空对象
                    Seckill seckill=schema.newMessage();
                    //按照schema把bytes中的数据传入到seckill空对象中，反序列化seckill
                    ProtostuffIOUtil.mergeFrom(bytes,seckill,schema);
                    return seckill;
                }
            }finally{
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
    public String putSeckill(Seckill seckill){
        //set Object(Seckill)->序列化->bytes

        try {
            Jedis jedis=jedisPool.getResource();
            try{
                String key="seckill:"+seckill.getSeckillId();
                byte[] bytes=ProtostuffIOUtil.toByteArray(seckill,schema,
                        LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                //超时缓存
                int timeout=60*60;
                String result=jedis.setex(key.getBytes(),timeout,bytes);
                return result;
            }
            finally {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return null;
    }
}
