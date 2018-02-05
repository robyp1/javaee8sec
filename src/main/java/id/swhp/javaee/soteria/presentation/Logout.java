package id.swhp.javaee.soteria.presentation;

import javax.enterprise.inject.Model;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.omnifaces.util.Faces.getRequest;
import static org.omnifaces.util.Messages.addGlobalError;

@Model
public class Logout {


    public void submit() throws IOException {
        try {
            getRequest().logout();
        } catch (ServletException e) {
            e.printStackTrace();
            addGlobalError("auth.message.error.failure");
        }
    }

}

