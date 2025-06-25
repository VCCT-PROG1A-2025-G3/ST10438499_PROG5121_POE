package st10438499;

import org.json.JSONObject;

/**
 * Represents a chat message with sender, recipient, content, ID, number, and hash.
 * Can be converted to and from JSON.
 */
public class Message {

    // Static counter to track total number of messages created
    private static int messageCount = 0;

    // Instance variables for each message
    private String messageId;
    private int messageNumber;
    private String sender;       // NEW
    private String recipient;
    private String message;
    private String messageHash;

    /**
     * Constructor used when creating a new message
     * Automatically generates message ID, number, and hash
     */
    public Message(String sender, String recipient, String message) {
        this.sender = sender;                       // NEW
        this.messageId = generateMessageID();      // Random 10-digit ID
        this.messageNumber = ++messageCount;       // Increment message count
        this.recipient = recipient;
        this.message = message;
        this.messageHash = generateHash();         // Create a hash for this message
    }

    /**
     * Converts this message to a JSONObject for saving to file.
     */
    public JSONObject toJson() {
    JSONObject obj = new JSONObject();
    obj.put("sender", sender);
    obj.put("recipient", recipient);
    obj.put("message", message);
    obj.put("messageId", messageId);
    obj.put("messageHash", messageHash);
    obj.put("messageNumber", messageNumber);
    return obj;
}


    /**
     * Reconstructs a Message object from a JSONObject (e.g., when loading from file).
     */
    public static Message fromJson(JSONObject obj) {
    String sender = obj.has("sender") ? obj.getString("sender") : "Unknown";

    Message m = new Message(sender, obj.getString("recipient"), obj.getString("message"));
    m.messageId = obj.getString("messageId");
    m.messageHash = obj.getString("messageHash");
    m.messageNumber = obj.getInt("messageNumber");

    if (m.messageNumber > messageCount) {
        messageCount = m.messageNumber;
    }

    return m;
}


    
    

    /**
     * Generates a random 10-digit message ID.
     */
    private String generateMessageID() {
        return String.format("%010d", (int)(Math.random() * 1_000_000_000));
    }

    /**
     * Creates a message hash from the ID, number, and first/last words of the message.
     * This could be used as a basic fingerprint or verification.
     */
    private String generateHash() {
        String[] words = message.trim().split(" ");
        String first = words.length > 0 ? words[0] : "";
        String last = words.length > 1 ? words[words.length - 1] : first;
        return (messageId.substring(0, 2) + ":" + messageNumber + ":" + first + last).toUpperCase();
    }

    // Getters to allow access to private fields (no setters needed if immutable)
    public String getMessageId() { return messageId; }
    public int getMessageNumber() { return messageNumber; }
    public String getSender() { return sender; }               // NEW
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getMessageHash() { return messageHash; }
}
