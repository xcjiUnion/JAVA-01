package org.geekbang.time.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceType {

	private static final ThreadLocal<DataBaseType> DATABASE_TYPE = new ThreadLocal<>();
	
	public enum DataBaseType {
		MASTER, SLAVE
	}
	
	public static void setDataBaseType(DataBaseType dataBaseType) {
		if(dataBaseType == null) {
			throw new NullPointerException();
		}
		log.info("[设置当前数据源为]:" + dataBaseType);
		DATABASE_TYPE.set(dataBaseType);
	}
	
	public static DataBaseType getDataBaseType() {
		DataBaseType dataBaseType = DATABASE_TYPE.get();
		log.info("[获取当前数据源为]:" + dataBaseType);
		return dataBaseType;
	}
	
	public static void clearDataBaseType() {
		DATABASE_TYPE.remove();
	}
}
