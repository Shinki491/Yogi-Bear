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

public class Ranger extends LevelObject {
    private int speed; // Speed of movement
    private boolean horizontal; // Whether the ranger moves horizontally or vertically
    private int direction; // 1 for positive, -1 for negative

    public Ranger(int x, int y, int width, int height, int speed, boolean horizontal) {
        super(x, y, width, height);
        this.speed = speed;
        this.horizontal = horizontal;
        this.direction = 1; // Default direction
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED); // Example color for rangers
        g.fillRect(x, y, width, height);
    }

    public void move(List<Obstacle> obstacles) {
        int dx = horizontal ? speed * direction : 0;
        int dy = horizontal ? 0 : speed * direction;

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

        // Check horizontal movement
        if (canMoveX) {
            x = newX;
        } else {
            changeDirection(); // Change direction if collision occurs
        }

        // Check vertical movement
        newBounds = new Rectangle(x, newY, width, height);
        boolean canMoveY = true;
        for (Obstacle obstacle : obstacles) {
            if (newBounds.intersects(new Rectangle(obstacle.getX(), obstacle.getY(), obstacle.getWidth(), obstacle.getHeight()))) {
                canMoveY = false;
                break;
            }
        }

        if (canMoveY) {
            y = newY;
        } else {
            changeDirection(); // Change direction if collision occurs
        }
    }



    public void changeDirection() {
        direction *= -1;
    }

    // Getters and setters
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
}
