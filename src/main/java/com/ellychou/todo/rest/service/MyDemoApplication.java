package com.ellychou.todo.rest.service;

import com.ellychou.todo.rest.filter.AuthenticationRequestFilter;
import com.ellychou.todo.rest.filter.CORSResponseFilter;
import com.ellychou.todo.rest.filter.LoggingResponseFilter;
import com.ellychou.todo.rest.security.AuthenticationSecurityContext;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Registers the components to be used by the JAX-RS application
 * @author szhou
 * @version 1.0.1
 * @since 2015/2/19
 *
 */
public class MyDemoApplication extends ResourceConfig {

    /**
	* Register JAX-RS application components.
	*/	
	public MyDemoApplication(){
		register(RequestContextFilter.class);
		register(JacksonFeature.class);		
		//register(LoggingResponseFilter.class);
		register(CORSResponseFilter.class);

        register(EventRestService.class);
        register(UserRestService.class);
        register(AuthenticationRequestFilter.class);
        register(AuthenticationSecurityContext.class);

	}
}
