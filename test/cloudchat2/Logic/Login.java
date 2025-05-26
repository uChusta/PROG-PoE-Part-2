package cloudchat2.Logic;

import java.io.*;
import java.util.*;

public class Login {

    private String loggedInFirstName;
    private String loggedInLastName;
    private boolean loginSuccess = false;
    private String dataFileName; // This will now be configurable

    // Default constructor for the main application (uses "users.txt")
    public Login() {
        this.dataFileName = "users.txt";
    }

    // Constructor for testing or if you want to specify a different file
    public Login(String fileName) {
        this.dataFileName = fileName;
    }

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        return password.length() >= 8 && hasUppercase && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phoneNumber) {
        // Matches an international code (e.g., +27) followed by 10 digits
        // This is based on typical phone number formats with international codes.
        // The doc says "no more than ten characters" for Recipient, which might imply just the local number.
        // Sticking to the current regex for phone number validation as it's more robust.
        return phoneNumber.matches("^\\+\\d{1,3}\\d{10}$");
    }

    public String registerUser(String username, String password, String phone, String firstName, String lastName) {
        boolean isUsernameValid = checkUserName(username);
        boolean isPasswordValid = checkPasswordComplexity(password);
        boolean isPhoneValid = checkCellPhoneNumber(phone);

        // 1. First, validate input formats
        if (!isUsernameValid) {
            return "Username is not correctly formatted";
        }
        if (!isPasswordValid) {
            return "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        if (!isPhoneValid) {
            return "Cellphone number incorrectly formatted or does not contain international code";
        }

        // 2. If formats are valid, then check if username already exists in the file
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(username)) {
                    return "Username already exists.";
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, which is fine for initial registration, continue to write
        } catch (IOException e) {
            // Other IO errors during read
            e.printStackTrace(); // Log the error for debugging
            return "An error occurred while checking for existing user.";
        }

        // 3. If all validations pass and username does not exist, save the user
        try (FileWriter fw = new FileWriter(dataFileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            // Save in format: username,password,phone,firstName,lastName
            out.println(username + "," + password + "," + phone + "," + firstName + "," + lastName);
        } catch (IOException e) {
            return "An error occurred while saving user.";
        }

        return "User has been registered successfully.";
    }

    public boolean loginUser(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dataFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String savedUsername = parts[0];
                    String savedPassword = parts[1];
                    String savedPhone = parts[2];
                    String savedFirstName = parts[3];
                    String savedLastName = parts[4];

                    if (savedUsername.equals(username) && savedPassword.equals(password)) {
                        loggedInFirstName = savedFirstName;
                        loggedInLastName = savedLastName;
                        loginSuccess = true;
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Log the error or handle it more gracefully
        }
        loginSuccess = false;
        return false;
    }

    public String returnLoginStatus() {
        if (loginSuccess) {
            return "Welcome " + loggedInFirstName + ", " + loggedInLastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again";
        }
    }
}