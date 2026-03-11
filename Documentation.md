
# Documentation

Nous avons pris un peu de temps pour réfléchir à la conception du projet, voici les diagrammes de cas d'utilisation et de classes.

## Diagramme de cas d'utilisation
![use-case-diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/QDeh/Miniature/main/diagramme_cas_utilisation.iuml)

## Diagramme de classes

```mermaid
classDiagram

User --o User : abonné à (*)
User --o Post : crée (*)
Post --o Post : parent (1)
class User{
+int id
+String username
+String mail
+String password
}

class Post{
+int id
+String content
+int likes
}
```
