package util;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "/player/final-knight.png";
    public static final String LEVEL_ATLAS = "/levels/final.png";
    public static final String LEVEL_ONE_DATA = "/levels/level_one_data.png";

    public static BufferedImage getSpriteAtlas(String filename) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream(filename);

        try {
            img = ImageIO.read(is);



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static int[][] getLevelData() {
        int[][] lvlData = new int[Game.TILES_HEIGHT][Game.TILES_WIDTH];
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 48) {
                    value = 0;
                    lvlData[j][i] = value;

                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }

}


















