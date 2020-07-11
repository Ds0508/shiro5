package com.kuang.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MyController {

    @RequestMapping({"/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg","hello,liangzia");
        return "index";
    }
    // add 请求

     // updata 请求

    @RequestMapping("toLogin")
    public  String toLogin(){
        return "login";
    }

    @RequestMapping("login")
    public String login(String username,String password,Model model){
        // 获取当前的用户（用户名和密码）
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登录
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try{
            subject.login(token);  //执行登录方法 , 如果没有异常说明ok
            return "index";
        }catch (UnknownAccountException e){   //用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){  //密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
    @RequestMapping("/moauth")
    public String Unauthorized(){
         return "moauth";
    }
}
