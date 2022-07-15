package com.practice.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.practice")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig
{

	//variable to hold properties
	@Autowired
	private Environment env;

	//logger
	private Logger logger = Logger.getLogger(getClass().getName());


	// bean for ViewResolver

	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	// bean for security sources

	@Bean
	public DataSource securityDataSource()
	{
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

		try
		{
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		}
		catch (PropertyVetoException e)
		{
			throw  new RuntimeException(e);
		}

		// connection check

		logger.info("\n---> jdbc.url : "+env.getProperty("jdbc.url"));
		logger.info("\n---> jdbc.user : "+env.getProperty("jdbc.user"));

		//set db connection properties
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));

		//connection pool properties
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

		return securityDataSource;
	}

	//helper to get properties

	private int getIntProperty(String propName)
	{
		String propVal = env.getProperty(propName);

		int intValue = Integer.parseInt(propVal);

		return intValue;
	}

}









