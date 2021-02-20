package org.geekbang.time.database.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 1)使用 JDBC 原生接口，实现数据库的增删改查操作
 * @author jixch
 *
 */
public class DbUtil {
	
	private static String driver;
	private static String url;
	private static String userName;
	private static String password;
	
	static {
		try {
			InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("db.properties");
			Properties prop = new Properties();
			prop.load(in);
			
			driver = prop.getProperty("driver");
			url = prop.getProperty("url");
			userName = prop.getProperty("username");
			password = prop.getProperty("password");

			Class.forName(driver);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获得连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, userName, password);
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
