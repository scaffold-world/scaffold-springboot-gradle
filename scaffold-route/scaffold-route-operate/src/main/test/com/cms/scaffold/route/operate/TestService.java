package com.cms.scaffold.route.operate;

import com.alibaba.fastjson.JSON;
import com.cms.scaffold.core.util.GenerateUniqueNoUtil;
import com.cms.scaffold.sys.sys.bo.SysMenuBO;
import com.cms.scaffold.sys.sys.service.SysMenuService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public void testSysMenu() {
        List<SysMenuBO> pid = sysMenuService.findByPid(0L);
        logger.info("JSON.toJSONString(pid) = " + JSON.toJSONString(pid));
    }

    @Test
    public void testGenerateUtil() {
        ExecutorService pools = Executors.newCachedThreadPool();
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        CountDownLatch c = new CountDownLatch(10000);
        CountDownLatch d = new CountDownLatch(10000);
        for (int i = 0; i < 10000; i++) {
            pools.execute(() -> {
                try {
                    c.countDown();
                    c.await();
                    map.put(GenerateUniqueNoUtil.generateUniqueNo(), 0);
                    d.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            d.await();
            logger.info(map.size()+"");
            logger.info(map.size()+"");
            Assert.assertEquals(10000, map.size());
            logger.info(map.size()+"");
            logger.info(map.size()+"");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
