package id.swhp.javaee.soteria.business.exception.boundary;

/**
 *
 * @author Sukma Wardana
 * @since 1.0
 */
public class InvalidUsernameException extends BusinessException {

    public InvalidUsernameException(String message) {
        super(message);
    }

    public InvalidUsernameException() {
    }
}
