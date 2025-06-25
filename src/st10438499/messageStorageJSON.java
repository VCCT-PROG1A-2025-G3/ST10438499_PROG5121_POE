package st10438499;

// Import JSON handling classes
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class messageStorageJSON {

    /**
     * Saves a list of Message objects to a JSON file.
     *
     * @param messages List of messages to save
     * @param filename The filename to write to (e.g., "messages.json")
     */
    public static void saveMessages(List<Message> messages, String filename) {
        JSONArray array = new JSONArray();  // Create a JSON array to hold all messages

        // Convert each Message object to JSON and add it to the array
        for (Message msg : messages) {
            array.put(msg.toJson());  // toJson() is assumed to return a JSONObject
        }

        // Try to write the JSON array to the specified file
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(array.toString()); // Write JSON as string to file
        } catch (IOException e) {
            e.printStackTrace(); // Print error if file writing fails
        }
    }

    /**
     * Loads messages from a JSON file and returns them as a List<Message>.
     *
     * @param filename The filename to read from (e.g., "messages.json")
     * @return List of Message objects loaded from the file
     */
    public static List<Message> loadMessages(String filename) {
        List<Message> messages = new ArrayList<>(); // List to store loaded messages
        File file = new File(filename); // File object for the given filename

        // If the file doesn't exist, return the empty list
        if (!file.exists()) return messages;

        // Try to read the JSON data from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonText = new StringBuilder(); // Holds entire file content
            String line;

            // Read each line and append it to jsonText
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }

            // Parse the full text into a JSONArray
            JSONArray array = new JSONArray(jsonText.toString());

            // Loop through each JSON object and convert to a Message object
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                messages.add(Message.fromJson(obj)); // fromJson() converts JSON to Message
            }

        } catch (Exception e) {
            e.printStackTrace(); // Print error if something goes wrong
        }

        return messages; // Return the list of loaded messages
    }
}
