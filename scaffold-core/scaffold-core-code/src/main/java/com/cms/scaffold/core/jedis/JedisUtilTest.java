package com.cms.scaffold.core.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

/**
 * Created by zjh on 2018/3/23.
 */
public class JedisUtilTest {


    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"120.55.190.250",6373,8000,"pwd8ok8",0);


        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            double result = 0;
            result = jedis.incrByFloat("test",1);
            if(jedis.incrByFloat("test",1)>2.0){
                System.out.println("1");
            }

            if(jedis.incrByFloat("test",1)>2.0){
                System.out.println("2");
            }

        } catch (JedisException e) {
            throw e;
        }
    }
}
