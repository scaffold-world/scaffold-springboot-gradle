package com.cms.scaffold.common.util;

import com.cms.scaffold.common.base.BaseEntity;
import com.cms.scaffold.common.base.CompareEntity;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 比较实体类工具
 * @author: zhangjiahengpoping@gmail.com
 * @date: 2019-02-28 00:55
 **/
public class CompareEntityUtil {

    public static List<CompareEntity> compare(Object objNew, Object objOld ){
        List<CompareEntity> compareEntitieList = new ArrayList<>();

        if(objOld instanceof BaseEntity && objNew instanceof BaseEntity){
            BaseEntity baseEntityOld = (BaseEntity) objOld;
            BaseEntity baseEntityNew = (BaseEntity) objNew;
            try {
                Class clazz = baseEntityOld.getClass();
                Field[] fields = baseEntityOld.getClass().getDeclaredFields();
                for (Field field : fields) {
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                    Method getMethod = pd.getReadMethod();
                    Object o1 = getMethod.invoke(baseEntityOld);
                    Object o2 = getMethod.invoke(baseEntityNew);
                    String s1 = o1 == null ? "" : o1.toString();//避免空指针异常
                    String s2 = o2 == null ? "" : o2.toString();//避免空指针异常
                    //思考下面注释的这一行：会bug的，虽然被try catch了，程序没报错，但是结果不是我们想要的
                    if (o1!=null && !s1.equals(s2)) {
                        CompareEntity compareEntity = new CompareEntity();
                        compareEntity.setField(field.getName());
                        compareEntity.setValueOld(s1);
                        compareEntity.setValueNew(s2);
                        compareEntitieList.add(compareEntity);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        return compareEntitieList;
    }

}
