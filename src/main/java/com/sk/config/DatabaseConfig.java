package com.sk.config;

import java.io.IOException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import com.alibaba.druid.pool.DruidDataSource;

/**
 * Database Config
 * 
 * @author y00202274
 *
 */
@Configuration
@MapperScan(basePackages = { "com.sk.mapper" })
public class DatabaseConfig implements EnvironmentAware {

	private RelaxedPropertyResolver mybatisProps;

	private static Logger logger = LogManager.getLogger();

	@Override
	public void setEnvironment(final Environment environment) {
		this.mybatisProps = new RelaxedPropertyResolver(environment, "mybatis.");
	}

	/**
	 * Data Source
	 */
	@Bean
	public DataSource redistestDataSource(@Value("${spring.datasource.driverClassName}") String driver,
			@Value("${spring.datasource.url}") String url, @Value("${spring.datasource.username}") String username,
			@Value("${spring.datasource.password}") String password,
			@Value("${spring.datasource.initialSize}") int size, @Value("${spring.datasource.maxActive}") int max,
			@Value("${spring.datasource.minIdle}") int min,
			@Value("${spring.datasource.timeBetweenEvictionRunsMillis}") int timeBetweenEvictionRunsMillis,
			@Value("${spring.datasource.minEvictableIdleTimeMillis}") int minEvictableIdleTimeMillis,
			@Value("${spring.datasource.validationQuery}") String validationQuery) throws IOException {

		DruidDataSource datasource = new DruidDataSource();

		logger.info("database url:" + url);

		datasource.setUrl(url);
		datasource.setDriverClassName(driver);
		datasource.setUsername(username);
		datasource.setPassword(password);

		datasource.setInitialSize(size);
		datasource.setMaxActive(max);
		datasource.setMinIdle(min);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);

		return datasource;
	}

	/**
	 * SqlSessionFactory
	 */
	@Bean
	@ConditionalOnMissingBean
	public SqlSessionFactoryBean sqlSessionFactory(@Qualifier("redistestDataSource") DataSource redistestDataSource)
			throws IOException {
		try {
			SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(redistestDataSource);
			sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
					.getResources(mybatisProps.getProperty("mapperLocations")));
			// sessionFactory
			// .setConfigLocation(new DefaultResourceLoader()
			// .getResource(mybatisProps
			// .getProperty("configLocation")));

			return sessionFactory;
		} catch (IOException e) {
			return null;
		}

	}

	/**
	 * Transaction Manager
	 */
	@Bean
	@ConditionalOnMissingBean
	public DataSourceTransactionManager transactionManager(@Qualifier("redistestDataSource") DataSource redistestDataSource)
			throws IOException {
		return new DataSourceTransactionManager(redistestDataSource);
	}

	public RelaxedPropertyResolver getMybatisProps() {
		return mybatisProps;
	}

	public void setMybatisProps(RelaxedPropertyResolver mybatisProps) {
		this.mybatisProps = mybatisProps;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		DatabaseConfig.logger = logger;
	}
}