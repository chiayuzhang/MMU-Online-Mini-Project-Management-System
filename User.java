/*
 * Created by CHIA YU ZHANG
 * class User
 */

public class User{

    private String userID;
    private String password; 

    User(String userID, String password){
        updateUserID(userID);
        updatePassword(password);
    }

    // get the user information by using the get function
    public String getUserID(){
        return userID;
    }

    public String getPassword(){
        return password;
    }


    // update/set the user information by using the update function
    public void updateUserID(String userID){
        this.userID = userID;
    }

    public void updatePassword(String password){
        this.password = password;
    }

}