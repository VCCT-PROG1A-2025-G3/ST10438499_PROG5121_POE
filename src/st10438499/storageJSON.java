package st10438499;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author noxid
 */
public class storageJSON {

     // Path to the JSON file where user data will be stored
    private static final String FILE_PATH = "users.json";

    //Saves a list of users to a JSON file
    public static void saveUsers(List<userClass> users) {
        JSONArray userArray = new JSONArray();
        
        // Convert each user to JSON and add to array
        for (userClass u : users) {
            userArray.put(u.toJson());
        }
        
        // Write JSON array to the file
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(userArray.toString(4)); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads users from the JSON file
    public static List<userClass> loadUsers() {
        List<userClass> users = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return users;

        // Read file contents and build the JSON array
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder jsonText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonText.append(line);
            }

            // Convert each JSONObject into a userClass object
            JSONArray userArray = new JSONArray(jsonText.toString());
            for (int i = 0; i < userArray.length(); i++) {
                JSONObject obj = userArray.getJSONObject(i);
                users.add(userClass.fromJson(obj));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
