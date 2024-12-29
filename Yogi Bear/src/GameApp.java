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

public class GameApp {
    private JFrame frame; // Main game window
    private CardLayout cardLayout; // Layout to switch between panels
    private JPanel mainPanel; // Main container panel
    private MenuPanel menuPanel; // Store a reference to MenuPanel

    public GameApp() {
        frame = new JFrame("Yogi Bear Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Set up CardLayout for switching between MenuPanel and GamePanel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create MenuPanel with an action to start the game
        menuPanel = new MenuPanel(this);
        HighScoreManager highScoreManager = menuPanel.getHighScoreManager();

        // Add panels to the CardLayout
        mainPanel.add(menuPanel, "Menu");
        mainPanel.add(new GamePanel(this), "Game");
        mainPanel.add(new GameOverPanel(this, highScoreManager), "GameOver");

        frame.add(mainPanel);
        frame.pack();
        frame.setSize(892, 912); // Example size
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setVisible(true);
        
        // Show the MenuPanel initially
        showMenuPanel();
    }

    // Method to display the MenuPanel
    public void showMenuPanel() {
        menuPanel.refreshLeaderboard(); // Refresh leaderboard
        cardLayout.show(mainPanel, "Menu");
    }

    // Method to display the GamePanel
    public void showGamePanel() {
        GamePanel gamePanel = (GamePanel) mainPanel.getComponent(1); // Get the GamePanel
        gamePanel.resetGame(); // Ensure resetGame exists in GamePanel
        cardLayout.show(mainPanel, "Game");
        gamePanel.requestFocusInWindow(); // Ensure focus is on the GamePanel
    }

    // Method to display the GameOverPanel
    public void showGameOverPanel(int score) {
        GameOverPanel gameOverPanel = (GameOverPanel) mainPanel.getComponent(2); // Get GameOverPanel
        gameOverPanel.setScore(score); // Pass the score to the panel
        cardLayout.show(mainPanel, "GameOver");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameApp::new);
    }
}
