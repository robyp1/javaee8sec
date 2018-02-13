package id.swhp.javaee.soteria.business.account.entity;

import id.swhp.javaee.soteria.business.security.entity.Token;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@Entity
@Table(name = "account", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username", "email"})
})
@NamedQueries({
    @NamedQuery(name = Account.FIND_BY_USERNAME, query = "select a from Account a where a.username = :username")
    ,
    @NamedQuery(name = Account.FIND_BY_EMAIL, query = "select a from Account a where a.email = :email")
    ,
    @NamedQuery(name = Account.FIND_BY_TOKEN, query = "select a from Account a inner join a.tokens t where t.tokenHash = :tokenHash and t.tokenType = :tokenType and t.expiration > CURRENT_TIMESTAMP")
    ,
    @NamedQuery(name = Account.FAILED_ACESS_THRESHOLD, query = "select a from Account a where a.username =:username and a.failedConsecutiveLogin > 5" )
})
public class Account implements Serializable {

    public static final String FIND_BY_USERNAME = "Account.findByUsername";
    public static final String FIND_BY_EMAIL = "Account.findByEmail";
    public static final String FIND_BY_TOKEN = "Account.findByToken";
    public static final String FAILED_ACESS_THRESHOLD = "Account.loginFailedAttempt";

    @Id
    @GeneratedValue(generator = "account_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "account_id_seq", sequenceName = "account_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Size(min = 1, max = 100)
    private String email;

    @Column(name = "active")
    @NotNull
    private boolean active;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Token> tokens = new ArrayList<>();

    @Column(name = "num_login_failed")
    @Basic(optional = true)
    private Integer failedConsecutiveLogin;

    public Account() {
    }

    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.failedConsecutiveLogin = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public Integer getFailedConsecutiveLogin() {
        return failedConsecutiveLogin;
    }

    public void setFailedConsecutiveLogin(Integer failedConsecutiveLogin) {
        this.failedConsecutiveLogin = failedConsecutiveLogin;
    }

    /* Both method addToken and removeToken is necessary, to avoid propagation issue see: https://vladmihalcea.com/2017/03/29/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/#more-7143 */
    public void addToken(Token token) {
        this.tokens.add(token);
        token.setAccount(this);
    }

    public void removeToken(Token token) {
        this.tokens.remove(token);
        token.setAccount(this);
    }

    @Override
    public String toString() {
        return "Account: " + this.username;
    }
}
