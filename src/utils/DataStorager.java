package src.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DataStorager extends DataManager {

    public final static String APP_NAME = "FeedingFrenzy";
    public final static int APP_HEIGHT = 640;
    public final static int APP_WIDTH = 1180;

    public DataStorager() {
        super();
        // APP ICON
        addImageResource("APP_ICON", new ImageData("./Resource/icon.png"));

        // 主背景
        addImageResource("BG1", new ImageData("./Resource/bg0.jpg"));

        // Main Title
        addImageResource("MAIN_TITLE", new ImageData("./Resource/mainTitle.png"));

        // 开始游戏按钮
        addImageResource("GAME_START_BTN", new ImageData("./Resource/main_go0.png"));
        addImageResource("GAME_START_BTN_PRESS", new ImageData("./Resource/main_go1.png"));

        // 加载中文字
        addImageResource("LOADING_TEXT", new ImageData("./Resource/loading.png"));
        // 加载中背景
        addImageResource("LOADING_TEXT", new ImageData("./Resource/loading1.png"));

        // 我的成就按钮
        addImageResource("ACHIVEMENT_BTN", new ImageData("./Resource/main_cj.png"));
        addImageResource("ACHIVEMENT_BTN_PRESS", new ImageData("./Resource/main_cj2.png"));
        // 我的成就栏背景
        addImageResource("ACHIVEMENT_BG", new ImageData("./Resource/bg_chievement.png"));

        // 制作团队按钮
        addImageResource("HELP_TEAM_BTN", new ImageData("./Resource/helpTeam0.png"));
        addImageResource("HELP_TEAM_BTN_PRESS", new ImageData("./Resource/helpTeam1.png"));

        // 得分
        addImageResource("SCORE_TEXT", new ImageData("./Resource/tz_1.png"));
        // 新纪录
        addImageResource("SCORE_TEXT", new ImageData("./Resource/tuzhang.png"));

        // 鱼标0
        ImageData fish_self_0 = new ImageData("./Resource/fishL0.png", false);
        fish_self_0.addCroppingData("FISH_EAT0", 107, 103, 152, 112);
        addImageResource("FISH_SELF0", fish_self_0);
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
        // full image
        DataStorager ds = new DataStorager();
        System.out.println(getImage("APP_ICON"));
        // sub image
        BufferedImage bi = getImage("FISH_SELF0", "FISH_EAT0");
        ImageIO.write(bi, "png", new File("./test.png"));
    }
}

// dm.addImageResource("BIG_BOX",
// new ImageData("./Resource/Texture/box_02.BIG.png"));

// ImageData im = new ImageData("INDEX", false);
// im.addCroppingData("BACKGROUND", 453, 702, 571, 322);

// BufferedImage bm = dm.getImageResource("BIG_BOX").getImage();
// ImageIO.write(bm, "png", new File("./test.png"));

//

// public final static String APP_NAME = "天天酷跑";
// public final static String APP_ICON_PATH =
// "./Resource/Texture/Icon/oldIcon114.png";
// public final static String UNCROPPER_INDEX =
// "./Resource/Texture/Icon/oldIcon114.png";