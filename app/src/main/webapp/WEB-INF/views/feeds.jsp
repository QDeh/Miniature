<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List"  %>
<%@ page import="fr.miniature.models.Post"  %>

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
    </header>
    <main>
        <form method="get">
            <button name="action" value="recommendations" type="submit">Recommendations</button>
            <button name="action" value="subscriptions" type="submit">Abonnements</button>
        </form>

        <% 
            List<Post> posts = (List<Post>) request.getAttribute("posts");


            for (Post post : posts) {
        %>        
                <article>
                    <h3><%= post.getOwner() %></h3>
                    <p><%= post.getContent() %></p>
                    <p><%= post.getDate() %></p>
                    <form method="post">
                        <button type="submit" name="like"><%= post.getLikes() %></button>
                        <button type="submit" name="comment">Commenter</button>
                    </form>
        
                </article>
        <%
            }
        %>


        
        
        


    </main>
</body>
</html>