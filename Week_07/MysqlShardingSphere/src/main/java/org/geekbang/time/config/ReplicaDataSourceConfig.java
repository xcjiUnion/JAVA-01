package org.geekbang.time.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;

import org.geekbang.time.util.DataSourceUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReplicaDataSourceConfig {

	@Bean
	public DataSource getDataSouce() throws SQLException {
		ReplicaQueryDataSourceRuleConfiguration dataSourceConfig = new ReplicaQueryDataSourceRuleConfiguration(
				"replica_query_ds", "primary_ds", Arrays.asList("replica_ds_0"), null);
		ReplicaQueryRuleConfiguration ruleConfig = new ReplicaQueryRuleConfiguration(
				Collections.singleton(dataSourceConfig), Collections.emptyMap());
		return ShardingSphereDataSourceFactory.createDataSource(createDataSourceMap(),
				Collections.singleton(ruleConfig), new Properties());
	}

	private Map<String, DataSource> createDataSourceMap() {
		Map<String, DataSource> result = new HashMap<>();
		result.put("primary_ds", DataSourceUtil.createDataSource("primary_ds"));
		result.put("replica_ds_0", DataSourceUtil.createDataSource("replica_ds_0"));
		return result;
	}
}
