package com.jcble.parking.api.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcble.parking.api.controller.BaseController;
import com.jcble.parking.common.model.user.UserDto;
import com.jcble.parking.common.service.user.UserService;

/**
 * 用户模块
 * 
 * @author hjzeng-2015
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 3、 用户注册接口
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String register(HttpServletRequest request) throws Exception {
    	UserDto user = new UserDto();
    	user.setAccount("23456789");
    	userService.register(user);
        return "";
    }

   

   
}
