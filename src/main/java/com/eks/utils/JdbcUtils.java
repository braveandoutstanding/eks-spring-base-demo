package com.eks.utils;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author XuYongJie
 * @description JDBC增删改查通用方法,增删改如果有异常会自动回滚,支持MySql、Oracle等多种数据库
 */
public class JdbcUtils {
    /**
     * @author XuYongJie
     * @description JDBC查询通用方法
     * @params url(比如:jdbc:oracle:thin:@127.0.0.1:1521:eks)、username(数据库用户名)、password(用户密码)、sql(查询语句)
     * @return 一个Map<String,Object>就是一行数据,key为列名,value为其对应的值,List表示多条数据
     */
    public static List<Map<String,Object>> queryByJdbc(String url,String username,String password,String sql) throws Exception{
        Boolean queryHaveException = false;
        Exception queryException = null;
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Connection connection = null;// 创建一个数据库连接
        Statement statement = null;
        ResultSet resultSet = null;// 创建一个结果集对象
        try{
            //高版本的Oracle和MySql也不用写Class.forName()，因为采用了最新的SPI技术，驱动的类名在jar包的META-INF/services/java.sql.Driver文件里。
            //可以把类路径下所有jar包中META-INF/services/java.sql.Driver文件中定义的类加载上来，此类必须继承自java.sql.Driver。
            //查看源码发现,DriverManager会遍历所有的DriverInfo,Connection con = aDriver.driver.connect(url, info),返回能连接上的驱动
//            Class.forName("com.mysql.jdbc.Driver");//加载Mysql数据库驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
            connection = DriverManager.getConnection(url, username, password);// 获取连接
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);// 执行查询，注意括号中不需要再加参数
            ResultSetMetaData metaData = resultSet.getMetaData();//获取键名
            int columnCount = metaData.getColumnCount();//获取行的数量
            while (resultSet.next()){
                Map<String,Object> rowData = new TreeMap<String,Object>();//使用TreeMap保证顺序,一个Map相当于数据库中一行数据(相当于该表的enity)
                for (int i = 1; i <= columnCount; i++) {//请注意，列序号从 1 开始，而不是从 0 开始
                    rowData.put(metaData.getColumnName(i), resultSet.getObject(i));//获取键名及值
                }
                list.add(rowData);
            }
        } catch (Exception e){
            e.printStackTrace();
            queryHaveException = true;
            queryException = e;
        } finally {
            try{
                if (resultSet != null){
                    resultSet.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if (statement != null){
                    statement.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if (connection != null){
                    connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(queryHaveException){
            throw queryException;
        }
        return list;
    }

    /**
     * @author XuYongJie
     * @description JDBC增删改通用方法,增删改如果有异常会自动回滚
     * @params url(比如:jdbc:oracle:thin:@127.0.0.1:1521:eks)、username(数据库用户名)、password(用户密码)、sql(增删改语句)
     * @return int[]表示对应sql影响的行数
     */
    public static int[] updateByJdbc(String url,String username,String password,List<String> sqlList) throws Exception {
        int[] result = null;
        Boolean updateHaveException = false;
        Exception updateException = null;
        Connection connection = null;
        Statement statement = null;
        try {
            //高版本的Oracle和MySql也不用写Class.forName()，因为采用了最新的SPI技术，驱动的类名在jar包的META-INF/services/java.sql.Driver文件里。
            //可以把类路径下所有jar包中META-INF/services/java.sql.Driver文件中定义的类加载上来，此类必须继承自java.sql.Driver。
            //查看源码发现,DriverManager会遍历所有的DriverInfo,Connection con = aDriver.driver.connect(url, info),返回能连接上的驱动
//            Class.forName("com.mysql.jdbc.Driver");//加载Mysql数据库驱动
            Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("加载Oracle驱动程序失败");
        }
        try {
            connection = DriverManager.getConnection(url, username, password);// 获取数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("获取数据库连接失败");
        }
        statement = connection.createStatement();//创建会话
        if(connection.getAutoCommit()) {//如果是自动提交则改为手动提交
            connection.setAutoCommit(false);
        }
        try{
            for (String sqlString : sqlList) {
                statement.addBatch(sqlString);
            }
            result = statement.executeBatch();
            if (!connection.getAutoCommit()) {//不是自动提交则手动提交事物
                connection.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
            updateHaveException = true;
            updateException = e;
            if (!connection.getAutoCommit()) {//如果有错误则回滚
                connection.rollback();
            }
        }finally {
            try{
                if (statement != null){//关闭会话
                    statement.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if (!connection.getAutoCommit()) {//恢复事务状态
                    connection.setAutoCommit(true);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try{
                if (connection != null){//关闭数据库连接
                    connection.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(updateHaveException){
            throw updateException;
        }
        return result;
    }
}