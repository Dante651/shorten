package com.dh.shortener.config;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("com.dh.shortener")
@EnableJpaRepositories("com.dh.shortener.repository")
@EnableTransactionManagement
//@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "org.postgresql.Driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "pswd";
	private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:postgresql://localhost:5432/shorten";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "postgres";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "true";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.dh.shortener.model";
	private static final String PROPERTY_NAME_HBM2DDL_AUTO = "update";

	@Override
	public void configureMessageConverters(
			List<HttpMessageConverter<?>> converters) {
		converters.add(converter());
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(PROPERTY_NAME_DATABASE_USERNAME);
		dataSource.setPassword(PROPERTY_NAME_DATABASE_PASSWORD);
		dataSource.setDriverClassName(PROPERTY_NAME_DATABASE_DRIVER);
		dataSource.setUrl(PROPERTY_NAME_DATABASE_URL);

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory
				.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactory
				.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
		entityManagerFactory.setJpaProperties(hibernateProperties());

		return entityManagerFactory;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory()
				.getObject());

		return transactionManager;
	}

	@Bean
	public MappingJackson2HttpMessageConverter converter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		String[] supportedMT = { MediaType.APPLICATION_JSON_VALUE };
		List supportedMediaTypes = Arrays.asList(supportedMT);
		converter.setPrefixJson(false);
		converter.setSupportedMediaTypes(supportedMediaTypes);
		return converter;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", PROPERTY_NAME_HIBERNATE_DIALECT);
		properties.put("hibernate.show_sql", PROPERTY_NAME_HIBERNATE_SHOW_SQL);
		properties.put("hibernate.hbm2ddl.auto", PROPERTY_NAME_HBM2DDL_AUTO);
		properties.put("hibernate.connection.isolation", 8);

		return properties;
	}

}
