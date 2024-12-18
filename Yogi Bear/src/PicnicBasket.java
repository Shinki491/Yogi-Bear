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

public class PicnicBasket extends LevelObject {
    private boolean collected; // Tracks if the basket has been collected

    public PicnicBasket(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.collected = false;
    }

    @Override
    public void draw(Graphics g) {
        if (!collected) {
            g.setColor(Color.YELLOW); // Example color for picnic baskets
            g.fillRect(x, y, width, height);
        }
    }

    public void collect() {
        this.collected = true;
    }

    public boolean isCollected() {
        return collected;
    }
}
