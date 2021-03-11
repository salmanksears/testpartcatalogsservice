package com.searshc.hs.psc.catalog.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CatalogDBConfig {
			  
	@Value("${catalog.db.driverClass}")
	private String driverClass;

	@Value("${catalog.db.url}")
	private String url;

	@Value("${catalog.db.username}")
	private String username;

	@Value("${catalog.db.password}")
	private String password;
  
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(catalogDataSource());
	}

	@Bean
	public DataSource catalogDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}
}
