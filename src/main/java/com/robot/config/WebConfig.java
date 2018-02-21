package com.robot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import com.robot.client.RobotClient;
import com.robot.client.RobotClientImpl;

@Configuration
@EnableWebMvc
@ComponentScan("com.robot.*")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setViewClass(JstlView.class);
	    viewResolver.setPrefix("/WEB-INF/views/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	@Bean
	public MessageSource messageSource() {
	  ReloadableResourceBundleMessageSource messageSource =
	      new ReloadableResourceBundleMessageSource();
	  //messageSource.setBasename("file:///Users/diegobenavides/eclipse-workspace/SpringAction/src/main/resources");
	  messageSource.setBasename("/WEB-INF/messages/messages");
	  messageSource.setCacheSeconds(10);
	  return messageSource;
	}
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
	  TilesConfigurer tiles = new TilesConfigurer();
	  tiles.setDefinitions(new String[] {
			  "/WEB-INF/layout/tiles.xml"
	  });
	  tiles.setCheckRefresh(true);
	  return tiles;
    }
	
	@Bean
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public RobotClient robotClient() {
	    return new RobotClientImpl();
	}
}
