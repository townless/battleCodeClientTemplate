## BATTLE CODE TEMPLATE

Ce template est un client spring boot permettant d'intéragir avec le serveur de jeu. 
Aucune logique de jeu est implémenté


### Principe du template

Ce template expose deux endpoints afin de pouvoir : 

- jouer une partie existante (Play Game)
- demarrer une partie d'entrainement (StartTrainning)

Le postman est disponible à la racine du projet : battleCodeTemplate.postman_collection


### Description des packages 

- bot : contient toutes les classes permettant d'implémenter votre robot qui va jouer lors de la battleCode. YourBot.java contient la méthode permettant de déterminer la le coup qui sera joué 
- controller : classe endpoints + exceptionHandlers
- Http : Objets reponse http 
- Model : objets java représentant une partie (Game, Round, Action, TeamState, ..)
- Service / WebClientService : c'est le client web qui permet d'intéragir avec le serveur de jeu en effectuant des appels http

### Configuration 
Modifier les propriétés dans le fichier application properties (dossier ressources) avec vos infos de connexion. 

battlecode.game.server.url=

your.api.key=

your.api.id=


### Demarrage projet 

#### Prérequis : maven + JDK 18 installé

Effectuer un mvn clean install 

Effectuer un clic droit sur la classe principale "BattleCodeTemplateApp" afin de démarrer le WS


