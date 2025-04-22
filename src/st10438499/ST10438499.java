package st10438499;
import javax.swing.*;

/**
 *
 * @author noxid
 */

public class ST10438499 {

    public static void main(String[] args) {
        //asks for the user information
        String username = JOptionPane.showInputDialog(null, "Enter username:");
        String password = JOptionPane.showInputDialog(null, "Enter password:");
        String cell = JOptionPane.showInputDialog(null, "Enter cell phone number");

        //creates new user
        userLogin login = new userLogin();
        
        //checks the login details
        boolean usernameValid = login.Username(username);
        boolean passwordValid = login.Password(password);
        boolean cellValid = CellPhoneValidator.validateCellPhone(cell);
        
        
        //checks if the user entered correct data then welcoms them 
        if (usernameValid && passwordValid && cellValid) {
            JOptionPane.showMessageDialog(null, "Welcome to Quick Chat");
            handleMessageFlow(); 
        } else {
            
            JOptionPane.showMessageDialog(null, "Login failed");
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
                    JOptionPane.showMessageDialog(null, "Exiting Quickchat");
                    break;

                default:
                    break;
            }
            
            // Repeats until quit option is chosen
        } while (mainChoice != 2 && mainChoice != JOptionPane.CLOSED_OPTION); 
    }
    
    public static boolean isValidNumber(String input) {
    if (input == null || input.isEmpty()) {
        return false;
    }
    //Searches throught to make sure there are only numbers
    for (int i = 0; i < input.length(); i++) {
        if (!Character.isDigit(input.charAt(i))) {
            return false; 
        }
    }

    return true; 
}
    
}