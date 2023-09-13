package com.jayus.smallSpring.step18.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class JdbcTest {

    private static DruidDataSource dataSource;

    private static Connection connection;

    private static Statement statement;

    public static void main(String[] args) throws SQLException {
        dataSource = new DruidDataSource();
        dataSource.setDriver(new Driver());
        dataSource.setUrl("jdbc:mysql://localhost:3306/world?useSSL=false");
        dataSource.setPassword("123456");
        dataSource.setUsername("root");

        connection = dataSource.getConnection().getConnection();
        statement = connection.createStatement();
        //ddlTest();
        //dmlTest();
        //dqlTest();
        //batchStatementDMLTest();
        //preparedStatementDMLTest();
        //preparedStatementDQLTest();
        transactionTest();
        destroy();
    }

    public static void ddlTest() throws SQLException {
        boolean execute = statement.execute("        CREATE TABLE `user1` (\n" +
                "  `id` int NOT NULL AUTO_INCREMENT,\n" +
                "  `username` varchar(100) DEFAULT NULL,\n" +
                "        PRIMARY KEY (`id`),\n" +
                "        UNIQUE KEY `user_id_uindex` (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin");
        System.out.println(execute);
    }

    public static void dmlTest() throws SQLException {
        statement.executeUpdate("insert into user(username) values ('c')");
        statement.executeUpdate("update user set username = 'bb' where id = 2");
        statement.executeUpdate("delete from user where id = 1");
    }

    public static void dqlTest() throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from user;");
        int row = 1;
        while (resultSet.next()){
            /*int id = resultSet.getInt("id");
            String username = resultSet.getString("username");*/
            int id = resultSet.getInt(1);
            String username = resultSet.getString(2);
            System.out.printf("第%d行数据:id:%d,username:%s%n", row++, id, username);
        }
    }

    public static void batchStatementDMLTest() throws SQLException {
        String sql1 = "insert into user(username) values('小王')";
        String sql2 = "insert into user(username) values('小刘')";
        String sql3 = "update  user set username='小刘刘' where username='小刘'";
        statement.addBatch(sql1);
        statement.addBatch(sql2);
        statement.addBatch(sql3);
        int[] result = statement.executeBatch();
        for (int i : result) {
            System.out.printf("执行结果:%d\n", i);
        }
    }
    public static void preparedStatementDMLTest() throws SQLException {
        String sql = "insert into user(username) values(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, "d");
        int result = preparedStatement.executeUpdate();
        System.out.println(result);
        // 获取主键
        if (result > 0){
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
                System.out.println(rs.getInt(1));
            }
        }
    }

    public static void preparedStatementDQLTest() throws SQLException {
        String sql = "select * from user where id=? and username=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, 2);
        preparedStatement.setString(2, "bb");
        ResultSet resultSet = preparedStatement.executeQuery();
        int row = 1;
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String username = resultSet.getString("username");
            System.out.printf("第%d行数据:id:%d,username:%s%n", row++, id, username);
        }
    }

    public static void transactionTest() throws SQLException {
        statement.executeUpdate("begin;");
        statement.executeUpdate("insert into user(username) values ('e')");
        statement.executeUpdate("begin;");
        statement.executeUpdate("insert into user(username) values ('f')");
        statement.executeUpdate("rollback ;");
        statement.executeUpdate("commit ;");
    }

    public static void destroy() throws SQLException {
        if (null != statement){
            statement.close();
        }
        if (null != connection){
            connection.close();
        }
        if (null != dataSource){
            dataSource.close();
        }
    }

}
