package org.geekbang.time.database.hikari;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.geekbang.time.database.jdbc.DbUtil;
import org.geekbang.time.database.jdbc.User;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 3)配置 Hikari 连接池
 * @author jixch
 *
 */
public class HikariUtil {

	private static String driver;
	private static String url;
	private static String userName;
	private static String password;
	
	private static HikariDataSource hikariDataSource;
	
	static {
		try {
			InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			userName = prop.getProperty("username");
			password = prop.getProperty("password");

			HikariConfig config = new HikariConfig();
			config.setDriverClassName(driver);
			config.setJdbcUrl(url);
			config.setUsername(userName);
			config.setPassword(password);
			config.setMaximumPoolSize(10);
			config.setMinimumIdle(5);
			config.setIdleTimeout(180000L);
			config.setAutoCommit(true);
			config.setPoolName("MyHikariCP");
			config.setMaxLifetime(180000L);
			config.setConnectionTimeout(30000L);
			
			hikariDataSource = new HikariDataSource(config);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return hikariDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeDataSource() {
		hikariDataSource.close();
	}
	
	/**
	 * 新增操作
	 * @param user
	 * @return
	 */
	public static int insert(User user) {
		int result = 0;
		try {
			Connection conn = getConnection();
			String sql = "insert into user(user_name, sex, age) values(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getSex());
			pstmt.setInt(3, user.getAge());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param name
	 * @return
	 */
	public static int delete(String name) {
		int result = 0;
		try {
			Connection conn = getConnection();
			String sql = "delete from user where user_name='" + name + "'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 更新
	 * @param user
	 * @return
	 */
	public static int update(User user) {
		int result = 0;
		try {
			Connection conn = getConnection();
			String sql = "update user set age=" + user.getAge() + ", sex='" + user.getSex() + "' where user_name='" + user.getUserName() + "'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 查询
	 * @param name
	 * @return
	 */
	public static List<User> select(String name) {
		List<User> userList = new ArrayList<>();
		String sql = "select * from user ";
		if(null != name) {
			sql = sql + " where user_name='" + name + "'";
		}
		try {
			Connection conn = getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				User tempUser = new User(rs.getString("user_name"),rs.getString("sex"), rs.getInt("age"));
				userList.add(tempUser);
			}
			pstmt.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}

	public static void main(String[] args) {
		insert(new User("Tom", "男", 20));
		insert(new User("Jane", "女", 25));
		insert(new User("Alex", "男", 30));
		
		update(new User("Tom", "男", 28));

		delete("Alex");
		
		System.out.println(select(null).size());
	}

}
