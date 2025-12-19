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
  Le projet utilise Docker pour la base de données vectorielle. Assurez-vous de disposer d'un environnement Docker
  fonctionnel.
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

- src/main/java/ — code applicatif
- src/main/resources — configurations (application.properties)
- Dockerfile — conteneurisation

## Endpoints API des tests

1. Utilisation de l'assistant RH basique (Basic HR Assistant) qui permet juste de communiquer avec le modèle LLM. (
   BasicAssistantController.java)

- POST http://localhost:8080/api/v1/basic/questions?question=combien des jours de congé ai-je droit ?

- POST http://localhost:8080/api/v1/basic/questions?question=cites-moi 5 grandes villes de la France ?

2. Utilisation de l'assistant RH avec un prompt qui restreint le LLM à répondre uniquement sur des sujets RH (Prompt HR
   Assistant) (BasicAssistantWithPrompt.java)

- POST http://localhost:8080/api/v1/prompt/questions?question=combien des jours de congé ai-je droit ?
- POST http://localhost:8080/api/v1/prompt/questions?question=cites-moi 5 grandes villes de la France ?

3. Utilisation de l'assistant RH avec RAG (RAG HR Assistant) (RagAssistantController.java)


- POST http://localhost:8080/api/v1/rag/questions?question=Puis-je poser des RTT pendant ma période d’essai ?

4. Utilisation de l'assistant RH avec des tools qui permettent d'exécuter des actions (EmployeeLeaveController.java)

- POST http://localhost:8080/users/1/leaves?question=combien des jours de congé ai-je déjà posé?
- POST http://localhost:8080/users/1/leaves?question=Peux-tu me rappeler la date de ce jour de congé?
- POST http://localhost:8080/users/1/leaves?question=Peux-tu me créer un congé pour le 18 décembre 2025?

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