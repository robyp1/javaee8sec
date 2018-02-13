package id.swhp.javaee.soteria.application.security;

import id.swhp.javaee.soteria.business.account.boundary.AccountStore;
import id.swhp.javaee.soteria.business.account.entity.Account;
import id.swhp.javaee.soteria.business.exception.boundary.AccountNotVerifiedException;
import id.swhp.javaee.soteria.business.exception.boundary.InvalidCredentialException;
import id.swhp.javaee.soteria.business.exception.boundary.InvalidPasswordException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.security.enterprise.credential.CallerOnlyCredential;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
@ApplicationScoped
@Default
public class SoteriaIdentityStore implements IdentityStore {

    // call our EJB service to validate the account
    @Inject
    AccountStore accountStore;

    @Override
    public CredentialValidationResult validate(Credential credential) {
        try {

            // check if the credential was UsernamePasswordCredential
            if (credential instanceof UsernamePasswordCredential) {
                String username = ((UsernamePasswordCredential) credential).getCaller();
                String password = ((UsernamePasswordCredential) credential).getPasswordAsString();
                try {

                    return validate(this.accountStore.getByUsernameAndPassword(username, password));

                } catch (InvalidPasswordException e) {
                    Account account = this.accountStore.getByUsername(username).orElseThrow(InvalidCredentialException::new);
                    accountStore.incLoginFailedAttempt(account);
                    throw e;
                }
            }

            // check if the credential was UsernamePasswordCredential
            if (credential instanceof CallerOnlyCredential) {
                String username = ((CallerOnlyCredential) credential).getCaller();

                return validate(
                        this.accountStore.getByUsername(username)
                                .orElseThrow(InvalidCredentialException::new)
                );
            }

        } catch (InvalidCredentialException e) {
            return INVALID_RESULT;
        }
        return NOT_VALIDATED_RESULT;
    }

    // before return the CredentialValidationResult, check if the account is active or not
    private CredentialValidationResult validate(Account account) {
        if (!account.isActive()) {
            throw new AccountNotVerifiedException();
        }

        CredentialValidationResult credentialValidationResult = new CredentialValidationResult(account.getUsername());
        return credentialValidationResult;
    }
}
