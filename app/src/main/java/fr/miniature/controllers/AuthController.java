package fr.miniature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jspecify.annotations.NonNull;

import fr.miniature.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet({"/register", "/login"})
public class AuthController extends HttpServlet {

    private List<@NonNull User> users = new ArrayList<>();

    public void init() {
        users.add(new User("shinmen", "shinmen@gmail.com", "123456"));
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
                req.getRequestDispatcher("/register.jsp").forward(req, resp);
                return;
            } else {
                User newUser = new User(loginString, emailString, paString);
                users.add(newUser);
                resp.sendRedirect("/login");
            }
        
            

        } else if (path.equals("/login")) {
            String loginString = req.getParameter("login");
            String paString = req.getParameter("password");
            boolean found = users.stream()
                .anyMatch(u -> u.getLogin().equals(loginString) && u.getPassword().equals(paString));

            if (found) {
                resp.sendRedirect("/tasks");
            } else {
                req.setAttribute("error", "Login ou mot de passe incorrect");
                req.getRequestDispatcher("/login.jsp").forward(req, resp);
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
