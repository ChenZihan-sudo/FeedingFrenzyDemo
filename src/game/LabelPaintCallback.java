package src.game;

import java.awt.Graphics;
import java.io.IOException;

public interface LabelPaintCallback {
    void call(Graphics g) throws IOException;
}
