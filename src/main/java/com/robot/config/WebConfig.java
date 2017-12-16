package com.robot.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan("com.robot.web")
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver resolver =
		new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		//resolver.setExposeContextBeansAsAttributes(true);
		resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
		return resolver;
		//return new TilesViewResolver();
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
}
