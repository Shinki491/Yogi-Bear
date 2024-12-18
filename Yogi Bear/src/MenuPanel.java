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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MenuPanel extends JPanel {
    private HighScoreManager highScoreManager;
    private JButton playButton;

    public MenuPanel(ActionListener playAction) {
        highScoreManager = new HighScoreManager();
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Yogi Bear Game", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        add(title, BorderLayout.NORTH);

        // High Score Table
        JTextArea highScoreArea = new JTextArea();
        highScoreArea.setEditable(false);
        displayHighScores(highScoreArea);
        add(new JScrollPane(highScoreArea), BorderLayout.CENTER);

        // Play Button
        playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.BOLD, 24));
        playButton.addActionListener(playAction);
        add(playButton, BorderLayout.SOUTH);
    }

    private void displayHighScores(JTextArea highScoreArea) {
        List<HighScoreManager.HighScore> scores = highScoreManager.getHighScores();
        StringBuilder sb = new StringBuilder("High Scores:\n");
        int rank = 1;
        for (HighScoreManager.HighScore score : scores) {
            sb.append(rank++).append(". ").append(score.getPlayerName())
              .append(" - ").append(score.getScore()).append("\n");
        }
        highScoreArea.setText(sb.toString());
    }
}
