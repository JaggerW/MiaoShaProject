package com.nju.miaosha.validator;

import com.nju.miaosha.annotation.IsMobile;
import com.nju.miaosha.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author: jaggerw
 * @Description: IsMobile注解实现类
 * @Date: 2020/9/22
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!required && StringUtils.isEmpty(s)){
            return true;
        }else {
            return ValidatorUtil.isMobile(s);
        }
    }
}
