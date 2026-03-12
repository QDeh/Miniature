
package fr.miniature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jspecify.annotations.NonNull;

import fr.miniature.models.Post;
import fr.miniature.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/feeds")
public class FeedController extends HttpServlet {

    private List<@NonNull Post> posts = new ArrayList<>();

    public void init() {
        posts.add(new Post(0, new User("suer", "user@gmail.com", ""), "pcoucou"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
            HttpSession session = req.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                resp.sendRedirect("/login");
                return;
            }

            String action = req.getParameter("action");
            User user = (User) session.getAttribute("user");

            List<@NonNull Post> postsList = posts;

            if("subscriptions".equals(action)){
                postsList = posts.stream()
                    .filter(u -> user.getSubscriptions().stream()
                    .anyMatch(sub -> sub.getLogin().equals(u.getOwner().getLogin())))
                    .toList();
            }
            req.setAttribute("posts", postsList);
            req.getRequestDispatcher("/WEB-INF/views/feeds.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("logout".equals(action)){
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
                resp.sendRedirect("/login");
            }
            return;   
        }

            /*String loginString = req.getParameter("login");
            String emailString = req.getParameter("email");
            String paString = req.getParameter("password");
            String confirmString = req.getParameter("confirm");

            String error = validate(loginString, emailString, paString, confirmString);
            if (error != null) {
                req.setAttribute("error", error);
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            } else {
                User newUser = new User(loginString, emailString, paString);
                users.add(newUser);
                resp.sendRedirect("/login");
            }*/
        
        
            /*String loginString = req.getParameter("login");
            String paString = req.getParameter("password");
            boolean found = users.stream()
                .anyMatch(u -> u.getLogin().equals(loginString) && u.getPassword().equals(paString));

            if (found) {
                resp.sendRedirect("/feeds");
            } else {
                req.setAttribute("error", "Login ou mot de passe incorrect");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
            }
            

        }*/

        }
}
