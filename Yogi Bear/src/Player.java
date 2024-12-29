/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Amir
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.Rectangle;

public class Player extends LevelObject {
    private int lives; // Number of lives the player has
    private int score; // Number of collected picnic baskets

    public Player(int x, int y, int width, int height, int lives, int score) {
        super(x, y, width, height);
        this.lives = lives;
        this.score = score;
    }

    public void draw(Graphics g) {
        int pixelSize = 4; // Size of each "pixel" to scale the drawing

        // Updated bear grid: Rounded lower rows and centered light part
        int[][] bearPixels = {
            {0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0},
            {0, 0, 2, 2, 1, 1, 1, 1, 2, 2, 0, 0},
            {0, 2, 1, 1, 1, 1, 1, 1, 1, 1, 2, 0},
            {2, 1, 1, 3, 3, 1, 1, 3, 3, 1, 1, 2},
            {2, 1, 1, 3, 3, 1, 1, 3, 3, 1, 1, 2},
            {2, 1, 1, 1, 1, 4, 4, 1, 1, 1, 1, 2},
            {0, 2, 1, 1, 4, 4, 4, 4, 1, 1, 2, 0},
            {0, 2, 1, 1, 4, 4, 4, 4, 1, 1, 2, 0},
            {0, 0, 2, 1, 1, 1, 1, 1, 1, 2, 0, 0},
            {0, 0, 0, 2, 1, 1, 1, 1, 2, 0, 0, 0},
            {0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 0}
        };

        // Loop through the grid and draw each pixel
        for (int row = 0; row < bearPixels.length; row++) {
            for (int col = 0; col < bearPixels[row].length; col++) {
                switch (bearPixels[row][col]) {
                    case 1: g.setColor(new Color(139, 69, 19)); break; // Brown for the bear's face
                    case 2: g.setColor(Color.BLACK); break;            // Black for the outline
                    case 3: g.setColor(Color.BLACK); break;           // Yellow for the ears
                    case 4: g.setColor(new Color(245, 222, 179)); break; // Cream for the face center
                    default: continue; // Skip transparent pixels
                }
                g.fillRect(x + col * pixelSize, y + row * pixelSize, pixelSize, pixelSize);
            }
        }
    }


    public void move(int dx, int dy, List<Obstacle> obstacles) {
        int newX = x + dx;
        int newY = y + dy;

        // Create a Rectangle for the new position to check for collisions
        Rectangle newBounds = new Rectangle(newX, y, width, height);
        boolean canMoveX = true;
        for (Obstacle obstacle : obstacles) {
            if (newBounds.intersects(new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight()))) {
                canMoveX = false;
                break;
            }
        }

        // If no collision detected, move along the x-axis
        if (canMoveX) {
            x = newX;
        }

        // Check for movement along the y-axis
        newBounds = new Rectangle(x, newY, width, height);
        boolean canMoveY = true;
        for (Obstacle obstacle : obstacles) {
            if (newBounds.intersects(new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight()))) {
                canMoveY = false;
                break;
            }
        }

        // If no collision detected, move along the y-axis
        if (canMoveY) {
            y = newY;
        }
    }


    public void loseLife() {
        if (lives > 0) {
            lives--;
        }
    }

    public void collectBasket() {
        score++;
    }

    // Getters and setters
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
