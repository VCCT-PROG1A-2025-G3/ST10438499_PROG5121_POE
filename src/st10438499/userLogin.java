package st10438499;
import java.util.Scanner;

/**
 *
 * @author noxid
 */

public class userLogin {
    
// This method creates a new user account
    public Boolean Username(String username) {
         
        //error verification for username length
        if (username.contains("_") && username.length() <= 5) {
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted. Please ensure "
                    + "that your username contains an underscore and is no more "
                    + "than five characters in length.");
            return false;
        }
    }
   
    // Checks the length of the passwrod
    public Boolean Password(String password) {
        if (password.length() < 8) {
            System.out.println("Password is not the correct length.");
            return false;
        }
        
        // Checks if there is empty space
        if (password.contains(" ")) {
            System.out.println("Password has an empty space");
            return false;
        }

         //for loop that checks each character for a number
        int numberCount = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                numberCount++;
            }
        }
        if (numberCount == 0) {
            System.out.println("Password is missing a number");
            return false;
        }

         // Checks for Special Characters   
        if (!(password.contains("@") || password.contains("#") || password.contains("!")
                || password.contains("~") || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&") || password.contains("*")
                || password.contains("(") || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/") || password.contains(":")
                || password.contains(".") || password.contains(",") || password.contains("<")
                || password.contains(">") || password.contains("?") || password.contains("|"))) {
            System.out.println("Password is missing a special character");
            return false;
        }

        //for loop that checks each character for a upper case letter
        int capitalCount = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                capitalCount++;
            }
        }
        if (capitalCount == 0) {
            System.out.println("Password is missing a capital letter");
            return false;
        }

        // If there is no return false then print true
        System.out.println("Password successfully captured");
        return true;
    }

    //login area
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        userLogin login = new userLogin();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Cellphone number: ");
        String cellNumber = scanner.nextLine();

        boolean usernameValid = login.Username(username);
        boolean passwordValid = login.Password(password);
        boolean cellPhoneValid = CellPhoneValidator.validateCellPhone(cellNumber);

        if (usernameValid && passwordValid && cellPhoneValid) {
            System.out.println("Welcome " + username);
        } else {
            System.out.println("Login failed");
        }

        scanner.close();
    }
}

 /*I am making a chat app and i need a java based cellphone 
    checker for login credentials that contains the country code 
    of south africa followed by the number and is no more than ten characters long.
    OpenAI.2025 */
class CellPhoneValidator {

    public static boolean validateCellPhone(String cellNumber) {
        if (cellNumber == null) {
            System.out.println("Cell phone incorrectly formatted or does not contain international code");
            return false;
        }

        String numberPart = cellNumber.substring(3);

        if (!numberPart.matches("\\d{9}")) {
            System.out.println("Cell phone must contain exactly 9 digits after the +");
            return false;
        }

        System.out.println("Cell phone number successfully added");
        return true;
    }
}
