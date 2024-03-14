package com.driver;

import java.util.Scanner;

public class UNOTrackerController {
    private UNOTrackerService unoTrackerService;

    public UNOTrackerController(UNOTrackerService unoTrackerService) {
        this.unoTrackerService = unoTrackerService;
    }

    public void processUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    storeScore(scanner);
                    break;
                case 2:
                    calculateAverageScore(scanner);
                    break;
                case 3:
                    identifyTopPlayer();
                    break;
                case 4:
                    System.out.println("Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void printMenu() {
    	//your code goes here
        System.out.println("\nUNO Tracker Menu:");
        System.out.println("1. Store Score");
        System.out.println("2. Calculate Average Score");
        System.out.println("3. Identify Top Player");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private void storeScore(Scanner scanner) {
    	//your code goes here
        System.out.print("Enter player's name: ");
        String playerName = scanner.next();
        System.out.print("Enter player's score: ");
        int score = scanner.nextInt();
        ScoreDTO scoreDTO = new ScoreDTO(playerName, score);
        unoTrackerService.storeScoreData(scoreDTO);
    }

    private void calculateAverageScore(Scanner scanner) {
    	//your code goes here
        System.out.print("Enter player's name: ");
        String playerName = scanner.next();
        double averageScore = unoTrackerService.calculateAverageScore(playerName);
        System.out.println(playerName + "'s average score: " + averageScore);
    }

    private void identifyTopPlayer() {
    	//your code goes here
        String topPlayer = unoTrackerService.identifyTopPlayer();
        System.out.println("Top performing player: " + topPlayer);
    }

    public static void main(String[] args) {
        // Create necessary objects and start the application
        ScoreDataRepository repository = new ScoreDataRepository();
        UNOTrackerService unoTrackerService = new UNOTrackerService(repository);
        UNOTrackerController unoTrackerController = new UNOTrackerController(unoTrackerService);

        // Start processing user input
        unoTrackerController.processUserInput();
    }
}
