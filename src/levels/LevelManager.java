package levels;

import main.Game;
import util.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.TILES_SIZE;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;
    public LevelManager(Game game) {
        this.game = game;
        importLevelSprites();
        levelOne = new Level(LoadSave.getLevelData());
    }

    public void importLevelSprites() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[52];
        for (int j =0; j < 4; j++) {
            for (int i = 0; i < 13; i++) {
                int index = j*13 + i;
                levelSprite[index] = img.getSubimage(i*32, j*32, 32, 32);
            }
        }
    }


    public void draw(Graphics g, int lvlOffset) {
        for (int j= 0; j < Game.TILES_HEIGHT; j++) {
            for (int i = 0; i < levelOne.getLvlData()[0].length; i++) {
                int index = levelOne.getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], TILES_SIZE * i - lvlOffset, TILES_SIZE * j, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getCurrentLvl() {
        return levelOne;
    }
}
