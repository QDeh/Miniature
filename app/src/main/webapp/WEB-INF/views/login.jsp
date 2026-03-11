<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
</head>
<body>
    <h1>Connexion</h1>
    <main>
        <form method="post" action="/login">
            <input type="text" name="login" placeholder="Nom d'utilisateur">
            <input type="password" name="password" placeholder="Mot de passe">
            <input type="submit" value="Se connecter">
        </form>

    </main>


</body>
</html>