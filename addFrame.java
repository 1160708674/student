package 学生基本信息管理平台;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class addFrame extends JFrame {
    public addFrame()
    {
        super("添加一个学生信息");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(300, 300, 350, 350);
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
            this.setPreferredSize(new Dimension(300, 300));
            init();
        }
        private void init()
        {
            JPanel jp1 = new JPanel();
            jp1.setPreferredSize(new Dimension(620, 30));
            jp1.add(new JLabel("学号:"));
            JTextField id = new JTextField(20);
            jp1.add(id);
            add(jp1);

            JPanel jp2 = new JPanel();
            jp2.setPreferredSize(new Dimension(620, 30));
            jp2.add(new JLabel("姓名:"));
            JTextField name = new JTextField(20);
            jp2.add(name);
            add(jp2);

            JPanel jp3 = new JPanel();
            jp3.setPreferredSize(new Dimension(620, 30));
            jp3.add(new JLabel("性别:"));
            JTextField sex = new JTextField(20);
            jp3.add(sex);
            add(jp3);

            JPanel jp4 = new JPanel();
            jp4.setPreferredSize(new Dimension(620, 30));
            jp4.add(new JLabel("年龄:"));
            JTextField age = new JTextField(20);
            jp4.add(age);
            add(jp4);

            JPanel jp5 = new JPanel();
            jp5.setPreferredSize(new Dimension(620, 30));
            jp5.add(new JLabel("学分:"));
            JTextField scroe = new JTextField(20);
            jp5.add(scroe);
            add(jp5);

            JPanel jp6 = new JPanel();
            jp6.setPreferredSize(new Dimension(620, 30));
            jp6.add(new JLabel("地址:"));
            JTextField address = new JTextField(20);
            jp6.add(address);
            add(jp6);

            JPanel jp7 = new JPanel();
            jp7.setPreferredSize(new Dimension(620, 30));
            JTextField show = new JTextField(20);
            show.setEditable(false);
            jp7.add(show);
            add(jp7);

            JPanel jp8 = new JPanel();
            jp8.setPreferredSize(new Dimension(620, 30));
            JButton tijiao = new JButton("提交");
            JButton reset = new JButton("重置");
            JButton reset2 = new JButton("菜单");
            jp8.add(tijiao);
            jp8.add(reset);
            jp8.add(reset2);
            add(jp8);

            tijiao.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    String hao = id.getText(); //学号
                    String ming = name.getText();//姓名
                    String bie = sex.getText(); //性别
                    String ling = age.getText(); //年龄
                    int nian = Integer.parseInt(ling);
                    String cheng = scroe.getText(); //成绩
                    double ji = Double.parseDouble(cheng);
                    String dizhi = address.getText();//地址

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
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/jvsql?serverTimezone=GMT%2B8", "root", "root");
                            String sql = "insert into stu values(?,?,?,?,?,?);";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1, hao);
                            ps.setString(2, ming);
                            ps.setString(3, bie);
                            ps.setInt(4, nian);
                            ps.setDouble(5, ji);
                            ps.setString(6, dizhi);
                            int rows = ps.executeUpdate();
                            if(rows == 1)
                            {
                                show.setText("学生添加成功");
                                id.setText("");
                                name.setText("");
                                sex.setText("");
                                age.setText("");
                                scroe.setText("");
                                address.setText("");
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
                    sex.setText("");
                    age.setText("");
                    scroe.setText("");
                    address.setText("");
                    show.setText("重置成功!");
                }
            });
            reset2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    addFrame.this.dispose();
                    new btFrame();

                }
            });

        }
    }
}



