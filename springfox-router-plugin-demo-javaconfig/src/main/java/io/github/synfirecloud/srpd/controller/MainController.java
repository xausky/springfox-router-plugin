package io.github.synfirecloud.srpd.controller;

import org.resthub.web.springmvc.router.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fc on 6/20/16.
 */
@Controller
public class MainController {

	@ResponseBody
	public String RouterIndex(@RequestParam String name){
		return "RouterIndex:"+name;
	}

	@ResponseBody
	public String RouterUser(@RequestParam String name){
		return "RouterUser:"+name;
	}

	@ResponseBody
	@RequestMapping(value="/MappingUser.do",method = {RequestMethod.POST})
	public String MappingUser(@RequestParam String name){
		return "MappingUser:"+name;
	}

	@ResponseBody
	@RequestMapping(value="/MappingRouter.do",method = {RequestMethod.GET})
	public String MappingRouter(){
		return Router.routes.toString();
	}
}
