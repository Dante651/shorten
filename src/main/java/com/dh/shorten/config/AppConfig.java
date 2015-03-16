package com.dh.shorten.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class AppConfig {
	
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "org.postgresql.Driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "pswd";
    private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:postgresql://localhost:5432/shorten";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "postgres";
 
    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";
    private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.dh.shorten.model";
    private static final String PROPERTY_NAME_HBM2DDL_AUTO = "create"; 
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
		dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
		dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
		dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);
		
		return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactory.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
		entityManagerFactory.setJpaProperties(hibernateProperties());
		
		return entityManagerFactory;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		
		return transactionManager;
	}
	
	private Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.put("PROPERTY_NAME_HIBERNATE_DIALECT", PROPERTY_NAME_HIBERNATE_DIALECT);
		properties.put("PROPERTY_NAME_HIBERNATE_SHOW_SQL", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
		properties.put("PROPERTY_NAME_HBM2DDL_AUTO", PROPERTY_NAME_HBM2DDL_AUTO);
		
		return properties;
	}
	
	

}
