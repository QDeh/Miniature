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



        <% 
                Post post = (Post) request.getAttribute("post");
 
            %>        
                    <article>
                        <h3><%= post.getOwner() %></h3>
                        <p>le <%= post.getFormattedDate()%></p>
                        <p><%= post.getContent() %></p>
                        <form method="post" action="/details/<%=post.getId() %>">
                            <button type="submit" name="like" value="<%= post.getId() %>"><%= post.getLikes() %>❤️</button>
                        </form>
            
                    </article>
            <%
            %>
        <article>
            <form method="post" action="/details">
                <h2>Ecrire un commentaire</h2>
                <textarea rows="5" cols="33" name="content" placehoalder="Ecrivez votre commentaire..."></textarea>
                <br>
                <button type="submit" name="newComment">Commentez</button>
            </form>
        </article>

        
        
        


    </main>
</body>
</html>