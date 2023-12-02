import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PasswordManager {
    private static final Map<String, String> passwordVault = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nPassword Manager Menu:");
            System.out.println("1. Store Password");
            System.out.println("2. Retrieve Password");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    storePassword();
                    break;
                case 2:
                    retrievePassword();
                    break;
                case 3:
                    System.out.println("Exiting Password Manager. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void storePassword() {
        System.out.print("Enter the account name: ");
        String account = scanner.nextLine();

        System.out.print("Enter the password: ");
        String password = scanner.nextLine();

        // Hash the password before storing it
        String hashedPassword = hashPassword(password);

        passwordVault.put(account, hashedPassword);

        System.out.println("Password stored successfully for account: " + account);
    }

    private static void retrievePassword() {
        System.out.print("Enter the account name: ");
        String account = scanner.nextLine();

        if (passwordVault.containsKey(account)) {
            System.out.print("Enter the password: ");
            String enteredPassword = scanner.nextLine();

            // Verify the entered password against the stored hashed password
            String storedHashedPassword = passwordVault.get(account);
            String enteredHashedPassword = hashPassword(enteredPassword);

            if (storedHashedPassword.equals(enteredHashedPassword)) {
                System.out.println("Password retrieved successfully for account: " + account);
            } else {
                System.out.println("Incorrect password. Access denied!");
            }
        } else {
            System.out.println("Account not found in the password vault.");
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }

            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }
}
