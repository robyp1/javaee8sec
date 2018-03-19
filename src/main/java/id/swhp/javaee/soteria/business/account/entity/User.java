package id.swhp.javaee.soteria.business.account.entity;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class User implements Serializable{

    private String username;
    private long userId;
    private String userToken;

    public User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUsername() {
        return username;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserToken() {
        return userToken;
    }
}
