package backend;

public class user {
    String userID,userName,userPass,userMail,userPhone,userUID,userAdd;

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserUID() {
        return userUID;
    }

    public String getUserAdd() {
        return userAdd;
    }

    public user(String userID, String userName, String userPass, String userMail, String userAdd, String userPhone, String userUID) {
        this.userID = userID;
        this.userName = userName;
        this.userPass = userPass;
        this.userMail = userMail;
        this.userPhone = userPhone;
        this.userUID = userUID;
        this.userAdd = userAdd;
    }
}
