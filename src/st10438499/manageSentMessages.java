package st10438499;

import javax.swing.JOptionPane;
import java.util.List;
import st10438499.messageStorageJSON;

public class manageSentMessages {

    public static void manage(List<Message> sentMessages) {
        String[] options = {
            " Show sender & recipient of all messages",
            " Show longest message",
            " Search by Message ID",
            " Search by Recipient",
            " Delete by Hash",
            " Display Full Report",
            "Back to Main Menu"
        };

        while (true) {
            int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an option",
                "Manage Sent Messages",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == JOptionPane.CLOSED_OPTION || choice == 6) {
                // User closed dialog or chose to go back
                return;
            }

            switch (choice) {
                case 0: // Show sender and recipient of all messages
                    if (sentMessages.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No messages found.");
                    } else {
                        StringBuilder sb = new StringBuilder("Sender & Recipient Info:\n\n");
                    for (Message msg : sentMessages) {
                        sb.append("Sender: ").append(msg.getSender()).append("\n")
                        .append("Recipient: ").append(msg.getRecipient()).append("\n")
                        .append("---------------------------------\n");
                     }
                    JOptionPane.showMessageDialog(null, sb.toString());
                    }
                    break;

                case 1: // Show longest message
                    if (sentMessages.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No messages found.");
                        break;
                    }
                    Message longest = null;
                    for (Message msg : sentMessages) {
                        if (longest == null || msg.getMessage().length() > longest.getMessage().length()) {
                            longest = msg;
                        }
                    }
                    JOptionPane.showMessageDialog(null,
                        "Longest Message:\n" +
                        "Recipient: " + longest.getRecipient() + "\n" +
                        "Message:\n" + longest.getMessage()
                    );
                    break;

                case 2: // Search by Message ID
                    String idSearch = JOptionPane.showInputDialog("Enter Message ID:");
                    if (idSearch == null) break; // User canceled
                    boolean found = false;
                    for (Message msg : sentMessages) {
                        if (msg.getMessageId().equals(idSearch)) {
                            JOptionPane.showMessageDialog(null,
                                "Recipient: " + msg.getRecipient() + "\n" +
                                "Message:\n" + msg.getMessage());
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        JOptionPane.showMessageDialog(null, "No message with that ID found.");
                    }
                    break;

                case 3: // Search by Recipient
                    String recipientSearch = JOptionPane.showInputDialog("Enter recipient number:");
                    if (recipientSearch == null) break;
                    StringBuilder matches = new StringBuilder();
                    for (Message msg : sentMessages) {
                        if (msg.getRecipient().equals(recipientSearch)) {
                            matches.append("Message: ").append(msg.getMessage())
                                   .append("\n---------------------------------\n");
                        }
                    }
                    if (matches.length() > 0) {
                        JOptionPane.showMessageDialog(null, "Messages to " + recipientSearch + ":\n\n" + matches);
                    } else {
                        JOptionPane.showMessageDialog(null, "No messages found for that recipient.");
                    }
                    break;

                case 4: // Delete by Hash
                    String hashToDelete = JOptionPane.showInputDialog("Enter message hash to delete:");
                    if (hashToDelete == null) break;
                    boolean deleted = sentMessages.removeIf(msg -> msg.getMessageHash().equals(hashToDelete));
                    if (deleted) {
                        // Save the updated list - make sure messageStorageJSON is accessible here
                        messageStorageJSON.saveMessages(sentMessages, "sentMessages.json");
                        sentMessages.clear();
                        sentMessages.addAll(messageStorageJSON.loadMessages("sentMessages.json"));
                        JOptionPane.showMessageDialog(null, "Message deleted.");
                    } else {
                        JOptionPane.showMessageDialog(null, "No message with that hash found.");
                    }
                    break;

                case 5: // Display Full Report
                    if (sentMessages.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No messages found.");
                    } else {
                        StringBuilder report = new StringBuilder("Full Sent Messages Report:\n\n");
                        for (Message msg : sentMessages) {
                            report.append("Message Number: ").append(msg.getMessageNumber()).append("\n")
                                  .append("Sender: ").append(msg.getSender()).append("\n")  
                                  .append("ID: ").append(msg.getMessageId()).append("\n")
                                  .append("Hash: ").append(msg.getMessageHash()).append("\n")
                                  .append("Recipient: ").append(msg.getRecipient()).append("\n")
                                  .append("Message: ").append(msg.getMessage()).append("\n")
                                  .append("---------------------------------\n");
                        }
                        JOptionPane.showMessageDialog(null, "Total messages now: " + sentMessages.size());
                        JOptionPane.showMessageDialog(null, report.toString());
                    }
                    break;

                default:
                    break;
            }
        }
    }
}
