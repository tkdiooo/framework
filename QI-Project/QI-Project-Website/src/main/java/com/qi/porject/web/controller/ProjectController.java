package com.qi.porject.web.controller;

import com.qi.common.model.exception.VerifyException;
import com.qi.common.model.result.ValidatorResult;
import com.qi.common.util.SpringContextUtil;
import com.qi.common.util.ValidatorUtil;
import com.qi.common.spring.boot.configurer.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

/**
 * Class ProjectController
 *
 * @author 张麒 2017/1/25.
 * @version Description:
 */
@Controller
public class ProjectController {

    @RequestMapping(value = "/index")
    public String index() {
        User user = new User();
////        user.setCreateTime(new Date());
////        user.setCreator(1L);
////        user.setEmail("");
////        user.setGuid(1L);
////        user.setPassWord("");
        user.setUserName("ss");
        ValidatorResult result = ValidatorUtil.validate(user);
        System.out.println(result);
        if (result.isHasErrors()) {
            throw new VerifyException("验证错误", result);
        }
        return "index";
    }

    @RequestMapping(value = "/validator")
    public String validator(@Valid User user, BindingResult br) {
        if (br.hasErrors()) {
            for (ObjectError objectError : br.getAllErrors()) {
                System.out.println("objectError = " + objectError.getDefaultMessage());
            }
        }
        return "index";
    }
}
