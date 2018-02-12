package id.swhp.javaee.soteria.presentation;

import id.swhp.javaee.soteria.business.account.boundary.AccountStore;
import id.swhp.javaee.soteria.business.account.entity.UserNameNotTaken;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.validation.Valid;

import static org.omnifaces.util.Messages.addGlobalInfo;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Model
public class Register {

    private String username;
    private String password;
    private String email;

    @Inject
    AccountStore accountStore;

    public void submit() {
        this.accountStore.registerAccount(username, email, password);
        addGlobalInfo("register.message.success");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(@Valid @UserNameNotTaken String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
