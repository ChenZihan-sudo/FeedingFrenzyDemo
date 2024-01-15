package test;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Font;

public class Demo2 extends JFrame {
    JFrame jf = null;
    JPanel jp = null;
    JButton jb = null;

    public Demo2() {
        jf = new JFrame();
        jp = new JPanel();

        // 设置JPanel背景颜色
        jp.setForeground(Color.BLACK);
        jb = new JButton("按钮");

        // 查看按钮控件默认字体与设置字体
        System.out.println(jb.getFont());
        Font f1 = new Font("宋体", Font.PLAIN, 16);
        jb.setFont(f1);

        // JPanel不要布局
        jp.setLayout(null);
        jp.add(jb);

        // 使用setBounds()定位与调整按钮大小
        jb.setBounds(150, 100, 150, 50);
        jf.add(jp);

        // 设置JFrame起始位置与宽、高
        jf.setTitle("按钮定位");
        jf.setBounds(0, 0, 400, 300);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        Demo2 bpij = new Demo2();
    }
}