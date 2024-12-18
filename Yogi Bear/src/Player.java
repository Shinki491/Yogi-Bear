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

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE); // Example color for the player
        g.fillRect(x, y, width, height);
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
