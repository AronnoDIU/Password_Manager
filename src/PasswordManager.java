import java.security.MessageDigest; // For hashing the password using SHA-256
import java.security.NoSuchAlgorithmException; // For hashing the password using SHA-256
import java.util.HashMap; // For storing the account name and hashed password
import java.util.Map; // For storing the account name and hashed password
import java.util.Scanner;

public class PasswordManager {
    // Password vault to store the account name and hashed password
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
                case 1: // For Store password
                    storePassword();
                    break;
                case 2: // For Retrieve password
                    retrievePassword();
                    break;
                case 3: // For Exit
                    System.out.println("Exiting Password Manager. Goodbye!");
                    System.exit(0);
                    break;
                default: // For invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Store the account name and hashed password in the password vault
    private static void storePassword() {
        System.out.print("Enter the account name: ");
        String account = scanner.nextLine();

        System.out.print("Enter the password: ");
        String password = scanner.nextLine();

        // Hash the password before storing it
        String hashedPassword = hashPassword(password); // Hash the password using SHA-256

        // Store the account name and hashed password in the password vault
        passwordVault.put(account, hashedPassword);

        System.out.println("Password stored successfully for account: " + account);
    }

    // Retrieve the password from the password vault and verify it against the entered password
    private static void retrievePassword() {
        System.out.print("Enter the account name: ");
        String account = scanner.nextLine();

        // Check if the account exists in the password vault
        if (passwordVault.containsKey(account)) {
            System.out.print("Enter the password: ");
            String enteredPassword = scanner.nextLine();

            // Verify the entered password against the stored hashed password
            String storedHashedPassword = passwordVault.get(account);
            String enteredHashedPassword = hashPassword(enteredPassword);

            // Check if the entered password matches the stored hashed password
            if (storedHashedPassword.equals(enteredHashedPassword)) {
                System.out.println("Password retrieved successfully for account: " + account);
            } else {
                System.out.println("Incorrect password. Access denied!");
            }
        } else {
            System.out.println("Account not found in the password vault.");
        }
    }

    // Hash the password using SHA-256 and return the hashed password
    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hexStringBuilder.append(String.format("%02x", b));
            }

            return hexStringBuilder.toString(); // Return the hashed password
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            e.printStackTrace();
            return null;
        }
    }
}
