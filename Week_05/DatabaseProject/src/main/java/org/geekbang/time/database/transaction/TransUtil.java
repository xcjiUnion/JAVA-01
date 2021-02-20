package org.geekbang.time.database.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.geekbang.time.database.jdbc.DbUtil;
import org.geekbang.time.database.jdbc.User;

/**
 * 2)使用事务，PrepareStatement 方式，批处理方式，
 * @author jixch
 *
 */
public class TransUtil {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			//关闭自动提交
			conn.setAutoCommit(false);
			
			//插入
			String sql1 = "insert into user(user_name, sex, age) values(?,?,?)";
			PreparedStatement pstmt1 = conn.prepareStatement(sql1);
			pstmt1.setString(1, "Jimmy");
			pstmt1.setString(2, "男");
			pstmt1.setInt(3, 40);
			pstmt1.executeUpdate();
			
			//更新
			String userName = "Tom";
			String sql2 = "update user set age=" + 27 + ", sex='" + "男" + "' where user_name='" + userName + "'";
			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
			pstmt2.executeUpdate();
			
			//查询
			List<User> userList = new ArrayList<>();
			String sql3 = "select * from user ";
			PreparedStatement pstmt3 = conn.prepareStatement(sql3);
			ResultSet rs = pstmt3.executeQuery();
			while(rs.next()) {
				User tempUser = new User(rs.getString("user_name"),rs.getString("sex"), rs.getInt("age"));
				userList.add(tempUser);
			}
			System.out.println(userList.size());
			
			//提交事务
			conn.commit();
			
			//关闭连接
			pstmt1.close();
			pstmt2.close();
			pstmt3.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
			try {
				if(null != conn) {
					conn.rollback();
				}
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
