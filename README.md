# Jenkins Docker Demo

Projet de démonstration CI/CD avec **Spring Boot**, **Maven**, **Docker**, **Jenkins** et **GitHub**.

L'objectif est de mettre en place une chaîne complète d'intégration et de déploiement continu.

```
Développeur
      |
      ↓
    GitHub
      |
      ↓
 Jenkins Pipeline
      |
      ↓
 Build Maven
      |
      ↓
 Tests automatiques
      |
      ↓
 Création Image Docker
      |
      ↓
 Push Docker Hub
      |
      ↓
 Déploiement du conteneur
```

---

# 1. Technologies utilisées

- Java 17
- Spring Boot
- Maven
- Docker
- Jenkins
- GitHub

---

# 2. Structure du projet

```
jenkins-docker-demo
│
├── src
│
├── Dockerfile
│
├── Jenkinsfile
│
├── pom.xml
│
├── README.md
│
└── .gitignore
```

---

# 3. Prérequis

Installer :

- Java JDK 17
- Maven
- Docker Desktop
- Jenkins
- Git
- Compte GitHub
- Compte Docker Hub

Vérification Java :

```bash
java -version
```

Résultat attendu :

```
java version "17"
```

Vérification Docker :

```bash
docker --version
```

Vérification Git :

```bash
git --version
```

---

# 4. Récupération du projet

Cloner le dépôt GitHub :

```bash
git clone https://github.com/Samuel255231/jenkins-docker-demo.git
```

Entrer dans le projet :

```bash
cd jenkins-docker-demo
```

---

# 5. Lancement local avec Maven

## Compilation

```bash
mvnw.cmd clean package
```

Cette commande :

- compile le projet
- exécute les tests
- génère le fichier `.jar`

Résultat :

```
BUILD SUCCESS
```

---

## Démarrer Spring Boot

```bash
mvnw.cmd spring-boot:run
```

Application disponible :

```
http://localhost:8081
```

Test Health Check :

```
http://localhost:8081/health
```

---

# 6. Dockerisation de l'application

Le projet contient un fichier :

```
Dockerfile
```

Contenu :

```dockerfile
FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]
```

---

## Construire l'image Docker

```bash
docker build -t jenkins-docker-demo:1.0 .
```

Vérifier :

```bash
docker images
```

---

## Lancer le conteneur

```bash
docker run -p 8081:8081 jenkins-docker-demo:1.0
```

Accès :

```
http://localhost:8081
```

---

## Gestion des conteneurs

Voir les conteneurs actifs :

```bash
docker ps
```

Arrêter un conteneur :

```bash
docker stop ID_CONTAINER
```

Supprimer un conteneur :

```bash
docker rm ID_CONTAINER
```

---

# 7. Publication Docker Hub

Connexion :

```bash
docker login
```

Créer l'image Docker Hub :

```bash
docker build -t samuel643/jenkins-docker-demo:latest .
```

Envoyer l'image :

```bash
docker push samuel643/jenkins-docker-demo:latest
```

Télécharger depuis Docker Hub :

```bash
docker pull samuel643/jenkins-docker-demo:latest
```

Lancer l'image distante :

```bash
docker run -p 8081:8081 samuel643/jenkins-docker-demo:latest
```

---

# 8. Configuration Jenkins

## Plugins nécessaires

Installer :

- Git Plugin
- Maven Integration Plugin
- Pipeline Plugin
- Credentials Plugin
- Docker Pipeline Plugin
- Email Extension Plugin


---

# 9. Configuration des outils Jenkins

Dans Jenkins :

```
Manage Jenkins
        |
        ↓
Global Tool Configuration
```

Configurer :

## JDK

Nom :

```
Java17
```

Version :

```
17
```

---

## Maven

Nom :

```
Maven-3.9.16
```

---

# 10. Credentials Jenkins

Ajouter Docker Hub :

```
Manage Jenkins
        |
        ↓
Credentials
        |
        ↓
Global
        |
        ↓
Add Credentials
```

Type :

```
Username with password
```

ID :

```
dockerhub
```

Ce credential est utilisé dans le fichier :

```
Jenkinsfile
```

---

# 11. Pipeline Jenkins

Créer un nouveau projet :

```
New Item
    |
    ↓
Pipeline
```

Choisir :

```
Pipeline script from SCM
```

SCM :

```
Git
```

Repository :

```
https://github.com/Samuel255231/jenkins-docker-demo.git
```

Jenkins récupère automatiquement :

```
Jenkinsfile
```

---

# 12. Fonctionnement du Jenkinsfile

La pipeline réalise :

```
1. Checkout GitHub

        ↓

2. Build Maven

        ↓

3. Tests Maven

        ↓

4. Build Image Docker

        ↓

5. Push Docker Hub
```

---

# 13. Exécution de la pipeline

Lancer :

```
Build Now
```

Résultat attendu :

```
Pipeline exécutée avec succès

Finished: SUCCESS
```

---

# 14. Webhook GitHub + Jenkins

Objectif :

Déclencher automatiquement Jenkins après un `git push`.

Architecture :

```
Modification du code

        ↓

git push

        ↓

GitHub Webhook

        ↓

Jenkins

        ↓

Pipeline automatique
```

---

## Configuration Jenkins

Dans le projet Jenkins :

```
Configure

↓

Build Triggers

↓

GitHub hook trigger for GITScm polling
```

---

## Configuration GitHub

Dans le dépôt :

```
Settings

↓

Webhooks

↓

Add webhook
```

URL :

```
https://adresse-ngrok/github-webhook/
```

Type :

```
application/json
```

Événement :

```
Just the push event
```

---

# 15. Notifications Email

Objectif :

Recevoir un email lorsqu'une pipeline échoue.

Configuration :

```
Manage Jenkins

↓

System

↓

SMTP Configuration
```

Fonctionnement :

```
Pipeline FAILED

        ↓

Email envoyé automatiquement
```

---

# 16. Test final CI/CD

Modifier le code :

```bash
git add .
```

Créer un commit :

```bash
git commit -m "Modification test "
```

Envoyer :

```bash
git push
```

Résultat attendu :

```
GitHub

 ↓

Webhook

 ↓

Jenkins

 ↓

Build Maven

 ↓

Tests

 ↓

Docker Build

 ↓

Docker Hub
```

---

# 17. Résultat final du projet

Le projet permet :

✅ Compilation automatique  
✅ Tests automatiques  
✅ Création image Docker  
✅ Publication Docker Hub  
✅ Pipeline Jenkins complète  
✅ Déclenchement automatique par Webhook  
✅ Notification email en cas d'échec  

---