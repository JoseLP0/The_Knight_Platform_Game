package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow window;
    private GamePanel gamePanel;
    private Thread thread;
    private final int FPS_SET = 120;
    private final int UPD_SET = 200;

    private Player player;

    public Game() {
        initializeClasses();



        gamePanel = new GamePanel(this);
        window = new GameWindow(gamePanel);
        gamePanel.requestFocus();



        startGameLoop();
    }

    private void initializeClasses() {
        player = new Player(200, 200);
    }

    private void startGameLoop() {
        thread = new Thread(this);
        thread.start();
    }

    public void update() {
        player.update();
    }

    public void render(Graphics g){
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
