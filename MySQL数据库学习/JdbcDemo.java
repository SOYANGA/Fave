import java.sql.*;

public class JdbcDemo {
    public static void main(String[] args) throws ClassNotFoundException {
//        Class.forName("com.mysql.jdbc.Driver");//加载JDBC驱动程序  1.3后可以不加，DriverManager源码里说明了，Connection类中会自动调用 close()方法
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {//建立数据库链接(把finally里的代码放到try（里）)

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/memo?" + "user=root&password=123456789&useUnicode=true&characterEncoding=UTF-8");
            //创建命令（statement）
            statement = connection.createStatement();
            //执行SQL语句
            resultSet = statement.executeQuery(
                    "select id,group_id,title,content,is_protected, backgroup,is_remind,remind_time,created_time,modify_time from memo_info");
            //处理结果集
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String background = resultSet.getString("backgroup");
                Date createTime = resultSet.getDate("created_time");

                System.out.println(String.format("Memo: id=%d,background=%s, title=%s, content=%s, createTime=%s", id, background, title, content, createTime.toString()));
            }


        } catch (SQLException e) {
            e.printStackTrace();  //没有catch的正确操作 ，解决错误的方法
        } finally {
            //关闭结果集
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //关闭命令
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //关闭连接命令
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }

        }

    }
}

//Driver 类中的源码
//        try {
//                DriverManager.registerDriver(new Driver());
//                } catch (SQLException var1) {
//                throw new RuntimeException("Can't register driver!");
//                }