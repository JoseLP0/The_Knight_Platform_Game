package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;
import util.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.ref.PhantomReference;

import static main.Game.*;
import static util.Constants.Enviroment.*;

public class Playing extends State implements Statemethods {
    private Player player;
    private LevelManager levelManager;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;

    private int xLvlOffset;
    private int leftBorder = (int)(0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int)(0.8 * Game.GAME_WIDTH);
    private int lvlTilesWide = LoadSave.getLevelData()[0].length;
    private int maxTilesOffest = lvlTilesWide - Game.TILES_WIDTH;
    private int maxLvlOffsetX = maxTilesOffest * Game.TILES_SIZE;

    private BufferedImage backgroundImg;
    private BufferedImage cityImg, fogImg;

    public Playing(Game game) {
        super(game);
        initializeClasses();

        backgroundImg = LoadSave.getSpriteAtlas(LoadSave.PLAYING_BG_IMG);
        cityImg = LoadSave.getSpriteAtlas(LoadSave.CITY_IMG);
        fogImg = LoadSave.getSpriteAtlas(LoadSave.FOG_IMG);
    }

    private void initializeClasses() {
        levelManager = new LevelManager(game);
        player = new Player(200, 200, (int) (96*SCALE), (int) (96*SCALE));
        player.loadLvlDate(levelManager.getCurrentLvl().getLvlData());
        pauseOverlay = new PauseOverlay(this);
    }

    @Override
    public void update() {
        if (!paused) {
            levelManager.update();
            player.update();
            checkCloseToBorder();
        } else {
            pauseOverlay.update();
        }
    }

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitbox().x;
        int differance = playerX - xLvlOffset;

        if (differance > rightBorder) {
            xLvlOffset += differance - rightBorder;
        } else if (differance < leftBorder) {
            xLvlOffset += differance - leftBorder;
        }

        if (xLvlOffset > maxLvlOffsetX) {
            xLvlOffset = maxLvlOffsetX;
        } else if (xLvlOffset < 0) {
            xLvlOffset = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);

        darwCity(g);

        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);

        if (paused) {
            g.setColor(new Color(0,0,0,150));
            g.fillRect(0,0, GAME_WIDTH, GAME_HEIGHT);
            pauseOverlay.draw(g);
        }
    }

    private void darwCity(Graphics g) {

        for (int i = 0; i < 4; i++) {
            g.drawImage(cityImg, 0 + i * CITY_IMG_WIDTH - (int)(xLvlOffset * 0.3), (int) (167 * SCALE), CITY_IMG_WIDTH, CITY_IMG_HEIGHT, null);
        }

        for (int i = 0; i < 4; i++) {
            g.drawImage(fogImg, 0 + i * FOG_IMG_WIDTH - (int)(xLvlOffset * 0.7), (int) (330 * SCALE), FOG_IMG_WIDTH, FOG_IMG_HEIGHT, null);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseMoved(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_P:
               player.setAttacking(true);
               break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }

    public void mouseDragged(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseDragged(e);
        }
    }

    public void unpauseGame() {
        paused = false;
    }

    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }

    public Player getPlayer() {
        return player;
    }

}
