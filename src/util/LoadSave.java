package util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "/player/the-final-knight.png";
    public static final String LEVEL_ATLAS = "/levels/tiles.png";
    public static final String LEVEL_ONE_DATA = "/levels/level_one_data_long.png";
    public static final String PLAYING_BG_IMG = "/levels/layer_1.png";
    public static final String CITY_IMG = "/levels/layer_2.png";
    public static final String FOG_IMG = "/levels/layer_3.png";
    public static final String MENU_BUTTONS = "/ui/menu-buttons.png";
    public static final String MENU_BACKGROUND = "/ui/menu.png";
    public static final String MENU_BACKGROUND_IMG = "/ui/Background.png";
    public static final String PAUSED_BACKGROUND = "/ui/paused.png";
    public static final String SOUND_BUTTONS = "/ui/sound-buttons.png";
    public static final String URM_BUTTONS = "/ui/urm-buttons.png";
    public static final String VOLUME_BUTTONS = "/ui/volume_buttons.png";
    public static final String SKELETON_SPRITE = "/player/skeleton.png";


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
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);
        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 52) {
                    value = 0;
                    lvlData[j][i] = value;

                }
                lvlData[j][i] = value;
            }
        }
        return lvlData;
    }

}


















