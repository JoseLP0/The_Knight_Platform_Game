package main;

import entities.Player;
import levels.LevelManager;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow window;
    private GamePanel gamePanel;
    private Thread thread;
    private final int FPS_SET = 120;
    private final int UPD_SET = 200;
    private Player player;
    private LevelManager levelManager;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE  = 1.5F;
    public final static int TILES_WIDTH = 26;
    public final static int TILES_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH = TILES_SIZE*TILES_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE*TILES_HEIGHT;

    public Game() {
        initializeClasses();



        gamePanel = new GamePanel(this);
        window = new GameWindow(gamePanel);
        gamePanel.requestFocus();



        startGameLoop();
    }

    private void initializeClasses() {

        player = new Player(200, 200);
        levelManager = new LevelManager(this);
    }

    private void startGameLoop() {
        thread = new Thread(this);
        thread.start();
    }

    public void update() {
        player.update();
        levelManager.update();
    }

    public void render(Graphics g){
        levelManager.draw(g);

        player.render(g);
    }


    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPD_SET;
//        long lastFrame = System.nanoTime();

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
           long currentTime = System.nanoTime();

           deltaU += (currentTime-previousTime) / timePerUpdate;
           deltaF += (currentTime-previousTime) / timePerFrame;
           previousTime = currentTime;

           if (deltaU >= 1) {
               update();
               updates++;
               deltaU--;
           }

           if (deltaF >= 1) {
               gamePanel.repaint();
               deltaF--;
               frames++;
           }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }

    public Player getPlayer() {
        return player;
    }


}
