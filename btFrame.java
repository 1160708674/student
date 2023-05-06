package 学生基本信息管理平台;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class btFrame extends Frame{
    public btFrame()
    {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(350, 400);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        // 创建一个按钮
        final JButton btn = new JButton("输入人员信息");
        final JButton btn2 = new JButton("更改人员信息");
        final JButton btn3 = new JButton("删除人员信息");
        final JButton btn4 = new JButton("选择人员信息");
        final JButton btn5 = new JButton("展示人员信息");
        final JButton btn6 = new JButton("退出人员信息");
        final JButton btn7 = new JButton("输入成员成绩");
        btn.setPreferredSize(new Dimension(620, 30));
        btn2.setPreferredSize(new Dimension(620, 30));
        btn3.setPreferredSize(new Dimension(620, 30));
        btn4.setPreferredSize(new Dimension(620, 30));
        btn5.setPreferredSize(new Dimension(620, 30));
        btn6.setPreferredSize(new Dimension(620, 30));
        btn7.setPreferredSize(new Dimension(620, 30));
        // 添加按钮的点击事件监听器
        btn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                // 获取到的事件源就是按钮本身
                // JButton btn = (JButton) e.getSource();
                new addFrame();
            }
        });
        btn2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                // 获取到的事件源就是按钮本身
                // JButton btn = (JButton) e.getSource();
                new changeFrame();
            }
        });
        btn3.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                // 获取到的事件源就是按钮本身
                // JButton btn = (JButton) e.getSource();
                new deleteFrame();
            }
        });
        btn4.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                // 获取到的事件源就是按钮本身
                // JButton btn = (JButton) e.getSource();
                new selectFrame();
            }
        });
        btn5.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                // 获取到的事件源就是按钮本身
                // JButton btn = (JButton) e.getSource();
                new showFrame();

            }
        });
        btn6.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                // 获取到的事件源就是按钮本身
                // JButton btn = (JButton) e.getSource();
                System.exit(0);

            }
        });
        panel.add(btn);
        panel.add(btn7);
        panel.add(btn2);
        panel.add(btn3);
        panel.add(btn4);
        panel.add(btn5);
        panel.add(btn6);
        jf.setContentPane(panel);
        jf.setVisible(true);
    }
}
