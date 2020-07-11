package com.kuang.config;

import com.kuang.pojo.User;
import com.kuang.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行了+>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");
        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal(); //拿到User对象

        //设置当前用户权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了+>认证doGetAuthenticationInfo");
//        //用户名，密码数据库中取
//        String name = "root";
//        String password = "123456";

        UsernamePasswordToken usertoken = (UsernamePasswordToken)token;

        //连接真实数据库
        User user = userService.queryUserByName(usertoken.getUsername());

        if (user==null){ //没有这个数据
            return null;  // 抛出异常 UnknownAccountException
        }
        //可以进行MD5加密   盐值加密：在MD5的基础上再加盐值
        //密码认证，shiro做 ,加密了
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }

}
