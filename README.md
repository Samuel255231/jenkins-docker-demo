# Jenkins Docker Demo

Projet de démonstration CI/CD avec Spring Boot, Maven, Docker et Jenkins.

## Technologies

- Java 17
- Spring Boot
- Maven
- Docker
- Jenkins
- GitHub

---

## Structure du projet

```
jenkins-docker-demo
│── src
│── Dockerfile
│── Jenkinsfile
│── pom.xml
│── README.md
```

---

## Compilation

```bash
mvnw.cmd clean package
```

---

## Exécuter l'application

```bash
mvnw.cmd spring-boot:run
```

Accès :

```
http://localhost:8081
```

Health Check :

```
http://localhost:8081/health
```

---

## Construire l'image Docker

```bash
docker build -t jenkins-docker-demo:1.0 .
```

---

## Exécuter le conteneur

```bash
docker run -p 8081:8081 jenkins-docker-demo:1.0
```

---

## Pipeline Jenkins

La pipeline exécute automatiquement :

1. Checkout depuis GitHub
2. Build Maven
3. Tests
4. Build Docker
5. Push Docker Hub

---

## Auteur

Projet réalisé dans le cadre du TP Jenkins + Docker.