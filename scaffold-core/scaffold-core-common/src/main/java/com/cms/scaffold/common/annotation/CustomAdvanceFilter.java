package com.cms.scaffold.common.annotation;

import com.cms.scaffold.common.constant_manual.AdvanceFilterOpConstantManual;

import java.lang.annotation.*;

/**
 * Created by zhangjiahengpoping@gmail.com on 2018/8/2.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomAdvanceFilter {

    String join() default AdvanceFilterOpConstantManual.ADVANCE_FILTER_JOIN_AND;

    String lb() default "";

    String field() default "";

    String op() default "";

    String value() default "";

    String rb() default "";
}
