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

public class GameOverPanel extends JPanel {
    private int score;
    private JLabel scoreLabel;
    private GameApp gameApp;
    private HighScoreManager highScoreManager;
    
    public GameOverPanel(GameApp gameApp, HighScoreManager highScoreManager) {
        this.gameApp = gameApp;
        this.highScoreManager = highScoreManager;
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Game Over", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        add(title, BorderLayout.NORTH);

        scoreLabel = new JLabel("Score: ", SwingConstants.CENTER); // Initialize scoreLabel
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24)); // Style it
        add(scoreLabel, BorderLayout.CENTER);   
        
        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 1)); // Adjusted to 3 rows for extra label
        inputPanel.add(scoreLabel); // Add the score display

        JLabel nameLabel = new JLabel("Enter your name:", SwingConstants.CENTER); // Adjusted
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        inputPanel.add(nameLabel); // Add name labelww

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 24));
        inputPanel.add(nameField); // Add the input field

        add(inputPanel, BorderLayout.CENTER);

        // Save Button
        JButton saveButton = new JButton("Save & Return to Menu");
        saveButton.setFont(new Font("Arial", Font.BOLD, 24));
        saveButton.addActionListener(e -> {
            String playerName = nameField.getText().trim();
            if (!playerName.isEmpty() && score > 0) {
                highScoreManager.addHighScore(playerName, score); // Use shared instance
            }
            gameApp.showMenuPanel();
        });
        add(saveButton, BorderLayout.SOUTH);
    }
    
    public void setScore(int score) {
        this.score = score; // Store the score
        scoreLabel.setText("Your Score: " + score);
    }
}
