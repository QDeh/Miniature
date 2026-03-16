package fr.miniature.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.NonNull;

import fr.miniature.models.Post;
import fr.miniature.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet({"/feeds", "/details/*"})
public class FeedController extends HttpServlet {

    private List<@NonNull Post> posts = new ArrayList<>();

    public void init() {
        posts.add(new Post(new User("suer", "user@gmail.com", ""), "pcoucou", null));
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
            
            String path = req.getServletPath();
            if (path.equals("/feeds")) {
                postsList = postsList.stream()
                    .sorted((a, b) -> b.getcreatedAt().compareTo(a.getcreatedAt()))
                    .collect(Collectors.toList());

                postsList = postsList.stream()
                    .filter(p -> p.getParent() == null)
                    .collect(Collectors.toList());
                req.setAttribute("posts", postsList);
                req.getRequestDispatcher("/WEB-INF/views/feeds.jsp").forward(req, resp);

            } else if (path.equals("/details")) {
                int id = Integer.parseInt(req.getPathInfo().substring(1));

                Post post = posts.stream()
                    .filter(p -> p.getId() == id)
                    .findFirst().orElse(null);
                if (post == null) {
                    resp.sendError(404, "Post introuvable");
                }

                List<Post> comments = posts.stream()
                    .filter(p -> p.getParent() != null && p.getParent().equals(post))
                    .collect(Collectors.toList());

                req.setAttribute("post", post);
                req.setAttribute("posts", comments);
                req.getRequestDispatcher("/WEB-INF/views/details.jsp").forward(req, resp);

            }
            
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        User connectedUser = (User) session.getAttribute("user");
        String action = req.getParameter("action");

        String content = req.getParameter("content");
        String subscribe = req.getParameter("subscribe");
        String like = req.getParameter("like");
        String comment = req.getParameter("comment");

        String postId = req.getParameter("postId");
        String postComment = req.getParameter("postComment");
        
        
        if ("logout".equals(action)){
            if (session != null) {
                session.invalidate();
                resp.sendRedirect("/login");
            }
            return;   
        }

        String path = req.getServletPath();
        if (path.equals("/feeds")) {
            for (Post post : posts){
                String id = String.valueOf(post.getId());

                if (id.equals(subscribe)) {
                    User user = post.getOwner();
                    connectedUser.updateSubscriptions(user);
                    resp.sendRedirect("/feeds");
                    return;
                }

                if (id.equals(like)){
                    post.updateLikes(connectedUser);
                    resp.sendRedirect("/feeds");
                    return;
                }

                if (id.equals(comment)){
                    resp.sendRedirect("/details/" + id);
                    return;
                }
                
            }

            if (content != null && !content.isEmpty()) {
                Post post = new Post(connectedUser, content, null);
                posts.add(post);
                resp.sendRedirect("/feeds");
                return;
            } else {
                resp.sendRedirect("/feeds");
                return;
            }

        } else if (path.equals("/details")) {
            System.out.println("postID : " + postId);
            for (Post post : posts) {
                String id = String.valueOf(post.getId());
                if (like != null && id.equals(like)){
                    post.updateLikes(connectedUser);
                    resp.sendRedirect("/details/" + postId);
                    return;
                }
                if (postComment != null && !postComment.isEmpty()) {
                    System.out.println(id);
                    if (id.equals(postId)) {
                        Post newComment = new Post(connectedUser, postComment, post);
                        posts.add(newComment);
                        resp.sendRedirect("/details/" + id);
                        return;
                    }   
                }
            }
        }

    }
}
