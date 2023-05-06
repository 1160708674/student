package 学生基本信息管理平台;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class deleteFrame extends JFrame {
    public deleteFrame()
    {
        super("删除某个学生信息");
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setBounds(300, 300, 350, 200);
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
            this.setPreferredSize(new Dimension(310, 200));
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
            JTextField show = new JTextField(20);
            show.setEditable(false);
            jp3.add(show);
            add(jp3);

            JPanel jp4 = new JPanel();
            jp4.setPreferredSize(new Dimension(620, 30));
            JButton tijiao = new JButton("提交");
            JButton reset = new JButton("重置");
            JButton reset2 = new JButton("菜单");
            jp4.add(tijiao);
            jp4.add(reset);
            jp4.add(reset2);
            add(jp4);
            reset2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    deleteFrame.this.dispose();
                    new btFrame();

                }
            });

            tijiao.addActionListener(new ActionListener()
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
                        show.setText("正在删除数据库信息!请稍后!");
                        try
                        {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/jvsql?serverTimezone=GMT%2B8", "root", "root");
                            String sql = "delete from stu where ? = id and ? = name;";
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1, hao);
                            ps.setString(2, ming);
                            int rows = ps.executeUpdate();
                            if(rows == 1)
                            {
                                show.setText("学生删除成功");
                                id.setText("");
                                name.setText("");
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
