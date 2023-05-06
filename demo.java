package 学生基本信息管理平台;
import java.sql.*;
public class demo {
    public static void main(String[] args)
    {
        try {
            //前两行连接数据库 端口：3306 数据库：lxx 账号：root 密码：root
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/lxxsql?serverTimezone=GMT%2B8", "root", "root");
            //这行是数据库指令 问号代表值的个数
            String sql = "insert into prj values(?,?,?,?,?);";
            //对ps变量使用sql操作
            PreparedStatement ps = con.prepareStatement(sql);
            //ps操作中要输入的两个值
            ps.setString(1, "001");
            ps.setString(2, "包子");
            ps.setString(3, "1");
            ps.setString(4, "1.5");
            ps.setString(5, "1998..5.17");
            //成功验证
            int rows = ps.executeUpdate();
            if(rows == 1)
            {
                System.out.println("添加成功");

            }
        }
        catch (ClassNotFoundException e1){} catch (SQLException e) {
            e.printStackTrace();
            //失败验证
            System.out.println("添加失败");
        }
    }
}
