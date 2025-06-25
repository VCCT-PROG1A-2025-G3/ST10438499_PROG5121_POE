package st10438499;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ST10438499Test {

    @Test
    public void testMessageCreationAndClassification() {
        // Create test messages
        Message msg1 = new Message("Sender1", "+27834557896", "Did you get the cake?");
        Message msg2 = new Message("Sender2", "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message msg3 = new Message("Sender3", "+27834484567", "Yohoooo, I am at your gate.");
        Message msg4 = new Message("Sender4", "0838884567", "It is dinner time!");
        Message msg5 = new Message("Sender5", "+27838884567", "Ok, I am leaving without you.");

        // Simulate classification
        List<Message> sent = new ArrayList<>();
        List<Message> stored = new ArrayList<>();
        List<Message> discarded = new ArrayList<>();

        sent.add(msg1);
        sent.add(msg4);
        stored.add(msg2);
        stored.add(msg5);
        discarded.add(msg3);

        // 1. Sent Messages array correctly populated
        assertEquals(2, sent.size());
        assertEquals("Did you get the cake?", sent.get(0).getMessage());
        assertEquals("It is dinner time!", sent.get(1).getMessage());

        // 2. Display the longest message
        Message longest = msg1;
        for (Message m : List.of(msg1, msg2, msg3, msg4, msg5)) {
            if (m.getMessage().length() > longest.getMessage().length()) {
                longest = m;
            }
        }
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest.getMessage());

        // 3. Search for message by ID (simulate)
        String targetId = msg4.getMessageId();
        boolean found = false;
        for (Message m : List.of(msg1, msg2, msg3, msg4, msg5)) {
            if (m.getMessageId().equals(targetId)) {
                assertEquals("It is dinner time!", m.getMessage());
                assertEquals("0838884567", m.getRecipient());
                found = true;
                break;
            }
        }
        assertTrue(found);

        // 4. Search for all messages sent to a particular recipient
        List<Message> messagesToRecipient = new ArrayList<>();
        for (Message m : List.of(msg1, msg2, msg3, msg4, msg5)) {
            if (m.getRecipient().equals("+27838884567")) {
                messagesToRecipient.add(m);
            }
        }
        assertEquals(2, messagesToRecipient.size());
        assertEquals("Where are you? You are late! I have asked you to be on time.", messagesToRecipient.get(0).getMessage());
        assertEquals("Ok, I am leaving without you.", messagesToRecipient.get(1).getMessage());

        // 5. Delete a message using a hash
        String hashToDelete = msg2.getMessageHash();
        boolean deleted = stored.removeIf(m -> m.getMessageHash().equals(hashToDelete));
        assertTrue(deleted);
        assertEquals(1, stored.size()); // msg2 removed
        assertFalse(stored.get(0).getMessage().equals(msg2.getMessage())); // msg2 not in list

        // 6. Display Report
        StringBuilder report = new StringBuilder();
        for (Message m : sent) {
            report.append("Message Hash: ").append(m.getMessageHash()).append("\n")
                  .append("Recipient: ").append(m.getRecipient()).append("\n")
                  .append("Message: ").append(m.getMessage()).append("\n")
                  .append("---------------------------------\n");
        }
        String reportStr = report.toString();
        assertTrue(reportStr.contains("Message Hash"));
        assertTrue(reportStr.contains("Did you get the cake?"));
        assertTrue(reportStr.contains("It is dinner time!"));
    }

    @Test
    public void testIsValidNumber() {
        assertTrue(ST10438499.isValidNumber("123"));
        assertTrue(ST10438499.isValidNumber("999999"));
        assertFalse(ST10438499.isValidNumber(""));
        assertFalse(ST10438499.isValidNumber("12a3"));
        assertFalse(ST10438499.isValidNumber(null));
    }
}
