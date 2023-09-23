package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static main.Game.SCALE;

public class Playing extends State implements Statemethods {
    private Player player;
    private LevelManager levelManager;

    public Playing(Game game) {
        super(game);
        initializeClasses();
    }

    private void initializeClasses() {
        levelManager = new LevelManager(game);
        player = new Player(200, 200, (int) (64*SCALE), (int) (64*SCALE));
        player.loadLvlDate(levelManager.getCurrentLvl().getLvlData());
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

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
            case KeyEvent.VK_BACK_SPACE :
                Gamestate.state = Gamestate.MENU;

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

    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }

    public Player getPlayer() {
        return player;
    }

}
