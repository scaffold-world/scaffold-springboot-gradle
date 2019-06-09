package com.jiaheng.scaffold.route.operate.freemarker.factory;

import com.jiaheng.scaffold.common.util.StringUtil;
import com.jiaheng.scaffold.route.operate.freemarker.impl.ThFormatterTemplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 表头格式工厂类
 * Created by 张嘉恒 on 2018/7/2.
 */
public class ThFormatterFactory {

    private static Logger logger = LoggerFactory.getLogger(ThFormatterFactory.class);

    public static ThFormatterInterface createThFormatter(String type){
        if(StringUtils.isEmpty(type)){
            return  new ThFormatterTemplet();
        }
        //文件名
        String fileName = "ThFormatter" + StringUtil.firstCharUpperCase(type);
        //类路径
        String className = "com.jiaheng.scaffold.route.operate.freemarker.impl."+fileName;

        //生成表头格式实现类
        ThFormatterInterface thFormatterInterface = null;
        try {
            thFormatterInterface =(ThFormatterInterface) Class.forName(className).newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }

        return thFormatterInterface;
    }
}
