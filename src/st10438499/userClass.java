
package st10438499;

import org.json.JSONObject;

/**
 *
 * @author noxid
 */
public class userClass {
    private String username;
    private String password;
    private String cellphone;

    
    //create a new user object
    public userClass(String username, String password, String cellphone) {
        this.username = username;
        this.password = password;
        this.cellphone = cellphone;
    }

    //Converts the user object into a JSON object
    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        obj.put("username", username);
        obj.put("password", password);
        obj.put("cellphone", cellphone);
        return obj;
    }

    // Creates a user object from a JSON object
    public static userClass fromJson(JSONObject obj) {
        return new userClass(
            obj.getString("username"),
            obj.getString("password"),
            obj.getString("cellphone")
        );
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

