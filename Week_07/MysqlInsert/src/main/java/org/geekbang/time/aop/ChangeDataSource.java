package org.geekbang.time.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.geekbang.time.config.DataSourceType;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ChangeDataSource {

	@Before("@annotation(org.geekbang.time.annotation.SelectReadOnly)")
	public void setReadOnlyDataSource() {
		log.info("----setReadOnlyDataSource---");
		DataSourceType.setDataBaseType(DataSourceType.DataBaseType.SLAVE);
	}
	
	@After("@annotation(org.geekbang.time.annotation.SelectReadOnly)")
	public void setMasterDataSource() {
		log.info("----setMasterDataSource---");
		DataSourceType.setDataBaseType(DataSourceType.DataBaseType.MASTER);
	}
}
