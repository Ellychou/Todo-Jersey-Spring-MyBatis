package com.ellychou.todo.rest.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;

import com.ellychou.todo.rest.entities.User;
import com.ellychou.todo.rest.security.AuthenticationSecurityContext;
import com.ellychou.todo.rest.service.TokenService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * Created by szhou on 2015/3/5.
 */
public class AuthenticationRequestFilter implements ContainerRequestFilter {
    private static final Logger log = Logger.getLogger(AuthenticationRequestFilter.class);

    @Autowired
    private TokenService tokenService;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        //log.info(requestContext.getHeaders());
        String token = requestContext.getHeaderString("Authorization");
        log.info("token"+ token);
        User user = StringUtils.isEmpty(token) ? null : tokenService.getUserByToken(token);

        if (user == null) {
            requestContext.abortWith(
                    Response.status(Response.Status.BAD_REQUEST).entity("User must log in first").build()
            );
        }else{
            requestContext.setSecurityContext(new AuthenticationSecurityContext(user));
        }
    }
}
