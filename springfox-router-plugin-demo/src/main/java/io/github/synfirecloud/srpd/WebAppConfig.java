package io.github.synfirecloud.srpd;

import io.github.synfirecloud.srp.SpringRouterRequestHandlerProvider;
import io.github.synfirecloud.srp.SpringfoxRouterConfigurationSupport;
import org.resthub.web.springmvc.router.RouterConfigurationSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fc on 6/20/16.
 */

@Configuration
@ComponentScan
@EnableAutoConfiguration //only use spring-boot need
public class WebAppConfig extends SpringfoxRouterConfigurationSupport {
	private final static Logger logger = LoggerFactory.getLogger(WebAppConfig.class);
	@Override
	public List<String> listRouteFiles() {
		List<String> routeFiles = new ArrayList<String>();
		routeFiles.add("classpath:router.conf");
		return routeFiles;
	}
}
