
package st10438499;
import java.util.*;
/**
 *
 * @author noxid
 */



public class userData {
    public static List<Message> sentMessages = new ArrayList<>();
    public static List<Message> disregardedMessages = new ArrayList<>();
    public static List<Message> storedMessages = new ArrayList<>();

    public static void addSentMessage(Message msg) {
        sentMessages.add(msg);
    }

    public static void addDisregardedMessage(Message msg) {
        disregardedMessages.add(msg);
    }

    public static void addStoredMessage(Message msg) {
        storedMessages.add(msg);
    }

    public static Message getLongestMessage() {
        return sentMessages.stream()
                .max(Comparator.comparingInt(m -> m.getMessage().length()))
                .orElse(null);
    }

    public static Message searchById(String id) {
        return sentMessages.stream()
                .filter(m -> m.getMessageId().equals(id))
                .findFirst().orElse(null);
    }

    public static List<Message> searchByRecipient(String recipient) {
        List<Message> result = new ArrayList<>();
        for (Message m : sentMessages) {
            if (m.getRecipient().equals(recipient)) {
                result.add(m);
            }
        }
        return result;
    }

    public static boolean deleteByHash(String hash) {
        return sentMessages.removeIf(m -> m.getMessageHash().equals(hash));
    }

    public static String generateReport() {
        StringBuilder report = new StringBuilder("Sent Messages Report:\n\n");
        for (Message m : sentMessages) {
            report.append("To: ").append(m.getRecipient()).append("\n")
                  .append("Message: ").append(m.getMessage()).append("\n")
                  .append("ID: ").append(m.getMessageId()).append("\n")
                  .append("Hash: ").append(m.getMessageHash()).append("\n\n");
        }
        return report.toString();
    }
}


