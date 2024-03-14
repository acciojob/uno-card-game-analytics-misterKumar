package com.driver;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UNOTrackerService {
    private ScoreDataRepository scoreDataRepository;

    public UNOTrackerService(ScoreDataRepository scoreDataRepository) {
        this.scoreDataRepository = scoreDataRepository;
    }

    public void storeScoreData(ScoreDTO scoreDTO) {
    	//your code goes here
        ScoreDataConverter converter = new ScoreDataConverter();
        Score score = converter.convertToEntity(scoreDTO);
        scoreDataRepository.storeScore(score);
        System.out.println("Stored data for " + score.getPlayerName() + ".");
    }

    public double calculateAverageScore(String playerName) {
    	//your code goes here
        List<Score> scores = scoreDataRepository.getScoresByPlayer(playerName);
        if (scores.isEmpty()) return 0.0;

        int totalScore = scores.stream().mapToInt(Score::getScore).sum();
        return (double) totalScore / scores.size();
    }

    public String identifyTopPlayer() {
    	//your code goes here
        Map<String, List<Score>> playerScores = scoreDataRepository.getPlayerScores();
        if (playerScores.isEmpty()) return "No scores recorded.";

        String topPlayer = "";
        double maxAverageScore = 0.0;
        for (String playerName : playerScores.keySet()) {
            double averageScore = calculateAverageScore(playerName);
            if (averageScore > maxAverageScore) {
                maxAverageScore = averageScore;
                topPlayer = playerName;
            }
        }
        return topPlayer;
    }
}
