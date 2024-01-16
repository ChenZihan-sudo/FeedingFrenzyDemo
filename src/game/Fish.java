package src.game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.text.Position;

import com.mysql.cj.util.Util;

import src.FeedingFrenzy;
import src.framework.PageManager;
import src.framework.SwingFramer;
import src.utils.Utils;

class EatInfo {
    public Boolean isEatRange;
    public double distance;
}

public class Fish {

    protected static final int upperSiderBarHeight = FeedingFrenzy.upperSiderBarHeight;

    protected Point2D position = null;

    protected BufferedImage fishImage = null;
    protected BufferedImage leftTowardImage = null;
    protected BufferedImage rightTowardImage = null;

    protected Boolean isLeftTowards = true;

    protected SwingFramer frame;
    protected JLabel fish = null;
    protected Graphics graphic = null;

    protected String fishName = null;

    protected float fishScale = 0.5f;

    public Fish(String fishName, BufferedImage fishImage) {
        frame = FeedingFrenzy.getFrame();
        this.fishImage = fishImage;

        leftTowardImage = fishImage;
        rightTowardImage = Utils.revertImage(fishImage);

        this.fishImage = rightTowardImage;

        fish = frame.setLabelPaint(new LabelPaintCallback() {
            @Override
            public void call(Graphics g) throws IOException {
                graphic = g;
                draw();
            }
        });
        PageManager.addComponent(10, "GameMain", fishName, fish);
    }

    public void draw() {
        graphic.drawImage(fishImage, 0, 0, getFishWidth(), getFishHeight(), null);
    }

    public void setFishPosition(Point p) {
        fish.setBounds((int) p.getX(), (int) p.getY() - upperSiderBarHeight, getFishWidth(), getFishHeight());
        fish.repaint();
    }

    public Integer getFishHeight() {
        return (int) (fishImage.getHeight() * fishScale);
    }

    public Integer getFishWidth() {
        return (int) (fishImage.getWidth() * fishScale);
    }

    public void setPosition(Integer x, Integer y) {
        if (x == null) {
            position.setLocation(position.getX(), y);
            return;
        }

        if (y == null) {
            position.setLocation(x, position.getY());
            return;
        }

        position.setLocation(x, y);
    }

    public Rectangle getRectangle() {
        return new Rectangle(
                (int) position.getX(), (int) position.getY(),
                fishImage.getWidth(), fishImage.getHeight());
    }

    /**
     * Calculate the fish mouth toward postion
     */
    public Point2D calculateMouthTowardsPostion(Fish fish, Boolean useLeftSide) {

        double X = fish.position.getX();
        double Y = fish.position.getY() + ((double) fish.fishImage.getHeight()) / 2.f;

        if (!useLeftSide) {
            X += (double) fish.fishImage.getWidth();
        }

        return new Point2D.Double(X, Y);
    }

    /**
     * Calculate the eaten distance between two fish
     */
    public EatInfo calculateEatDistance(Fish anotherFish) {

        EatInfo eatInfo = new EatInfo();
        eatInfo.isEatRange = false;
        Point2D point = calculateMouthTowardsPostion(this, isLeftTowards);

        if (isLeftTowards) {
            double aFishXRight = anotherFish.position.getX() + anotherFish.fishImage.getWidth();
            if (point.getX() - aFishXRight <= 0.f) {
                return eatInfo;
            }
            eatInfo.distance = point.distanceSq(new Point2D.Double(aFishXRight,
                    anotherFish.position.getY() + ((double) anotherFish.fishImage.getHeight()) / 2.f));
        } else {
            double aFishXLeft = anotherFish.position.getX();
            if (aFishXLeft - point.getX() <= 0.f) {
                return eatInfo;
            }
            eatInfo.distance = point.distanceSq(new Point2D.Double(aFishXLeft,
                    anotherFish.position.getY() + ((double) anotherFish.fishImage.getHeight()) / 2.f));
        }
        eatInfo.isEatRange = true;
        return eatInfo;
    }
}