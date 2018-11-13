package com.everis;

import com.everis.login.boundary.LoginService;
import com.everis.login.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class StartupConfigurer {

    @Inject
    private LoginService loginService;

    @PostConstruct
    public void init() {
        User user = new User();
        byte[] bytes = {
                '1',
                '4'
        };
        user.setUsername("bmota");
        user.setEmail("bmota@test.com");
        user.setPassword("teste");
        user.setSalt(bytes);
        loginService.createUser(user);
    }
}