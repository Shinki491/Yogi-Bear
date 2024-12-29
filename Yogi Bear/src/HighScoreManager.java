/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Amir
 */
import java.io.*;
import java.util.*;

public class HighScoreManager {
    private static final String HIGH_SCORE_FILE = "highscores.dat"; // File to save high scores
    private List<HighScore> highScores;
    private static final int MAX_SCORES = 10; // Limit to top 10 scores

    public HighScoreManager() {
        highScores = new ArrayList<>();
        loadHighScores();
    }       

    // Add a new high score
    public void addHighScore(String playerName, int score) {
        highScores.add(new HighScore(playerName, score));
        highScores.sort(Comparator.comparingInt(HighScore::getScore).reversed());
        if (highScores.size() > MAX_SCORES) {
            highScores = new ArrayList<>(highScores.subList(0, MAX_SCORES)); // Create a new ArrayList
        }
        saveHighScores();
    }

    // Load high scores from file
    private void loadHighScores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HIGH_SCORE_FILE))) {
            highScores = (List<HighScore>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("High score file not found. Creating a new one.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Save high scores to file
    private void saveHighScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HIGH_SCORE_FILE))) {
            oos.writeObject(highScores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Get the list of high scores
    public List<HighScore> getHighScores() {
        return highScores;
    }

    // Display high scores
    public void displayHighScores() {
        System.out.println("Top 10 High Scores:");
        for (int i = 0; i < highScores.size(); i++) {
            HighScore hs = highScores.get(i);
            System.out.printf("%d. %s - %d%n", i + 1, hs.getPlayerName(), hs.getScore());
        }
    }

    // Inner class to represent a high score entry
    public static class HighScore implements Serializable {
        private static final long serialVersionUID = 1L;
        private final String playerName;
        private final int score;

        public HighScore(String playerName, int score) {
            this.playerName = playerName;
            this.score = score;
        }

        public String getPlayerName() {
            return playerName;
        }

        public int getScore() {
            return score;
        }
    }
}