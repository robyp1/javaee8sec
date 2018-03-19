package id.swhp.javaee.soteria.presentation;

import id.swhp.javaee.soteria.business.account.entity.User;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class UserInSession {


    @Inject
    User user;

    public UserInSession() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
