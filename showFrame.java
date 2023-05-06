package 学生基本信息管理平台;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class showFrame extends JFrame {
    public showFrame()
    {
        super("显示所有学生信息");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(300, 300, 650, 400);
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
            this.setPreferredSize(new Dimension(620, 350));
            init();
        }
        private void init()
        {
            JButton jb = new JButton("显示所有学生信息");
            add(jb);
            JButton clear = new JButton("清除数据");
            JButton clear2 = new JButton("菜单");
            add(clear);
            add(clear2);
            JTextArea jta = new JTextArea(17,55);
            jta.setEditable(false);
            jta.setLineWrap(true);
            add(new JScrollPane(jta));
            clear2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    showFrame.this.dispose();
                    new btFrame();

                }
            });

            jb.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    try
                    {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/jvsql?serverTimezone=GMT%2B8", "root", "root");
                        String sql = "select * from stu";
                        PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                        ResultSet rs = ps.executeQuery();
                        if(rs.next())
                        {
                            rs.previous();
                            jta.setText("");
                            int count = 1 ;
                            while(rs.next())
                            {
                                String id = rs.getString("id");
                                String name = rs.getString("name");
                                String sex = rs.getString("sex");
                                int age = rs.getInt("age");
                                String score = rs.getString("score");
                                String address = rs.getString("address");
                                jta.append("--------------------------------\r\n");
                                jta.append("学生"+count++ + "\r\n");
                                jta.append("学号:" + id + "\r\n");
                                jta.append("姓名:" + name + "\r\n");
                                jta.append("性别:" + sex + "\r\n");
                                jta.append("年龄:" + age + "\r\n");
                                jta.append("学分:" + score + "\r\n");
                                jta.append("地址:" + address + "\r\n");
                                jta.append("--------------------------------\r\n");
//                                                                jta.append("学号\t姓名\t性别\t年龄\t学分\t地址\r\n");
//                                                                jta.append(id + "     " + name + "\t" + sex + "\t" + age + "\t" + score + "\t" + address+"\r\n");
                            }
                        }else
                        {
                            jta.append("数据库里面空空如野!");
                        }
                    }catch(ClassNotFoundException e1)
                    {
                        e1.printStackTrace();
                    }catch(SQLException e1)
                    {
                        e1.printStackTrace();
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
