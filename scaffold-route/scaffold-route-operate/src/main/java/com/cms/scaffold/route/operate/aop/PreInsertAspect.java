package com.cms.scaffold.route.operate.aop;

import com.cms.scaffold.common.base.BaseEntity;
import com.cms.scaffold.common.util.StringUtil;
import com.cms.scaffold.route.operate.util.UserUtil;
import com.cms.scaffold.sys.sys.domain.SysOperate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * 保存更新记录时，同时更新添加时间和更新时间
 * Created by 张嘉恒 on 2018/3/20.
 */
@Aspect
@Component
@Order(1)
public class PreInsertAspect {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* com.cms.scaffold.*.*.dao.*.insert*(..)) || execution( * com.cms.scaffold.sys.BaseMapper.insert*(..)) " +
            "|| execution( * com.cms.scaffold.core.baseService.BaseMapper.insert*(..))")
    public void insert(JoinPoint joinPoint){

        BaseEntity baseEntity = null;
        try {
            //获取目标方法的参数信息
            Object[] obj = joinPoint.getArgs();
            if(obj!=null && obj.length > 0 && obj[0] instanceof BaseEntity){
                baseEntity = (BaseEntity)obj[0];
                SysOperate sysOperate = UserUtil.getOperatorFromSession();
                if (baseEntity.getAddTime() == null){
                    baseEntity.preInsert();
                    if(sysOperate != null){
                        //插入操作人信息
                        baseEntity.setAddOperate(UserUtil.getOperatorFromSession().getId());
                    }else {
                        baseEntity.setAddOperate(0L);
                    }

                }
                if(sysOperate != null){
                    //插入操作人信息
                    baseEntity.setAddOperate(UserUtil.getOperatorFromSession().getId());
                }else {
                    baseEntity.setAddOperate(0L);
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
