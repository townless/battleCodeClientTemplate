## BATTLE CODE TEMPLATE

Ce template est un client java permettant d'intéragir avec le serveur de jeu. 
Aucune logique de jeu est implémenté


### Principe du template

Ce template expose deux endpoints afin de pouvoir : 

- jouer une partie existante (Play Game)
- demarrer une partie d'entrainement (StartTrainning)

Le postman est disponible dans le dossier Postman


### Description des packages 

- Http : Objets reponse http + ClientHttp
- Model : objets java représentant une partie (Game, Round, Action, TeamState, ..)

- la logique de jeu est implémenté dans la classe Application.java 
La méthode **jouer** reprendre le workflow de jeu
La méthode  **definirCoup** est un exemple pour récupérer les coups / informations d'un round.

Le boolean :  
**boolean trainningMode = true;** permet de changer de mode de jeu. 
Si celui ci est à vrai, il permet de démarrer une partie d'entrainnement. 
Dans le cas ou le ce booléen est à false : le "bot" récupéra la partie la plus ancienne non terminée. 


### Configuration 
Modifier les propriétés dans le fichier application.Java avant de démarrer votre partie

    #public static final String ID_PLAYER = "";
    #public static final String TOKEN = "";


### Demarrage projet 

#### Prérequis : maven + JDK 18 installé

Effectuer un mvn clean install 

Effectuer un clic droit sur la classe principale "Application" afin de démarrer le programme java 



