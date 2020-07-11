package com.kuang.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //拦截
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        //设置安全管理器
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro内置过滤器
        //anon: 无需认证就可以访问
        //authc： 必须认证了才能访问
        //perms： 拥有对某个资源的权限才能访问
        //role： 拥有某个角色权限才能访问

        //拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

        //授权 正常情况下，没有授权会跳转到未授权页面
        filterMap.put("/user/add","perms[user:add]");  //perms[user:add]: 代表的是授权的凭据
        filterMap.put("/user/update","perms[user:update]");  //perms[user:add]: 代表的是授权的凭据

        //    filterMap.put("/user/add","authc");
        //    filterMap.put("/user/update","authc");
        filterMap.put("/user/*","authc");  //* :通配符

        bean.setFilterChainDefinitionMap(filterMap);

        //设置登录请求
        bean.setLoginUrl("/toLogin");
        //跳转未授权页面
        bean.setUnauthorizedUrl("/noauth");
        return bean;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //自定义realm对象   需自定义类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
