package com.searshc.hs.psc.catalog.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ClientDBConfig {
			
	@Value("${client.db.driverClass}")
	private String driverClass;

	@Value("${client.db.url}")
	private String url;

	@Value("${client.db.username}")
	private String username;

	@Value("${client.db.password}")
	private String password;
  
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(clientDataSource());
	}

	@Bean
	public DataSource clientDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}
}
