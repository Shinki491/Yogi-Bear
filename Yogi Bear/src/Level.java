/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Amir
 */
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private Player player;
    private List<Obstacle> obstacles;
    private List<PicnicBasket> picnicBaskets;
    private List<Ranger> rangers;
    private int lives;
    private int score;
    private int levelStartX;
    private int levelStartY;
    
    public Level(String filePath, int lives, int score) {
        obstacles = new ArrayList<>();
        picnicBaskets = new ArrayList<>();
        rangers = new ArrayList<>();
        this.lives = lives;
        this.score = score;
        loadLevel(filePath);
    }

    // Load level data from a file
    private void loadLevel(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int y = 0; // Row index
            while ((line = br.readLine()) != null) {
                for (int x = 0; x < line.length(); x++) {
                    char ch = line.charAt(x);
                    int cellSize = 40; // Example size for grid cells
                    int posX = x * cellSize;
                    int posY = y * cellSize;

                    switch (ch) {
                        case 'P': // Player
                            player = new Player(posX, posY, cellSize, cellSize, lives, score);
                            levelStartX = posX;
                            levelStartY = posY;
                            break;
                        case '#': // Obstacle
                            obstacles.add(new Obstacle(posX, posY, cellSize, cellSize));
                            break;
                        case 'B': // Picnic Basket
                            picnicBaskets.add(new PicnicBasket(posX, posY, cellSize, cellSize));
                            break;
                        case 'R': // Ranger
                            rangers.add(new Ranger(posX, posY, cellSize, cellSize, 2, true)); // Default horizontal ranger
                            break;
                        // Add more symbols as needed
                    }
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update level state (e.g., moving rangers, checking collisions)
    public void update() {
        for (Ranger ranger : rangers) {
            ranger.move(obstacles);
        }
        
    }

    // Draw all objects in the level
    public void draw(Graphics g) {
        if (player != null) {
            player.draw(g);
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(g);
        }
        for (PicnicBasket basket : picnicBaskets) {
            basket.draw(g);
        }
        for (Ranger ranger : rangers) {
            ranger.draw(g);
        }
    }

    // Check if all baskets have been collected
    public boolean areAllBasketsCollected() {
        for (PicnicBasket basket : picnicBaskets) {
            if (!basket.isCollected()) {
                return false;
            }
        }
        return true;
    }

    // Getters
    public Player getPlayer() {
        return player;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }

    public List<PicnicBasket> getPicnicBaskets() {
        return picnicBaskets;
    }

    public List<Ranger> getRangers() {
        return rangers;
    }
    
    public int getLives() {
        return lives;
    }
    
    public int getLevelStartX() {
        return levelStartX;
    }
    
    public int getLevelStartY() {
        return levelStartY;
    }
}