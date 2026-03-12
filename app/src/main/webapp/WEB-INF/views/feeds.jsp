<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"  %>
<%@ page import="fr.miniature.models.Post"  %>
<%@ page import="fr.miniature.models.User"  %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Miniature</title>
</head>
<body>
    <header>
        <h1>MINIATURE</h1>
        <form method="post" action="/feeds">
            <button name="action" value="logout" type="submit">Se déconnecter</button>
        </form>
        <p>Bienvenue <%= ((User) session.getAttribute("user")).getLogin() %>.</p>
        <p>Abonnements <%= ((User) session.getAttribute("user")).getSubscriptions() %>.</p>
    </header>
    <main>
        <form method="get">
            <button name="action" value="recommendations" type="submit">Recommendations</button>
            <button name="action" value="subscriptions" type="submit">Abonnements</button>
        </form>

        <article>
            <form method="post" action="/feeds">
                <h2>Postez sur Miniature</h2>
                <textarea rows="5" cols="33">Ecrivez votre minia...</textarea>
                <br>
                <button type="submit" name="newPost">Postez votre minia</button>
            </form>
        </article>

        <% 
                List<Post> posts = (List<Post>) request.getAttribute("posts");
                for (Post post : posts) {
            %>        
                    <article>
                        <h3><%= post.getOwner() %></h3>
                        <p>le <%= post.getFormattedDate()%></p>
                        <p><%= post.getContent() %></p>
                        <form method="post">
                            <button type="submit" name="like" aria-pressed="<%= post.isLiked() ? " true" : "false" %>"><%= post.getLikes() %>❤️</button>
                            <button type="submit" name="comment">💬</button>
                        </form>
            
                    </article>
            <%
                } 
            %>


        
        
        


    </main>
</body>
</html>