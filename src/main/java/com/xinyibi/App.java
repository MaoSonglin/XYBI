package com.xinyibi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.Getter;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(basePackages={"com.xinyibi.mapper","com.xinyibi.dao"})
public class App implements WebMvcConfigurer,ApplicationContextAware
{
    public static void main( String[] args )
    {
    	ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    
    	DataSource ds = context.getBean(DataSource.class);
    	try (Connection conn = ds.getConnection();){
			String sql = "select count(1) from account";
			try(Statement statement = conn.createStatement();){
				statement.execute(sql);
			}
		} catch (Exception e) {
			try(Connection conn2 = ds.getConnection()){
				String propter = context.getBean(Environment.class).getProperty("database.init.script","xinyibi-sqlite.sql");
				ClassPathResource classPathResource = new ClassPathResource(propter) ;

				EncodedResource encodedResource = new EncodedResource(classPathResource,"UTF-8");
				ScriptUtils.executeSqlScript(conn2, encodedResource);
			} catch (SQLException e1) {
				context.close();
			}
		}
    }
    private static @Getter ApplicationContext applicationContext;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration registration = registry.addInterceptor(new AccessInterceptor());
		registration.addPathPatterns("/**");
		registration.excludePathPatterns("/user/**","/error","/register","/toLogin","/login","/file/src/**","*.jsp");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").allowCredentials(true);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		App.applicationContext = applicationContext;
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");
	}
   
    
}
