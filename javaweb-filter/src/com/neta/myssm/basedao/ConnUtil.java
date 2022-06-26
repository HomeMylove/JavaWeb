package com.neta.myssm.basedao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtil {
    private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver" ;
    public static final String URL = "jdbc:mysql://localhost:3306/fruitdb?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    public static final String USER = "root";
    public static final String PWD = "neta520" ;

    private static Connection createConn(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null ;
    }

    public static Connection getConn(){
        Connection conn = threadLocal.get();
        if(conn == null ){
            conn = createConn();
            assert conn != null;
            threadLocal.set(conn);
        }

        return threadLocal.get();
    }

    // 移除
    public static void closeConn() throws SQLException {
        Connection conn = threadLocal.get();
        if(conn == null ){
            return;
        }
        if(!conn.isClosed()){
            conn.close();
            threadLocal.set(null);
        }
    }


};


