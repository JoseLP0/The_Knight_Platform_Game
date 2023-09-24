package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import util.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;

import static util.Constants.UI.PauseButtons.*;
import static util.Constants.UI.URMButtons.*;
import static util.Constants.UI.VolumeButton.*;


public class PauseOverlay {

    private Playing playing;
    private BufferedImage backgroundImg;
    private int backgroundX, backgroundY, backgroundWidth, backgroundHeight;
    private SoundButton musicButton, sfxButton;
    private  UrmButton menuButton, replayButton,unpauseButton;
    private VolumeButton volumeButton;

    public PauseOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        createSoundButtons();
        createUrmButtons();
        createVolumeButton();
    }

    private void createVolumeButton() {
        int volumeX = (int)(392 * Game.SCALE);
        int volumeY = (int)(333 * Game.SCALE);
        volumeButton = new VolumeButton(volumeX, volumeY, SLIDER_WIDTH, VOLUME_HEIGHT);
    }

    private void createUrmButtons() {
        int menuX = (int)(403 * Game.SCALE);
        int replayX = (int)(467 * Game.SCALE);
        int unpauseX = (int)(532 * Game.SCALE);
        int buttonY = (int)(382 * Game.SCALE);

        menuButton = new UrmButton(menuX, buttonY, URM_SIZE, URM_SIZE,2);
        replayButton = new UrmButton(replayX, buttonY, URM_SIZE, URM_SIZE,1);
        unpauseButton = new UrmButton(unpauseX, buttonY, URM_SIZE, URM_SIZE,0);
    }

    private void createSoundButtons() {
        int soundX = (int)(510 * Game.SCALE);
        int musicY = (int)(185 * Game.SCALE);
        int sfxY = (int)(231 * Game.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.getSpriteAtlas(LoadSave.PAUSED_BACKGROUND);
        backgroundWidth = (int)(backgroundImg.getWidth() * Game.SCALE);
        backgroundHeight = (int)(backgroundImg.getHeight() * Game.SCALE);
        backgroundX = Game.GAME_WIDTH / 2 - backgroundWidth / 2;
        backgroundY = Game.GAME_HEIGHT / 2 - backgroundHeight / 2;
    }

    public void update() {
        //SOUND BUTTONS
        musicButton.update();
        sfxButton.update();

        //URM BUTTON
        menuButton.update();
        replayButton.update();
        unpauseButton.update();

        //VOLUME BUTTONS
        volumeButton.update();
    }


    public void draw(Graphics g) {
        //BACKGROUND
        g.drawImage(backgroundImg, backgroundX, backgroundY, backgroundWidth, backgroundHeight, null);

        //SOUND BUTTONS
        musicButton.draw(g);
        sfxButton.draw(g);

        //URM BUTTONS
        menuButton.draw(g);
        replayButton.draw(g);
        unpauseButton.draw(g);

        // VOLUME BUTTONS
        volumeButton.draw(g);
    }

    public void mouseDragged(MouseEvent e) {
        if (volumeButton.isMousePressed()) {
            volumeButton.changeX(e.getX());
        }
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(e,musicButton)) {
            musicButton.setMousePressed(true);
        } else if (isIn(e,sfxButton)) {
            sfxButton.setMousePressed(true);
        } else if (isIn(e,menuButton)) {
            menuButton.setMousePressed(true);
        } else if (isIn(e,replayButton)) {
            replayButton.setMousePressed(true);
        } else if (isIn(e,unpauseButton)) {
            unpauseButton.setMousePressed(true);
        } else if (isIn(e,volumeButton)) {
            volumeButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(e,musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.setMuted(!musicButton.isMuted());
            }
        } else if (isIn(e,sfxButton)) {
            if (sfxButton.isMousePressed()) {
                sfxButton.setMuted(!sfxButton.isMuted());
            }
        } else if (isIn(e,menuButton)) {
            if (menuButton.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
                playing.unpauseGame();
            }
        } else if (isIn(e,replayButton)) {
            if (replayButton.isMousePressed()) {
                System.out.println("replay level!");
            }
        } else if (isIn(e,unpauseButton)) {
            if (unpauseButton.isMousePressed()) {
                playing.unpauseGame();
            }
        }

        musicButton.restBooleans();
        sfxButton.restBooleans();
        menuButton.resetBooleans();
        replayButton.resetBooleans();
        unpauseButton.resetBooleans();
        volumeButton.resetBooleans();
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuButton.setMouseOver(false);
        replayButton.setMouseOver(false);
        unpauseButton.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isIn(e,musicButton)) {
            musicButton.setMouseOver(true);
        } else if (isIn(e,sfxButton)) {
            sfxButton.setMouseOver(true);
        } else if (isIn(e,menuButton)) {
            menuButton.setMouseOver(true);
        } else if (isIn(e,replayButton)) {
            replayButton.setMouseOver(true);
        } else if (isIn(e,unpauseButton)) {
            unpauseButton.setMouseOver(true);
        } else if (isIn(e,volumeButton)) {
            volumeButton.setMouseOver(true);
        }
    }

    private boolean isIn(MouseEvent e, PausedButton b) {
        return b.getBounds().contains(e.getX(), e.getY());
    }

}
