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
                <textarea rows="5" cols="33" name="content" placeholder="Ecrivez votre minia..."></textarea>
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

                        <form method="post" action="/feeds">
                            <button type="submit" name="subscription" value="<%= post.getOwner().getLogin() %>"><%= ((User) session.getAttribute("user")).isSubscribed(post.getOwner()) ? "Se désabonner" : "S'abonner" %></button>
                        </form>
                        <p>le <%= post.getFormattedDate()%></p>
                        <p><%= post.getContent() %></p>
                        <form method="post" action="/feeds">
                            <button type="submit" name="like" value="<%= post.getId() %>"><%= post.getLikes() %>❤️</button>
                            <!-- <button type="submit" name="comment" value="<%= post.getId() %>">💬</button> -->
                        </form>
                        <a href="/details/<%= post.getId() %>">💬</a>
            
                    </article>
            <%
                } 
            %>


        
        
        


    </main>
</body>
</html>