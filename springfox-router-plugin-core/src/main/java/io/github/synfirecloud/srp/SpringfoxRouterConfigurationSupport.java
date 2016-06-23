package io.github.synfirecloud.srp;

import org.resthub.web.springmvc.router.RouterConfigurationSupport;
import org.resthub.web.springmvc.router.RouterHandlerMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Created by fc on 6/23/16.
 */
public abstract class SpringfoxRouterConfigurationSupport extends RouterConfigurationSupport {
	@Bean
	@Autowired
	public SpringRouterRequestHandlerProvider springRouterRequestHandlerProvider(RouterHandlerMapping routerHandlerMapping){
		return new SpringRouterRequestHandlerProvider(routerHandlerMapping);
	}
}