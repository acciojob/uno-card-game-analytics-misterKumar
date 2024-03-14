package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreDataRepository {
    private final Map<String, List<Score>> playerScores;

    public ScoreDataRepository() {
        this.playerScores = new HashMap<>();
    }

    public void storeScore(Score score) {
    	//your code goes here
        List<Score> scores = playerScores.getOrDefault(score.getPlayerName(), new ArrayList<>());
        scores.add(score);
        playerScores.put(score.getPlayerName(), scores);
    }

    public List<Score> getScoresByPlayer(String playerName) {
    	//your code goes here
        return playerScores.getOrDefault(playerName, new ArrayList<>());
    }

    public List<Score> getAllScores() {
    	//your code goes here
        List<Score> allScores = new ArrayList<>();
        playerScores.values().forEach(allScores::addAll);
        return allScores;
    }
    // Method to access playerScores
    public Map<String, List<Score>> getPlayerScores() {
        return playerScores;
    }

}

