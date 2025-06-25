package st10438499;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading user data to/from a JSON file.
 * This class uses a static file path ("users.json") to persist user data.
 */
public class storageJSON {

    // Path to the file where user data is saved
    private static final String FILE_PATH = "users.json";

    /**
     * Saves a list of userClass objects to a JSON file.
     *
     * @param users List of userClass instances to save
     */
    public static void saveUsers(List<userClass> users) {
        JSONArray userArray = new JSONArray(); // JSON array to hold user data

        // Convert each user to JSON and add to the array
        for (userClass user : users) {
            userArray.put(user.toJson());
        }

        // Write the JSON array to the file
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(userArray.toString(4)); // '4' means pretty-print with indentation
        } catch (IOException e) {
            e.printStackTrace(); // Log the error if writing fails
        }
    }

    /**
     * Loads a list of userClass objects from the JSON file.
     *
     * @return List of userClass instances loaded from file, or empty list if file not found or empty
     */
    public static List<userClass> loadUsers() {
        List<userClass> users = new ArrayList<>(); // List to hold loaded users
        File file = new File(FILE_PATH);

        // If file doesn't exist, return empty list
        if (!file.exists()) return users;

        // Try reading file contents
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonText = new StringBuilder();
            String line;

            // Read each line and append it to jsonText
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }

            // Parse the file content into a JSON array
            JSONArray userArray = new JSONArray(jsonText.toString());

            // Convert each JSON object to a userClass instance
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject obj = userArray.getJSONObject(i);
                users.add(userClass.fromJson(obj)); // Assuming userClass has fromJson method
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log any error that occurs during reading/parsing
        }

        return users; // Return the loaded user list
    }
}
