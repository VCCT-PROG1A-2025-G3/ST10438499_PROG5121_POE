package st10438499;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 *
 * @author noxid
 */

public class ST10438499 {

     static Map<String, String> users = new HashMap<>();

     public static void main(String[] args) {
        userLogin Registration = new userLogin(); 
        
        //using a list of user Call and 
        List<userClass> userList = storageJSON.loadUsers();
        
        while (true) {
            String[] options = {"Register", "Login", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                null,
                "Welcome to QuickChat",
                "Main Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
            );

            switch (choice) {
                case 0: // Registering users and calling verification
                    String newUsername = JOptionPane.showInputDialog(null, "Enter a username:");
                    if (!Registration.Username(newUsername)) break;

                    String newPassword = JOptionPane.showInputDialog(null, "Enter a password:");
                    if (!Registration.Password(newPassword)) break;

                    String newCell = JOptionPane.showInputDialog(null, "Enter your phone number (e.g. (+27784519566):");
                    if (!CellPhoneValidator.validateCellPhone(newCell)) break;

                    users.put(newUsername, newPassword);
                    userList.add(new userClass(newUsername, newPassword, newCell)); 
                    storageJSON.saveUsers(userList); 
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                    
                    continue;

                case 1: // Login
                    String username = JOptionPane.showInputDialog(null, "Enter username:");
                    String password = JOptionPane.showInputDialog(null, "Enter password:");

                    boolean found = false;
                    for (userClass u : userList) {
                        if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                            JOptionPane.showMessageDialog(null, "Login successful. Welcome " + username);
                            handleMessageFlow();
                            found = true;
                            break;
                        }
                    }
                    
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "Login failed. Incorrect credentials.");
                    }
                    break;
                    
                case 2: // Exit
                case JOptionPane.CLOSED_OPTION:
                    return;
            }
        }
    }

    public static void handleMessageFlow() {
        
        String[] mainOptions = {"Send Message", "Recently Sent Messages", "Quit"};
        int mainChoice;

        //a repeat until loop until quit is chosen
        do {
            //shows the menu and gets the user to input
            mainChoice = JOptionPane.showOptionDialog(
                null,
                "QuickChat",
                "",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                mainOptions,
                mainOptions[0]
            );

            switch (mainChoice) {
               //Send Message
                case 0: 
                    
                    String countInput = JOptionPane.showInputDialog(null, "How many messages do you want to send");
                    
                    //checks if it is a number inputed
                    if (!isValidNumber(countInput)) {
                        JOptionPane.showMessageDialog(null, "Invalid number. Returning to main menu.");
                        break;
                    }

                    int messageCount = Integer.parseInt(countInput);

                    //for loop for the amount of messages the user chooses
                    for (int i = 1; i <= messageCount; i++) {
                        
                        String recipient = JOptionPane.showInputDialog(null, "Enter recipient number for message " + i);

                        //checks cellphone number 
                        if (recipient == null || !CellPhoneValidator.validateCellPhone(recipient)) {
                            JOptionPane.showMessageDialog(null, "Invalid recipient" + i);
                            continue;
                        }

                        
                        String messageText = JOptionPane.showInputDialog(null, "Enter message " + i);

                        //checks to see if the length is over 250 characters
                        if (messageText == null || messageText.length() > 250 || messageText.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Invalid message" + i);
                            continue;
                        }

                        Message msg = new Message(recipient, messageText);

                        //message summary
                        JOptionPane.showMessageDialog(null,
                        "Message " + i + "\n" +
                        "Message ID: " + msg.getMessageId() + "\n" +
                        "Hash: " + msg.getMessageHash() + "\n" +
                        "Recipient: " + msg.getRecipient() + "\n" +
                        "Message: " + msg.getMessage()
                        );

                        //Gives an option of send message, disregard message or store message in a menu 
                        Object[] messageOptions = {"Send Message", "Disregard Message", "Store Message"};
                        int messageChoice = JOptionPane.showOptionDialog(
                            null,
                            "Choose what to do with message " + i,
                            "Message Options",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            messageOptions,
                            messageOptions[0]
                        );

                        //displays a message based on what the user chooses
                        switch (messageChoice) {
                            case 0:
                                userData.addSentMessage(msg);
                                JOptionPane.showMessageDialog(null, "Message " + i + " sent to " + recipient);
                                break;
                            case 1:
                                userData.addDisregardedMessage(msg);
                                JOptionPane.showMessageDialog(null, "Message " + i + " discarded.");
                                break;
                            case 2:
                                userData.addStoredMessage(msg);
                                JOptionPane.showMessageDialog(null, "Message " + i + " stored");
                                break;
                        }
                    }
                    break;
                    
                    //Recently Sent Messages
                case 1: 
                    // Show a message indicating that this feature is not yet available
                    JOptionPane.showMessageDialog(null, "Coming soon!");
                    break;
                    
                    //quit
                case 2: 
                case JOptionPane.CLOSED_OPTION:
                    break;

                default:
                    break;
            }
            
            // Repeats until quit option is chosen
        } while (mainChoice != 2 && mainChoice != JOptionPane.CLOSED_OPTION); 
    }
    
     public static boolean isValidNumber(String input) {
        if (input == null || input.isEmpty()) return false;
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
    

