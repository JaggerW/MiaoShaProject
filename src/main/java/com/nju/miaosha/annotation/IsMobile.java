package com.nju.miaosha.annotation;

import com.nju.miaosha.validator.IsMobileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @Author: jaggerw
 * @Description: IsMobile注解类
 * @Date: 2020/9/22
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IsMobileValidator.class}
)
public @interface IsMobile {

    // mobile是否可以为空，默认是不能为空
    boolean required() default true;

    String message() default "手机号格式错误!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
