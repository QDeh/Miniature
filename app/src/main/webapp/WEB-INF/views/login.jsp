<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="/css/miniature.css">
</head>
<body>
    <a href="/"><h1>MINIATURE</h1></a>
    <main>
        <h2>Connexion</h2>
        <form method="post" action="/login">
            
            <input type="text" name="login" placeholder="Nom d'utilisateur">
            <input type="password" name="password" placeholder="Mot de passe">
            <input type="submit" value="Se connecter">
            
        </form>
        <a href="/index.jsp"><input type="submit" value="Retour à l'acceuil"></a>
    </main>
</body>
</html>