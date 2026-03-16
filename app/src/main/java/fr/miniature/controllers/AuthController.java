package fr.miniature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.NonNull;

import fr.miniature.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/register", "/login"})
public class AuthController extends HttpServlet {

    private List<@NonNull User> users = new ArrayList<>();

    // pour tester
    private User suer = new User("suer", "user@gmail.com", "");
    private User shinmen = new User("shinmen", "shinmen@gmail.com", "123456");
    private User admin = new User("admin", "user@gmail.com", "admin");
    private User piedbouche = new User("piedbouche", "piedbouche@gmail.com", "piedbouche");
    private User corine59 = new User("corine59", "corine@gmail.com", "corine59");
    
    public void init() {
        users.add(suer);
        users.add(shinmen);
        users.add(admin);
        users.add(piedbouche);
        users.add(corine59);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        if (path.equals("/register")) {
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);

        } else if (path.equals("/login")) {
            req.setAttribute("users", users);
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        if (path.equals("/register")) {
            String loginString = req.getParameter("login");
            String emailString = req.getParameter("email");
            String paString = req.getParameter("password");
            String confirmString = req.getParameter("confirm");

            String error = validate(loginString, emailString, paString, confirmString);
            if (error != null) {
                req.setAttribute("error", error);
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
                return;
            } else {
                User newUser = new User(loginString, emailString, paString);
                users.add(newUser);
                resp.sendRedirect("/login");
            }
        
            

        } else if (path.equals("/login")) {
            String loginString = req.getParameter("login");
            String paString = req.getParameter("password");
            Optional<@NonNull User> optUser = users.stream()
                .filter(u -> u.getLogin().equals(loginString) && u.getPassword().equals(paString))
                .findFirst();

            if (optUser.isPresent()) {
                HttpSession session = req.getSession();
                User user = optUser.get();
                session.setAttribute("user", user);
                resp.sendRedirect("/feeds");
            } else {
                req.setAttribute("error", "Login ou mot de passe incorrect");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            }
            

        }

    }

    private String validate(String loginString, String emailString, String paString, String confirmString) {
        if (loginString.isBlank()) return "Username vide";
        if (!paString.equals(confirmString)) return "Les mots de passe ne correspondent pas";
        if (users.stream().anyMatch(u -> u.getLogin().equals(loginString))) return "Username déjà pris";
        return null;
}

    
}
