package com.ellychou.todo.rest.security;

import com.ellychou.todo.rest.dao.UserDao;
import com.ellychou.todo.rest.entities.User;
import com.ellychou.todo.rest.service.UserRestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * AuthenticationSecurityContext SecurityContext with the user data
 * if the pass the AuthenticationRequestFilter
 * @author szhou
 * @version 1.0.1
 * @since 2015/3/9
 *
 */
public class AuthenticationSecurityContext implements SecurityContext {

    private static final Logger log = Logger.getLogger(AuthenticationSecurityContext.class);
    private User user;

    @Autowired
    private UserDao userDao;

    public AuthenticationSecurityContext() {
        user = null;
    }

    public AuthenticationSecurityContext(User user) {
        this.user = user;
    }

    /**
     * User entity implements Principal
     * @return user
     */
    @Override
    public Principal getUserPrincipal() {
        log.info("princeple: " + user);
        return this.user;
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
