package beans;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class User implements Serializable {

    String uID, uName, uEmail, uPass;

    public User(String uName, String uEmail, String uPass) throws NoSuchAlgorithmException {
        this.uID = Arrays.toString(MessageDigest.getInstance("MD5").digest((uEmail + uPass).getBytes(StandardCharsets.UTF_8)));
        this.uName = uName;
        this.uEmail = uEmail;
        this.uPass = uPass;
    }

    public String getuID() {
        return uID;
    }
    public void setuID(String uID) {
        this.uID = uID;
    }

    public String getuName() {
        return uName;
    }
    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuEmail() {
        return uEmail;
    }
    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuPass() {
        return uPass;
    }
    public void setuPass(String uPass) {
        this.uPass = uPass;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
