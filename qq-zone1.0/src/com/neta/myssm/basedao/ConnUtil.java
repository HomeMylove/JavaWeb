package com.neta.myssm.basedao;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnUtil {
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

    /*
    public static String DRIVER;
    public static String URL;
    public static String USER;
    public static String PWD;
    */

    private static Properties properties;

    static {
        InputStream resource = ConnUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties = new Properties();

        try {
            properties.load(resource);

            /*
            DRIVER = properties.getProperty("jdbc.driver");
            URL = properties.getProperty("jdbc.url");
            USER = properties.getProperty("jdbc.user");
            PWD = properties.getProperty("jdbc.pwd");

             */
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static Connection createConn() {
        try {
//            DruidDataSource druidDataSource = new DruidDataSource();
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

            /*
            druidDataSource.setDriverClassName(DRIVER);
            druidDataSource.setUrl(URL);
            druidDataSource.setPassword(PWD);
            druidDataSource.setUsername(USER);
             */
//            Class.forName(DRIVER);
//            return DriverManager.getConnection(URL, USER, PWD);
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Connection getConn() {
        Connection conn = threadLocal.get();
        if (conn == null) {
            conn = createConn();
            assert conn != null;
            threadLocal.set(conn);
        }

        return threadLocal.get();
    }

    // 移除
    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if (conn == null) {
            return;
        }
        if (!conn.isClosed()) {
            conn.close();
            threadLocal.set(null);
        }
    }


};


