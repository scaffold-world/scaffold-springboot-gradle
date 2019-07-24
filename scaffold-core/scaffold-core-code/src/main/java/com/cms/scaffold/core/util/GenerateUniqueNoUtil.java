package com.cms.scaffold.core.util;

import com.cms.scaffold.common.util.DateUtil;
import com.cms.scaffold.core.jedis.JedisUtils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GenerateUniqueNoUtil {

    private static final String REDIS_INCR_NO_KEY = "generate:incr:no:key";

    public static String generateUniqueNo() {
        Long perfix = Long.valueOf(DateUtil.dateStr10(new Date()));
        Long suffix = 0L;
        Long incr;
        try {
            incr = JedisUtils.incr(REDIS_INCR_NO_KEY, 1);
            suffix += incr;
        } catch (Exception e) {
            incr = new Random().nextLong();
        }
        return (perfix + suffix) + "";
    }


}
