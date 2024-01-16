package src.framework;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

public class DataStorager extends DataManager {

    public final static String APP_NAME = "FeedingFrenzy";
    public final static int APP_HEIGHT = 640;
    public final static int APP_WIDTH = 1180;

    public DataStorager() {
        super();
        // APP ICON
        addImageResource("APP_ICON", new ImageData("./resource/icon.png"));

        // 游戏背景0
        addImageResource("MAIN_BG0", new ImageData("./resource/bg0.jpg"));
        // 游戏背景1
        addImageResource("MAIN_BG1", new ImageData("./resource/bg1.jpg"));
        // 游戏背景2
        addImageResource("MAIN_BG2", new ImageData("./resource/bg2.jpg"));

        // Main Title
        addImageResource("MAIN_TITLE", new ImageData("./resource/mainTitle.png"));

        // 开始游戏按钮
        addImageResource("GAME_START_BTN", new ImageData("./resource/main_go0.png"));
        addImageResource("GAME_START_BTN_PRESS", new ImageData("./resource/main_go1.png"));

        // 加载中文字
        addImageResource("LOADING_TEXT", new ImageData("./resource/loading.png"));
        // 加载中背景
        addImageResource("LOADING_TEXT", new ImageData("./resource/loading1.png"));

        // 我的成就按钮
        addImageResource("ACHIVEMENT_BTN", new ImageData("./resource/main_cj.png"));
        addImageResource("ACHIVEMENT_BTN_PRESS", new ImageData("./resource/main_cj2.png"));
        // 我的成就栏背景
        addImageResource("ACHIVEMENT_BG", new ImageData("./resource/bg_chievement.png"));

        // 制作团队按钮
        addImageResource("HELP_TEAM_BTN", new ImageData("./resource/helpTeam0.png"));
        addImageResource("HELP_TEAM_BTN_PRESS", new ImageData("./resource/helpTeam1.png"));

        // 得分
        addImageResource("SCORE_TEXT", new ImageData("./resource/tz_1.png"));
        // 总得分
        addImageResource("TOTAL_SCORE_TEXT", new ImageData("./resource/sls_title.png"));
        // 新纪录
        addImageResource("NEW_RECORD_ICON", new ImageData("./resource/tuzhang.png"));

        // 确定
        addImageResource("CONFIRM_BTN", new ImageData("./resource/queding0.png"));
        addImageResource("CONFIRM_PRESS_BTN", new ImageData("./resource/queding1.png"));
        // 取消
        addImageResource("CANCEL_BTN", new ImageData("./resource/quxiao0.png"));
        addImageResource("CANCEL_PRESS_BTN", new ImageData("./resource/quxiao1.png"));
        // 返回
        addImageResource("RETURN_BTN", new ImageData("./resource/return0.png"));
        addImageResource("RETURN_PRESS_BTN", new ImageData("./resource/return1.png"));

        // 鱼标0
        ImageData fish_self_0 = new ImageData("./resource/fishL0.png", false);
        fish_self_0.addCroppingData("FISH_EAT0", 107, 103, 152, 112);
        addImageResource("FISH_SELF0", fish_self_0);

        // 数字0-9
        ImageData number_red = new ImageData("./resource/xsdl5.png", false);// 红色
        for (Integer i = 0; i <= 9; i++) {
            number_red.addCroppingData("NUMBER_RED" + i.toString(), i * 34, 0, 34, 42);
        }
        addImageResource("NUMBER_RED", number_red);

        // 成就背景
        addImageResource("ACHIEVEMENT_BG", new ImageData("./Resource/bg_chievement.png"));

        // 成就图标
        for (Integer i = 1; i <= 24; i++) {
            addImageResource("ACHIEVEMENTS_GET" + i.toString(),
                    new ImageData("./Resource/chengjiu_" + i.toString() + ".png"));
            addImageResource("ACHIEVEMENTS_NOTGET" + i.toString(),
                    new ImageData("./Resource/chengjiu_" + i.toString() + "_" + i.toString() + ".png"));
            addImageResource("ACHIEVEMENTS_NAME" + i.toString(),
                    new ImageData("./Resource/chengjiu_" + i.toString() + "name.png"));
        }
        addImageResource("MAIN_BG1_1", new ImageData("./Resource/bg1_0.png"));
        addImageResource("MAIN_BG1_2", new ImageData("./Resource/bg1_1.png"));
        addImageResource("MAIN_BG1_3", new ImageData("./Resource/bg1_2.png"));
    }

    public static BufferedImage getImage(String resourceName, String subImageName) throws IOException {
        ImageData imageData = getImageResource(resourceName);
        if (imageData == null)
            return null;
        BufferedImage bufferedImage = imageData.getImage(subImageName);
        return bufferedImage;
    }

    public static BufferedImage getImage(String resourceName) throws IOException {
        ImageData imageData = getImageResource(resourceName);
        if (imageData == null)
            return null;
        BufferedImage bufferedImage = imageData.getImage();
        return bufferedImage;
    }

    public static void main(String[] args) throws IOException {
        // test
        DataStorager ds = new DataStorager();
        System.out.println(getImage("APP_ICON"));
        // sub image
        BufferedImage bi = getImage("ACHIEVEMENTS_GET1");
        ImageIO.write(bi, "png", new File("./test.png"));
        // BufferedImage bi = getImage("FISH_SELF0", "FISH_EAT0");
        // ImageIO.write(bi, "png", new File("./test1.png"));
    }
}

// dm.addImageResource("BIG_BOX",
// new ImageData("./resource/Texture/box_02.BIG.png"));

// ImageData im = new ImageData("INDEX", false);
// im.addCroppingData("BACKGROUND", 453, 702, 571, 322);

// BufferedImage bm = dm.getImageResource("BIG_BOX").getImage();
// ImageIO.write(bm, "png", new File("./test.png"));

//

// public final static String APP_NAME = "天天酷跑";
// public final static String APP_ICON_PATH =
// "./resource/Texture/Icon/oldIcon114.png";
// public final static String UNCROPPER_INDEX =
// "./resource/Texture/Icon/oldIcon114.png";