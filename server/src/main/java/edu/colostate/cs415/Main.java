package edu.colostate.cs415;

import java.util.Scanner;

import edu.colostate.cs415.db.DBConnector;
import edu.colostate.cs415.server.RestController;

public class Main {

    private static final int PORT = 4567;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestController restController = new RestController(PORT, new DBConnector());
        try {
            restController.start();
            System.out.println("Server running on port: " + PORT + "\nPress ENTER to kill server...");
            scanner.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            restController.stop();
            scanner.close();
            System.out.println("Shutting down...");
        }
    }
}
