package org.geekbang.time.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.zaxxer.hikari.HikariDataSource;


@Configuration
public class DataSourceConfig {
	
	@Primary
	@Bean
	@Qualifier("masterDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public HikariDataSource masterDataSource(DataSourceProperties properties) {
		return new HikariDataSource();

	}

	@Bean
	@Qualifier("slaveDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.slave")
	public HikariDataSource slaveDataSource(DataSourceProperties properties) {
		return new HikariDataSource();
	}

	@Bean(name="dynamicDataSource")
	public DynamicDataSource returnDynamicDs(@Qualifier("masterDataSource")DataSource masterDataSource, 
			@Qualifier("slaveDataSource")DataSource slaveDataSource) {
		Map<Object, Object> targetDs = new HashMap<>();
		targetDs.put(DataSourceType.DataBaseType.MASTER, masterDataSource);
		targetDs.put(DataSourceType.DataBaseType.SLAVE, slaveDataSource);
		
		DynamicDataSource dyDs = new DynamicDataSource();
		dyDs.setTargetDataSources(targetDs);
		dyDs.setDefaultTargetDataSource(masterDataSource);
		return dyDs;
	}
	
	@Bean("sqlSessionFactory")
	public SqlSessionFactory getSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dynamicDataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
		return bean.getObject();
	}
}
