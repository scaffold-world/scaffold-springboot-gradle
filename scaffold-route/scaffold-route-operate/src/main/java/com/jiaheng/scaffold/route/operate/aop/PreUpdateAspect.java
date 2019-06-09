package com.jiaheng.scaffold.route.operate.aop;

import com.jiaheng.scaffold.common.base.BaseEntity;
import com.jiaheng.scaffold.common.util.StringUtil;
import com.jiaheng.scaffold.route.operate.util.UserUtil;
import com.jiaheng.scaffold.sys.sys.domain.SysOperate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by 张嘉恒 on 2018/3/25.
 */
@Aspect
@Component
@Order(2)
public class PreUpdateAspect {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.jiaheng.scaffold.*.*.dao.update*(..)) || execution( * com.jiaheng.scaffold.sys.BaseMapper.update*(..))")
    public void update(JoinPoint joinPoint){

        BaseEntity baseEntity = null;

        try {
            //获取目标方法的参数信息
            Object[] obj = joinPoint.getArgs();
            if(obj!=null && obj.length > 0 && obj[0] instanceof BaseEntity){
                baseEntity = (BaseEntity)obj[0];
                baseEntity.preUpdate();
                SysOperate sysOperate = UserUtil.getOperatorFromSession();
                if(sysOperate != null){
                    //插入操作人信息
                    baseEntity.setUpdateOperate(sysOperate.getId());
                }else {
                    baseEntity.setUpdateOperate(0L);
                }

            }
        }catch (Exception ex){
            logger.info(ex.getMessage(),ex);
            if(baseEntity != null && StringUtil.isBlank(baseEntity.getAddOperate())){
                baseEntity.setAddOperate(0L);
            }
        }

    }
}
