package com.cms.scaffold.sys.sys.init;

import com.cms.scaffold.sys.sys.service.SysConfigService;
import com.cms.scaffold.sys.sys.service.SysDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: zhangjiahengpoping@gmail.com
 * @Date: 2018/4/4 16:25
 **/
@Service
public class InitRedisCache implements ApplicationListener<ContextRefreshedEvent> {
    static final Logger logger = LoggerFactory
            .getLogger(InitRedisCache.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("-------加载配置信息 start-------");
        SysConfigService configService = contextRefreshedEvent.getApplicationContext().getBean(SysConfigService.class);
        configService.loadConfigIntoRedis();
        logger.info("-------加载配置信息 end-------");

        logger.info("-------加载字典信息 start-------");
        SysDictService dictService = contextRefreshedEvent.getApplicationContext().getBean(SysDictService.class);
        dictService.loadDictIntoRedis();
        logger.info("-------加载字典信息 end-------");
    }
}
