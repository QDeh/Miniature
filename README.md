# Miniature

## Tables des Matières

[[#Description]]
[[#Pré-requis]]
[[#Installation et lancement]]
[[#Structure du projet]]

## Description

Miniature est un mini réseau social en architecture MVC qui donne de la place aux petites choses, il permet d'intéragir avec tous les autres Miniaturists (nous sommes déjà 2 !)

Partagez, likez, suivez, commentez.
## Pré-requis

- Installer gradle en version 9.0.4 ou supérieure
- Installer JDK 21 ou supérieur
## Installation et lancement

Pour installer miniature, veuillez cloner le repository :

```bash
git clone https://github.com/QDeh/Miniature
```

Une fois cette étape réalisée, ouvrez un terminal dans le dossier "Miniature" crée puis lancez la commande

```bash
./gradlew run
```

## Structure du projet

```
app
|──src/main
|       |──java/fr/miniature
|       |              |──controllers
|       |              |    |──AuthController.java #crée et vérifie les comptes
|       |              |    |──FeedController.java #gère les posts, likes,...
|       |              |
|       |              |--models
|       |              |    |──Post.java #classe post, cd : documentation.md
|       |              |    |──User.java #classe user, cf : documentation.md
|       |              |
|       |              |──App.java #serveur de l'application (dossiers, port...)
|       |──webapp
|       |    |──css
|       |    |   |──miniature.css #fichier css du projet
|       |    |   |
|       |    |──images
|       |    |   |──miniature-banner.png #l'image dont nous avons besoin
|       |    |   |
|       |    |──WEB-INF/views
|       |    |   |──details.jsp #page pour afficher les détails d'un post
|       |    |   |──feeds.jsp #page pour afficher les feeds (découverte & abo)
|       |    |   |──login.jsp #page de connexion
|       |    |   |──register.jsp #page d'inscription
|       |    |   |
|       |    |──index.html #page d'accueil du site
|       |    |
build.gradle.kts #gére l'installation des dépendances (tomcat et java 21)    
```
