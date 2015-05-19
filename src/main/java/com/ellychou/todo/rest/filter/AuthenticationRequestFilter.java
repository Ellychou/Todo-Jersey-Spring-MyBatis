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
 * AuthenticationRequestFilter filter most of the requests, except login and signup request,
 * check if token is in the request hearder
 * @author szhou
 * @version 1.0.1
 * @since 2015/3/5
 *
 */
public class AuthenticationRequestFilter implements ContainerRequestFilter {
    private static final Logger log = Logger.getLogger(AuthenticationRequestFilter.class);

    @Autowired
    private TokenService tokenService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (requestContext.getMethod().equalsIgnoreCase("options")) {
            requestContext.abortWith(Response.ok().build());
        }
        // Get the request url path, if it is login or signup request, ignore them
        String path = requestContext.getUriInfo().getPath();
        if (path.equals("/user/login") || path.equals("/user/signup") || path.equals("/user/testRest")) {
            return;
        }
        //Get the token from the request header
        String token = requestContext.getHeaderString("Authorization");
        //Get the user by this token
        User user = StringUtils.isEmpty(token) ? null : tokenService.getUserByToken(token);

        //Set the user to the securityContext for the request if the user is not null
        if (user == null) {
            // requestContext.abortWith(
            //  Response.status(Response.Status.BAD_REQUEST).entity("User must log in first").build()
            //  );
            requestContext.setSecurityContext(new AuthenticationSecurityContext());
        } else {
            requestContext.setSecurityContext(new AuthenticationSecurityContext(user));
            log.info("securitycontext: " + requestContext.getSecurityContext().getUserPrincipal().getName());
        }

    }
}
