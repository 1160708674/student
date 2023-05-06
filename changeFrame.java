package 学生基本信息管理平台;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class changeFrame extends JFrame {
    public changeFrame()
    {
        super("修改某个学生信息");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(300, 300, 400, 450);
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
            this.setPreferredSize(new Dimension(370, 552));
            init();
        }
        private void init()
        {
            JPanel jp1 = new JPanel();
            jp1.setPreferredSize(new Dimension(370, 30));
            jp1.add(new JLabel("学号:"));
            JTextField id = new JTextField(20);
            jp1.add(id);
            add(jp1);

            JPanel jp2 = new JPanel();
            jp2.setPreferredSize(new Dimension(370, 30));
            jp2.add(new JLabel("姓名:"));
            JTextField name = new JTextField(20);
            jp2.add(name);
            add(jp2);
            JButton tijiao1 = new JButton("提交");
            add(tijiao1);
            JButton reset = new JButton("重置");
            add(reset);

            add(new JLabel("----------------------------------------------------------------------------------"));

            JPanel jp5 = new JPanel();
            jp5.setPreferredSize(new Dimension(370, 170));
            jp5.add(new JLabel("学号:"));
            JTextField newid = new JTextField(35);
            //  newid.setEditable(false);
            jp5.add(newid);

            jp5.add(new JLabel("姓名:"));
            JTextField newname = new JTextField(35);
//                        newname.setEditable(false);
            jp5.add(newname);

            jp5.add(new JLabel("性别:"));
            JTextField newsex = new JTextField(35);
//                        newsex.setEditable(false);
            jp5.add(newsex);

            jp5.add(new JLabel("年龄:"));
            JTextField newage = new JTextField(35);
            jp5.add(newage);

            jp5.add(new JLabel("学分:"));
            JTextField newscroll = new JTextField(35);
            jp5.add(newscroll);

            jp5.add(new JLabel("地址:"));
            JTextField newaddress = new JTextField(35);
            jp5.add(newaddress);

            add(jp5);

            JPanel jp3 = new JPanel();
            jp3.setPreferredSize(new Dimension(620, 30));
            JTextField show = new JTextField(20);
            show.setEditable(false);
            jp3.add(show);
            add(jp3);

            JPanel jp4 = new JPanel();
            jp4.setPreferredSize(new Dimension(370, 30));
            JButton tijiao2 = new JButton("提交");
            JButton tijiao3 = new JButton("菜单");
            jp4.add(tijiao2);
            jp4.add(tijiao3);
            add(jp4);
            tijiao3.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    changeFrame.this.dispose();
                    new btFrame();

                }
            });


            tijiao1.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String hao = id.getText(); //学号
                    String ming = name.getText();//姓名
                    if(hao.equals(""))
                    {
                        show.setText("学号不能为空");
                    }else if(ming.equals(""))
                    {
                        show.setText("姓名不能为空");
                    }else
                    {
                        show.setText("正在连接数据库!请稍后!");
                        try
                        {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/jvsql?serverTimezone=GMT%2B8", "root", "root");
                            show.setText("连接数据库成功!");
                            String sql = "select * from stu where name=? and id=?;";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1, ming);
                            ps.setString(2, hao);
                            ResultSet rs = ps.executeQuery();
                            if(rs.next())
                            {
                                rs.previous();
                                while(rs.next())
                                {
                                    String id = rs.getString("id");
                                    String name = rs.getString("name");
                                    String sex = rs.getString("sex");
                                    int age = rs.getInt("age");
                                    double score = rs.getDouble("score");
                                    String address = rs.getString("address");
                                    newid.setText(id);
                                    newname.setText(name);
                                    newsex.setText(sex);
                                    newage.setText(age+"");
                                    newscroll.setText(score+"");
                                    newaddress.setText(address);
                                }
                                show.setText("数据读取成功!");
                            }else
                            {
                                show.setText("找不到该学生的相关信息");
                            }
                        }catch(ClassNotFoundException e1)
                        {
                            e1.printStackTrace();
                            show.setText("学生删除失败");
                        }catch(SQLException e1)
                        {
                            e1.printStackTrace();
                            show.setText("学生删除失败");
                        }
                    }
                }
            });

            tijiao2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String id1 = id.getText(); //学号
                    String name1 = name.getText();//姓名
                    String hao = newid.getText(); //学号
                    String ming = newname.getText();//姓名
                    String bie = newsex.getText(); //性别
                    String ling = newage.getText(); //年龄
                    int nian = Integer.parseInt(ling);
                    String cheng = newscroll.getText(); //成绩
                    double ji = Double.parseDouble(cheng);
                    String dizhi = newaddress.getText();//地址

                    if(hao.equals(""))
                    {
                        show.setText("学号不能为空");
                    }else if(ming.equals(""))
                    {
                        show.setText("姓名不能为空");
                    }else if(bie.equals(""))
                    {
                        show.setText("性别不能为空");
                    }else if(ling.equals(""))
                    {
                        show.setText("年龄不能为空");
                    }else if(cheng.equals(""))
                    {
                        show.setText("成绩不能为空");
                    }else if(dizhi.equals(""))
                    {
                        show.setText("地址不能为空");
                    }else
                    {
                        show.setText("正在提交到数据库!请稍后!");
                        try
                        {
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lksql", "root", "root");
                            String sql = "update stu set id=?,name=?,sex=?,age=?,score=?,address=? where name=? and id=?;";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1, hao);
                            ps.setString(2, ming);
                            ps.setString(3, bie);
                            ps.setInt(4, nian);
                            ps.setDouble(5, ji);
                            ps.setString(6, dizhi);
                            ps.setString(7, name1);
                            ps.setString(8, id1);
                            int rows = ps.executeUpdate();
                            if(rows == 1)
                            {
                                show.setText("学生修改成功");
                                id.setText("");
                                name.setText("");

                                newid.setText("");
                                newname.setText("");
                                newsex.setText("");
                                newage.setText("");
                                newscroll.setText("");
                                newaddress.setText("");
                            }
                        }catch(ClassNotFoundException e1)
                        {
                            e1.printStackTrace();
                            show.setText("学生添加失败");
                        }catch(SQLException e1)
                        {
                            e1.printStackTrace();
                            show.setText("学生添加失败");
                        }
                    }
                }
            });

            reset.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    id.setText("");
                    name.setText("");
                    show.setText("重置成功");
                }
            });
        }
    }
}
