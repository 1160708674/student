package 学生基本信息管理平台;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class selectFrame extends JFrame {
    public selectFrame()
    {
        super("查找某个学生的信息");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(300, 300, 600, 400);
        this.setLayout(new FlowLayout());
        this.setResizable(false);
        init();
        this.setVisible(true);
    }
    private void init()
    {
        add(new myJpanel());
    }
    class myJpanel extends JPanel
    {
        myJpanel()
        {
            this.setBackground(Color.white);
            this.setPreferredSize(new Dimension(550, 350));
            init();
        }
        private void init()
        {
            add(new JLabel("请输入要查找的学生的姓名:"));
            JTextField jtf = new JTextField(15);
            add(jtf);
            JButton tijiao = new JButton("提交");
            add(tijiao);
            JButton clear = new JButton("清除");
            JButton clear2 = new JButton("菜单");
            add(clear);
            add(clear2);
            JTextArea jta = new JTextArea(17,48);
            jta.setEditable(false);
            jta.setLineWrap(true);
            add(new JScrollPane(jta));
            clear2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    selectFrame.this.dispose();
                    new btFrame();
                }
            });

            tijiao.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String str = jtf.getText();
                    if(str.equals(""))
                    {
                        jta.append("你没有输入姓名\r\n");
                    }else
                    {
                        try
                        {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/jvsql?serverTimezone=GMT%2B8", "root", "root");
                            String sql = "select * from stu where ? = name";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1, str);
                            ResultSet rs = ps.executeQuery();
                            if(rs.next())
                            {
                                String id = rs.getString("id");
                                String name = rs.getString("name");
                                String sex = rs.getString("sex");
                                int age = rs.getInt("age");
                                String score = rs.getString("score");
                                String address = rs.getString("address");
                                jta.append("--------------------------------\r\n");
                                jta.append("学号:" + id + "\r\n");
                                jta.append("姓名:" + name + "\r\n");
                                jta.append("性别:" + sex + "\r\n");
                                jta.append("年龄:" + age + "\r\n");
                                jta.append("学分:" + score + "\r\n");
                                jta.append("地址:" + address + "\r\n");
                                jta.append("--------------------------------\r\n");
                            }else
                            {
                                jta.append("没有找到你要找的学生!\r\n");
                            }
                        }catch(ClassNotFoundException e1)
                        {
                            e1.printStackTrace();
                        }catch(SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                    }
                }
            });
            clear.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    jta.setText("");
                }
            });
        }
    }
}
