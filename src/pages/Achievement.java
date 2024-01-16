package src.pages;

import javax.swing.*;
import src.PageManager;
import src.utils.DataStorager;
import src.utils.Utils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import src.sql.*;
import java.sql.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Achievement extends PageBase {
    public Achievement() throws IOException {
        super();
        String className = Utils.getClassName(this);
        Initialization(className);
    }

    private ArrayList<String> string2Array(String string) {
        ArrayList<String> resultList = new ArrayList<>();
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            resultList.add(matcher.group(1));
        }

        return resultList;
    }

    private void display_achievements() throws IOException, SQLException {
        // sql request
        sqlrequest request = new sqlrequest("feedingfrenzy", "192.168.31.166:3306", "root", "sql12119370317");
        ResultSet rs;
        ArrayList<String> achievement_array = new ArrayList<>();
        rs = request.get_data("SELECT id,achievements  FROM user_data where id =2");

        // transvert string to array
        while (rs.next()) {
            achievement_array = string2Array(rs.getString("achievements"));
            // System.out.println(achievement_array);
        }

        // 显示成就
        int startX = 160;// 图标的起始位置
        int startY = 150;
        int width_offset = 140;// 图标之间的间隔
        int height_offset = 120;
        int colmun_num = 6;// 每行的个数
        int word_offset_X = -10;// 字体与图片的偏移量
        int word_offset_Y = 80;
        for (Integer i = 0; i < achievement_array.size(); i++) {
            System.out.println(achievement_array.get(i).getClass().getSimpleName());
            PageManager.addComponent(pageName, "achievements_get",
                    frame.setBackground(DataStorager.getImage(achievement_array.get(i)),
                            startX + i * width_offset % (width_offset * colmun_num),
                            startY + i / colmun_num * height_offset));
            // System.out.println(startX + i * width_offset % (width_offset * colmun_num));
            // System.out.println(startY + i / colmun_num * height_offset);
            String achievement2replace = "ACHIEVEMENTS_GET";
            String achievement_id = achievement_array.get(i).replace(achievement2replace, "");
            System.out.println(achievement_id);
            PageManager.addComponent(pageName, "achievements_name",
                    frame.setBackground(DataStorager.getImage("ACHIEVEMENTS_NAME" + achievement_id),
                            startX + i * width_offset % (width_offset * colmun_num) + word_offset_X,
                            startY + i / colmun_num * height_offset + word_offset_Y));
        }

    }

    @Override
    public void pageComponentInitialize() throws IOException {
        JButton returnIndexBtn = frame.setImageButton(
                DataStorager.getImage("RETURN_BTN"),
                DataStorager.getImage("RETURN_PRESS_BTN"),
                0, 550, // *
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        PageManager.switchPageTo("Index");
                    }
                });
        PageManager.addComponent(pageName, "returnIndexBtn", returnIndexBtn);

        // 添加成就
        try {
            display_achievements();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        // 成就背景
        JLabel background = frame.setBackground(DataStorager.getImage("ACHIEVEMENT_BG"), 150, 50);
        PageManager.addComponent(pageName, "background", background);

        // 大背景
        JLabel main_background = frame.setBackground(DataStorager.getImage("MAIN_BG2"), 0, 0);
        PageManager.addComponent(pageName, "main_background", main_background);

    }

    public static void main(String[] args) {

    }
}
