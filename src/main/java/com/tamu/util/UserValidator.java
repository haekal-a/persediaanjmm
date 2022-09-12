//package com.kondangan.util;
//
//import com.kondangan.domain.table.User;
//import com.kondangan.service.IUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//
//@Component
//public class UserValidator implements Validator {
//    @Autowired
//    private IUserService userService;
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return User.class.equals(aClass);
//    }
//
//    @Override
//    public void validate(Object o, Errors errors) {
//        User user = (User) o;
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user", "NotEmpty");
//        if (user.getUser().length() < 5 || user.getUser().length() > 32) {
//            errors.rejectValue("user", "Size.userForm.username");
//        }
//        if (userService.findByUsername(user.getUser()) != null) {
//            errors.rejectValue("user", "Duplicate.userForm.username");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pass", "NotEmpty");
//        if (user.getPass().length() < 8 || user.getPass().length() > 32) {
//            errors.rejectValue("pass", "Size.userForm.password");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nama", "NotEmpty");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "level", "NotEmpty");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kdKantor", "NotEmpty");
//        if (user.getKdKantor().length() != 3) {
//            errors.rejectValue("kdKantor", "Size.userForm.kdKantor");
//        }
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "kanwil", "NotEmpty");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "since", "NotEmpty");
//
//    }
//}
