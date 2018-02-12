package id.swhp.javaee.soteria.business.account.entity;

import id.swhp.javaee.soteria.business.account.boundary.AccountStore;
import id.swhp.javaee.soteria.business.exception.boundary.InvalidUsernameException;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UserNameNotTakenValidator implements ConstraintValidator<UserNameNotTaken, String> {

    @Inject
    AccountStore accountStore;

    final static String ERROR_MSG ="Username already exists!";
    @Override
    public void initialize(UserNameNotTaken constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            Optional<Account> byUsername = accountStore.getByUsername(value);
            if (byUsername.isPresent() ) {
                throw new InvalidUsernameException(ERROR_MSG);
            }
        }catch (InvalidUsernameException ex ){
            ex.printStackTrace();
            return false;
        }
        return true;
    }


}
