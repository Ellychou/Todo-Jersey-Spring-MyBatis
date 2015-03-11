package com.ellychou.todo.rest.security;

import com.ellychou.todo.rest.dao.UserDao;
import com.ellychou.todo.rest.entities.User;
import com.ellychou.todo.rest.service.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * Created by szhou on 2015/3/9.
 */
public class AuthenticationSecurityContext implements SecurityContext {
    private User user;

    @Autowired
    private UserDao userDao;

    public AuthenticationSecurityContext() {
        user = null;
    }

    public AuthenticationSecurityContext(User user) {
        this.user = user;
    }

    @Override
    public Principal getUserPrincipal() {
        return user;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
