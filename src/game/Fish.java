package src.game;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.text.Position;

class EatInfo {
    public Boolean isEatRange;
    public double distance;
}

public class Fish {

    protected Point2D position = null;
    protected BufferedImage fishImage = null;
    protected Boolean isLeftTowards = true;

    public Fish(int x, int y, BufferedImage fishImage) {
        this.position = new Point2D.Float(x, y);
    }

    public void setFishImage(BufferedImage fishImage) {
        this.fishImage = fishImage;
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

    public void draw(Graphics g) {
        g.drawImage(fishImage,
                (int) position.getX(), (int) position.getY(),
                fishImage.getWidth(), fishImage.getHeight(),
                null);
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