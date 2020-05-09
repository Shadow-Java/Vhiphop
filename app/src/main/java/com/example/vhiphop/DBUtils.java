package com.example.vhiphop;

/*
 *作者：created by 影子 on 2020/5/8 15:44
 *邮箱：1723928492@qq.com
 */
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *1.主线程不能访问网络 so创建子线程
 *2.关闭各种操作 防止内存泄漏
 *3.IP地址是在命令行输入ipconfig后查看到的，我是在家里的局域网看到的网址，在学校里那种内网还没试过
 *4.要访问的数据库，就是刚才建的librarydb
 *5.用户名就是你MySQL中的用户，比如root，一定要给他授予权限，前面说到了
 */
public class DBUtils {//Mysql数据库

    private static final String TAG = "DBUtils";
    private static String driver = "com.mysql.jdbc.Driver";// MySql驱动
    private static String user = "root";// 用户名
    private static String password = "123guojing";// 密码

    private static Connection getConnection(String dbName) {

        Connection conn = null;

                try {
                    Class.forName(driver); //加载驱动
                    String ip = "192.168.56.1";
                    conn = DriverManager.getConnection(
                            "jdbc:mysql://" + ip + ":3306/" + dbName,
                            user, password);//本机的IPV4地址
                    System.out.println("MySQL数据库连接成功");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

       return conn;
    }

    public static HashMap<String, String> getUserInfoByName(String name) {//name=videos
        HashMap<String, String> map = new HashMap<>();
        Connection conn = getConnection("vhiphop");//根据数据库名称连接
        try {
            Statement st = conn.createStatement();
            String sql = "select * from vhiphop where name = '" + name + "'";// mysql简单的查询语句。这里是根据user表的name字段来查询某条记录
            ResultSet res = st.executeQuery(sql);// 执行sql查询语句并返回结果集
            if (res == null) {
                return null;
            } else {
                int cnt = res.getMetaData().getColumnCount();
                Log.e("DBUtils","列总数：" + cnt);
                res.next();
                for (int i = 1; i <= cnt; ++i) {//下标是从 1 开始的
                    String field = res.getMetaData().getColumnName(i);
                    map.put(field, res.getString(field));
                }

                conn.close();//关闭操作 防止内存泄漏
                st.close();
                res.close();
                return map;

            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, " 数据操作异常");
            return null;
        }

    }

}
