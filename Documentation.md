
# Documentation

Nous avons pris un peu de temps pour réfléchir à la conception du projet, voici les diagrammes de cas d'utilisation et de classes.

## Diagramme de cas d'utilisation

 ![use-case-diagram](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/QDeh/Miniature/blob/main/diagramme_cas_utilisation.iuml)

```plantuml
@startuml
left to right direction

together{
actor "Visiteur" as Visiteur
actor "Utilisateur" as Utilisateur
}

rectangle "Miniature"{
usecase "S'identifier" as Identifier
usecase "Créer un compte" as CreerCompte
usecase "Naviguer dans les recommendations" as NaviguerR
usecase "Naviguer dans les abonnements" as NaviguerA
usecase "Intéragir" as Interagir
usecase "Poster un message" as Poster
usecase "Liker un post" as Liker
usecase "Commenter un post" as Commenter
}

Utilisateur -left-|> Visiteur
Visiteur --- Identifier
Visiteur --- NaviguerR
Identifier <-.- CreerCompte : <<extends>>
Utilisateur --- NaviguerA
Utilisateur --- Interagir
Interagir <-.- Poster : <<extends>>
Interagir <-.- Liker : <<extends>>
Interagir <-.- Commenter : <<extends>>

@enduml
```

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
