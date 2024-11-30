package tictactoe;
import java.sql.*;
import java.util.Scanner;
import databaseConnection.DBconnection;
import Main.Structure;

public class GameCode {
    private static final Scanner scanner = new Scanner(System.in);
    private static Structure structure = new Structure();

    public static void main(String[] args) {
        System.out.println("Welcome to Tic Tac Toe!");
        while (true) {
            System.out.println("1. Register\n2. Login\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                registerUser();
            } else if (choice == 2) {
                loginUser();
            } else if (choice == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String code = scanner.nextLine();
        System.out.print("Enter birth date (YYYY-MM-DD): ");
        String birthDate = scanner.nextLine();
        try {
            structure.registerUser(name, code, birthDate);
            System.out.println("Registration successful!");
        } catch (Exception e) {
            System.out.println("Failed registration: " + e.getMessage());
        }
    }

    private static void loginUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String code = scanner.nextLine();

        try {
            if (structure.login(name, code)) {
                System.out.println("Login successful! Choose your symbol (X or O): ");
                String symbol = scanner.nextLine().toUpperCase();

                // Query the user ID for the logged-in user
                String query = "SELECT user_id FROM users WHERE name = ? AND password = ?";
                try (Connection conn = DBconnection.getConnection();
                     PreparedStatement ps = conn.prepareStatement(query)) {
                    ps.setString(1, name);
                    ps.setString(2, code);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        int userId = rs.getInt("user_id");
                        if (symbol.equals("X") || symbol.equals("O")) {
                            System.out.println("Starting game as " + symbol + "...");
                            structure.playGame(symbol, userId); // Pass symbol and user_id to playGame
                        } else {
                            System.out.println("Invalid symbol choice. Please select X or O.");
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Error retrieving user ID: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid login credentials.");
            }
        } catch (Exception e) {
            System.out.println("Login Failed: " + e.getMessage());
        }
    }
}
