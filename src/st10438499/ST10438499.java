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
    
    static List<Message> sentMessages = messageStorageJSON.loadMessages("sentMessages.json");
    static List<Message> storedMessages = messageStorageJSON.loadMessages("storedMessages.json");
    static List<Message> discardedMessages = messageStorageJSON.loadMessages("discardedMessages.json");

    static Map<String, String> users = new HashMap<>();
    static String currentSender = null; 
 
     
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
                            currentSender = username;
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
        
        String[] mainOptions = {"Send Message", "View Sent Messages", "Manage Sent Messages", "Quit"};


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

                        Message msg = new Message(currentSender, recipient, messageText);

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
                            //Sending message
                            case 0:
                                sentMessages.add(msg);
                                messageStorageJSON.saveMessages(sentMessages, "sentMessages.json");
                                JOptionPane.showMessageDialog(null, "Message " + i + " sent to " + recipient);
                                break;
                                
                            //Disgarding message    
                            case 1:
                                discardedMessages.add(msg);
                                messageStorageJSON.saveMessages(discardedMessages, "discardedMessages.json");
                                JOptionPane.showMessageDialog(null, "Message " + i + " discarded.");
                                break;
                                
                            //Storing message     
                            case 2:
                                storedMessages.add(msg);
                                messageStorageJSON.saveMessages(storedMessages, "storedMessages.json");
                                JOptionPane.showMessageDialog(null, "Message " + i + " stored");
                                break;
                        }
                    }
                    break;
                    
                    //Recently Sent Messages
                case 1: 
                    if (sentMessages.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No sent messages found.");
                        } else {
                        StringBuilder sentMsgDisplay = new StringBuilder("Sent Messages:\n\n");
                        for (Message msg : sentMessages) {
                            sentMsgDisplay.append("Message ID: ").append(msg.getMessageId()).append("\n")
                                    .append("Hash: ").append(msg.getMessageHash()).append("\n")
                                    .append("Recipient: ").append(msg.getRecipient()).append("\n")
                                    .append("Message: ").append(msg.getMessage()).append("\n\n");
                        }
                        JOptionPane.showMessageDialog(null, sentMsgDisplay.toString());
                    }
                    
                    break;
                    
                case 2: // View Stored Messages
                    if (storedMessages.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No stored messages found.");
                    } else {
                        StringBuilder storedMsgDisplay = new StringBuilder("Stored Messages:\n\n");
                    for (Message msg : storedMessages) {
                        storedMsgDisplay.append("Message ID: ").append(msg.getMessageId()).append("\n")
                            .append("Hash: ").append(msg.getMessageHash()).append("\n")
                            .append("Recipient: ").append(msg.getRecipient()).append("\n")
                            .append("Message: ").append(msg.getMessage()).append("\n\n");
                    }
                        JOptionPane.showMessageDialog(null, storedMsgDisplay.toString());
                    }
                    break;
                
                //calls the manage sent messages class
                case 3:
                    manageSentMessages.manage(sentMessages);
                    sentMessages = messageStorageJSON.loadMessages("sentMessages.json");
                    break;
                        
                //quit
                case 4: 
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
    

