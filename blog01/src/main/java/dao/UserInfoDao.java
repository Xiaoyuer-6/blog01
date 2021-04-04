package dao;

import models.UserInfo;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用来操作 userinfo 表
 */
public class UserInfoDao {

    /**
     * 用户添加【注册功能】数据库
     */
    public int add(String username, String password) throws SQLException {
        int result = 0;
        Connection connection = DBUtils.getConnection();//连接到已经登陆的数据库
        String sql = "insert into userinfo(username,password) values(?,?)";//sql的插入语句
        PreparedStatement statement = connection.prepareStatement(sql);//
        statement.setString(1, username);//得到两个值
        statement.setString(2, password);
        result = statement.executeUpdate();
        return result;
    }
    /**
     * 查询用户（登录部分。可以查询到就是登录成功）
     * @param username
     * @param password
     * @return
     */
    public UserInfo getUser(String username, String password) throws SQLException {
        UserInfo userInfo = new UserInfo();//new下user info，获得属性和构造方法
        // jdbc 查询数据库
        Connection connection = DBUtils.getConnection();
        String sql = "select * from userinfo where username=? and password=?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        // 查询数据库
        ResultSet resultSet = statement.executeQuery();//获得数据库中所有的用户名和密码
        while (resultSet.next()) {//获得数据库中所有的用户名和密码，存在结果集中
            userInfo.setId(resultSet.getInt("id"));
            userInfo.setUsername(resultSet.getString("username"));
            userInfo.setPassword(resultSet.getString("password"));
            //..
        }
        // 关闭数据库连接
        DBUtils.close(connection, statement, resultSet);
        return userInfo;
    }


}