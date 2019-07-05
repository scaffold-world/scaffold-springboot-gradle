package com.cms.scaffold.route.operate.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.Properties;

/**
 * @description:
 * @author: zjh
 * @date: 2019-02-27 11:21
 **/
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = { Statement.class })})
@Component

public class SqlStatementInterceptor implements Interceptor{

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
//        Object[] args = invocation.getArgs();
//        StatementHandler statementHandler= (StatementHandler) invocation.getTarget();
//        //通过MetaObject优雅访问对象的属性，这里是访问statementHandler的属性;：MetaObject是Mybatis提供的一个用于方便、
//        //优雅访问对象属性的对象，通过它可以简化代码、不需要try/catch各种reflect异常，同时它支持对JavaBean、Collection、Map三种类型对象的操作。
//        MetaObject metaObject = MetaObject
//                .forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
//                        new DefaultReflectorFactory());
//        //先拦截到RoutingStatementHandler，里面有个StatementHandler类型的delegate变量，其实现类是BaseStatementHandler，然后就到BaseStatementHandler的成员变量mappedStatement
//        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
//        //id为执行的mapper方法的全路径名，如com.uv.dao.UserMapper.insertUser
//        String id = mappedStatement.getId();
//        //sql语句类型 select、delete、insert、update
//        String sqlCommandType = mappedStatement.getSqlCommandType().toString();
//        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
//
//        if(!sqlCommandType.equals("select")){
//            BoundSql boundSql = statementHandler.getBoundSql();
//            Object parameterObject =  boundSql.getParameterObject();
//            if(parameterObject instanceof Map){
//                Map map  = (Map) parameterObject;
//                for(Object obj:map.values()){
//                    if(obj instanceof BaseEntity){
//                        BaseEntity entity = (BaseEntity)obj;
//                        entity.setTableName("test_table");
//                    }
//                }
//            }
//        }





        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
