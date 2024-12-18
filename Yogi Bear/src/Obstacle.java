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

public class Obstacle extends LevelObject {

    public Obstacle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GRAY); // Example color for obstacles
        g.fillRect(x, y, width, height);
    }
}
