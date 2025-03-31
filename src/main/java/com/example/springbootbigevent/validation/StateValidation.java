package com.example.springbootbigevent.validation;

import com.example.springbootbigevent.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidation implements ConstraintValidator<State,String> {
    /**
     *
     * @param s value to be validated
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null){
            return false;
        }
        if(s.equals("已发布") || s.equals("草稿")){
            return true;
        }
        return false;
    }
}
