/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Amir
 */
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class LevelObject {
    protected int x, y; // Position of the object
    protected int width, height; // Size of the object

    public LevelObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Abstract method for rendering the object
    public abstract void draw(Graphics g);

    // Check if this object intersects with another
    public boolean checkCollision(LevelObject other) {
        Rectangle thisBounds = new Rectangle(x, y, width, height);
        Rectangle otherBounds = new Rectangle(other.x, other.y, other.width, other.height);
        return thisBounds.intersects(otherBounds);
    }

    // Getters and setters for position
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // Getters and setters for size
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
