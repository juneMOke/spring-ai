# spring-ai-project

Projet Spring Boot démontrant les bases de l'utilisation de Spring AI.
Ce README donne les instructions essentielles pour installer, lancer et contribuer au projet.

## Aperçu

- Framework : Spring Boot (Java)
- Objectif : fournir une API backend qui intégrera le fournisseur d'IA OpenAI
- Packaging : JAR (Maven/Gradle) — possibilité de conteneuriser avec Docker

## Prérequis

* Java 21+
* MAVEN
* Docker
  Le projet utilise Docker pour la base de données vectorielle. Assurez-vous de disposer d'un environnement Docker fonctionnel.
* Une clé API pour le fournisseur d'openAI
    Vous aurez besoin de créer une clé API sur le site https://platform.openai.com/account/api-keys. 
    Vous pouvez récharger 5€ sur votre compte pour tester l'API.

## Variables d'environnement importantes

Dans le fichier application.properties, vous pourrez définir la variable d'environnement suivante :

- OPENAI_API_KEY: utiliser la clé de l'API OpenAI que vous avez créée.


## Installation et exécution

Avec Maven :
./mvnw clean package
./mvnw spring-boot:run
ou
java -jar target/*.jar


## Structure du dépôt

- src/main/java/... — code applicatif
- src/main/resources — configurations (application.yml / application.properties)
- src/test — tests unitaires/intégration
- Dockerfile — conteneurisation (si présent)


## Développement & Contribution

- Créez une branche descriptive pour chaque fonctionnalité/fix.
- Ouvrez une PR avec description et tests.
- Respectez les conventions de code du projet.

## Dépannage

- Problème de build : vérifier la version Java et les variables d'environnement.
- Erreur liée à l'API d'IA : vérifier la validité de la clé et les quotas du fournisseur.
- Consulter les logs (STDOUT / fichiers) pour plus de détails.

## Licence & Contact

- Licence : vérifier le fichier LICENSE à la racine du dépôt.
- Contact : ajouter ici l'email ou lien vers l'espace de collaboration interne.


