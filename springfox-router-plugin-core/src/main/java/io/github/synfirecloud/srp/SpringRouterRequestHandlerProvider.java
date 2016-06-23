package io.github.synfirecloud.srp;

import org.resthub.web.springmvc.router.Router;
import org.resthub.web.springmvc.router.Router.Route;
import org.resthub.web.springmvc.router.RouterConfigurationSupport;
import org.resthub.web.springmvc.router.RouterHandlerMapping;
import org.resthub.web.springmvc.router.support.RouterHandlerResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.HeadersRequestCondition;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import springfox.documentation.RequestHandler;
import springfox.documentation.schema.Model;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationModelsProviderPlugin;
import springfox.documentation.spi.service.RequestHandlerProvider;
import springfox.documentation.spi.service.contexts.RequestMappingContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Map.Entry;

/**
 * Created by fc on 6/20/16.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SpringRouterRequestHandlerProvider implements RequestHandlerProvider {

	private final static Logger logger = LoggerFactory.getLogger(SpringRouterRequestHandlerProvider.class);
	private RouterHandlerMapping routerHandlerMapping;
	private Map<String,RequestMethod> requestMethodMap = new HashMap<String, RequestMethod>();

	public SpringRouterRequestHandlerProvider(RouterHandlerMapping routerHandlerMapping) {
		logger.debug("SpringRouterRequestHandlerProvider");
		this.routerHandlerMapping = routerHandlerMapping;
		requestMethodMap.put("DELETE",RequestMethod.DELETE);
		requestMethodMap.put("GET",RequestMethod.GET);
		requestMethodMap.put("HEAD",RequestMethod.HEAD);
		requestMethodMap.put("OPTIONS",RequestMethod.OPTIONS);
		requestMethodMap.put("PATCH",RequestMethod.PATCH);
		requestMethodMap.put("POST",RequestMethod.POST);
		requestMethodMap.put("PUT",RequestMethod.PUT);
		requestMethodMap.put("TRACE",RequestMethod.TRACE);
	}

	@Override
	public List<RequestHandler> requestHandlers() {
		List<RequestHandler> requestHandlers = new ArrayList<RequestHandler>();
		try {
			Field methodResolverField = routerHandlerMapping.getClass().getDeclaredField("methodResolver");
			methodResolverField.setAccessible(true);
			RouterHandlerResolver methodResolver = (RouterHandlerResolver)methodResolverField.get(routerHandlerMapping);
			Method doResolveHandlerMethod = methodResolver.getClass().getDeclaredMethod("doResolveHandler",new Class[]{Route.class,String.class});
			doResolveHandlerMethod.setAccessible(true);
			for(Route r:Router.routes){
				HandlerMethod handlerMethod = (HandlerMethod)doResolveHandlerMethod.invoke(methodResolver,new Object[]{r,r.getAction()});
				PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition(r.getPath());
				RequestMethodsRequestCondition requestMethodsRequestConditionn = new RequestMethodsRequestCondition(requestMethodMap.get(r.getMethod()));
				RequestMappingInfo requestMappingInfo = new RequestMappingInfo(r.getAction(),patternsRequestCondition,requestMethodsRequestConditionn,null,null,null,null,null);
				requestHandlers.add(new RequestHandler(requestMappingInfo,handlerMethod));
				logger.debug(handlerMethod.toString());
			}
		}catch (Exception e){
			logger.error(e.toString());
		}
		logger.debug(requestHandlers.toString());
		return requestHandlers;
	}
}
