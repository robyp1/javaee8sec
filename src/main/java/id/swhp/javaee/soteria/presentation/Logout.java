package id.swhp.javaee.soteria.presentation;

import org.omnifaces.util.Servlets;

import javax.enterprise.inject.Model;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.omnifaces.util.Faces.getRequest;
import static org.omnifaces.util.Faces.getResponse;
import static org.omnifaces.util.Faces.redirect;
import static org.omnifaces.util.Messages.addGlobalError;

@Model
public class Logout {


    public void submit() throws IOException {
        try {
            HttpServletRequest req = getRequest();
            HttpServletResponse res = getResponse();
            req.logout();
            redirect("index.xhtml");
        } catch (ServletException e) {
            e.printStackTrace();
            addGlobalError("auth.message.error.failure");
        }
    }

}

