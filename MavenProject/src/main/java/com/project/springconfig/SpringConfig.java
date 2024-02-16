package com.project.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Controller//to mention that this class contains the configuration details
@EnableWebMvc//to mention it spring configuration
@ComponentScan("com.project.controller")//where to find the controllers
public class SpringConfig {
	
	@Autowired
	private ApplicationContext applicationcontext;
	
	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		//this is the object which finds the front end files-locate 
		SpringResourceTemplateResolver resolver=new SpringResourceTemplateResolver();
		//folder
		resolver.setPrefix("/WEB-INF/pages/");
		//extension
		resolver.setSuffix(".html");
		
		//which container
		resolver.setApplicationContext(applicationcontext);
		
		//type of file
		resolver.setTemplateMode(TemplateMode.HTML);
		
		return resolver;
	}
	
	@Bean
	public SpringTemplateEngine TemplateEngine() {
		//load the file found by the resolver 
		SpringTemplateEngine engine=new SpringTemplateEngine();
		//mapping the engine to the resolver
		engine.setTemplateResolver(templateResolver());
		//for accepting the expressio language eg:${temp.name}
		engine.setEnableSpringELCompiler(true);
		return engine;
	}
	
	
	@Bean
	public ThymeleafViewResolver viewResolver() {
		
		ThymeleafViewResolver resolver=new ThymeleafViewResolver();
		resolver.setTemplateEngine(TemplateEngine());
		
		return resolver;
	}

}
