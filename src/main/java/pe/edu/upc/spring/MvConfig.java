package pe.edu.upc.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvConfig implements WebMvcConfigurer {
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("error").setViewName("error");
	}
}
