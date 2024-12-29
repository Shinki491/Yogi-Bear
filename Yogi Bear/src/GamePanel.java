/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Amir
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    private GameApp gameApp;
    private Timer timer;
    private Player player;
    private Level currentLevel;
    private int currentLevelIndex;
    private boolean gameOver;
    private float time;

    public GamePanel(GameApp gameApp) {
        this.gameApp = gameApp;
        setFocusable(true);
        setBackground(Color.GREEN); // Example background color
        addKeyListener(this);

        timer = new Timer(16, this); // Approx 60 FPS

        currentLevelIndex = 1;
        loadLevel(currentLevelIndex, 3, 0);
        timer.start();
        time = 0;
        
    }

    private void loadLevel(int index, int lives, int score) {
        String levelFile = "level" + index + ".txt"; // Adjust path as needed
        currentLevel = new Level(levelFile, lives, score);
        player = currentLevel.getPlayer();
        gameOver = false;
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!gameOver) {
            currentLevel.draw(g);

            // Timer display
            g.setColor(Color.BLACK);
            g.drawString("Time: " + (int)(time/1000) + "s", 20, 20);

            // Lives and score display
            g.drawString("Lives: " + player.getLives(), 20, 40);
            g.drawString("Score: " + player.getScore(), 20, 60);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            time += timer.getDelay();
            currentLevel.update();

            for (Ranger ranger : currentLevel.getRangers()) {
                ranger.move(currentLevel.getObstacles());
            }
            
            // Check collisions with picnic baskets
            List<PicnicBasket> baskets = currentLevel.getPicnicBaskets();
            for (PicnicBasket basket : baskets) {
                if (!basket.isCollected() && player.checkCollision(basket)) {
                    basket.collect();
                    player.collectBasket();
                }
            }

            // Check collisions with rangers
            List<Ranger> rangers = currentLevel.getRangers();
            for (Ranger ranger : rangers) {
                if (player.checkCollision(ranger)) {
                    player.loseLife();
                    if (player.getLives() <= 0) {
                        gameOver = true;
                        triggerGameOver();
                    } else {
                        // Reset player position
                        //loadLevel(currentLevelIndex, player.getLives(), player.getScore());
                        List<Obstacle> obstacles = currentLevel.getObstacles();
                        player.move(currentLevel.getLevelStartX() - player.getX(), currentLevel.getLevelStartY() - player.getY(), obstacles);
                    }
                    break;
                }
            }

            // Check level completion
            if (currentLevel.areAllBasketsCollected()) {
                currentLevelIndex++;
                if (currentLevelIndex > 10) {
                    currentLevelIndex = 1; // Restart from the first level
                }
                loadLevel(currentLevelIndex, player.getLives(), player.getScore());
            }
        }

        repaint();
    }
    
    private void triggerGameOver() {
        gameApp.showGameOverPanel(player.getScore()); // Pass the player's score
       
    }
    
    public void resetGame() {
        currentLevelIndex = 1; // Reset to the first level
        time = 0; // Reset timer
        loadLevel(currentLevelIndex, 3, 0); // Reset player lives and score
        gameOver = false; // Ensure the game is not marked as over
        requestFocusInWindow(); // Ensure the panel regains focus for input
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("Key pressed: " + e.getKeyCode());
        int key = e.getKeyCode();
        int speed = 5;

        if (!gameOver) {
            List<Obstacle> obstacles = currentLevel.getObstacles(); // Get obstacles from the current level

            if (key == KeyEvent.VK_W) {
                player.move(0, -speed, obstacles);
            } else if (key == KeyEvent.VK_S) {
                player.move(0, speed, obstacles);
            } else if (key == KeyEvent.VK_A) {
                player.move(-speed, 0, obstacles);
            } else if (key == KeyEvent.VK_D) {
                player.move(speed, 0, obstacles);
            }
        }
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
       
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
}