
package st10438499;

/**
 *
 * @author noxid
 */
public class Message {
    //declaring variables
    private static int messageCount = 0;
    private String messageId;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    public Message(String recipient, String message) {
        //assigning variables
        this.messageId = generateMessageID();
        this.messageNumber = ++messageCount;
        this.recipient = recipient;
        this.message = message;
        this.messageHash = generateHash();
    }

    private String generateMessageID() {
        //generating a random number between 1000000000 for the message id
        return String.format("%010d", (int)(Math.random() * 1_000_000_000));
    }

    private String generateHash() {
        
        String[] words = message.trim().split(" ");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : first;
        return (messageId.substring(0, 2) + ":" + messageNumber + ":" + first + last).toUpperCase();
    }

    public String getMessageId() { return messageId; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getMessageHash() { return messageHash; }
}
