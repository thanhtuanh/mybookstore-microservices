# 📚 MyBookstore Microservices

**MyBookstore Microservices** ist eine erweiterte Version des Projekts [MyBookstore](https://github.com/thanhtuanh/mybookstore), das auf eine Microservice-Architektur umgestellt wurde. Ziel ist die Modularisierung und Skalierbarkeit der Anwendung auf Basis moderner Java- und Angular-Technologien.

---

## 🏗️ Architekturübersicht

Die Anwendung besteht aus folgenden Microservices:

- 🔐 `auth-service` – Benutzerverwaltung & Login mit JWT
- 📘 `book-service` – Buchverwaltung mit Suche, CRUD, Kategorien
- 🌐 `frontend` – Angular-Frontend (SPA)
- 🟢 `postgres-auth` – Datenbank für auth-service
- 🟢 `postgres-book` – Datenbank für book-service

Alle Services sind über Docker Compose orchestriert.

---

## 🚀 Starten des Projekts

### Voraussetzungen

- Docker + Docker Compose
- Java 21 (für lokale Tests)
- Node.js + Angular CLI (wenn Frontend lokal getestet wird)

### Lokaler Start (mit Docker)

```bash
docker-compose up --build
```

Frontend ist erreichbar unter [http://localhost:4200](http://localhost:4200)

---

## 🛆 Technologien

- **Backend:** Spring Boot 3, Spring Security 6, JWT, JPA (Hibernate)
- **Frontend:** Angular 17, TypeScript, Bootstrap
- **Datenbanken:** PostgreSQL
- **Containerisierung:** Docker, Docker Compose
- **API-Dokumentation:** Swagger / springdoc-openapi

---

## ✅ Nächste Schritte (in Planung)

- ✅ JWT-basierte Authentifizierung
- 🔄 Unit & Integration Tests (JUnit + Testcontainers)
- ⚙️ CI/CD mit GitHub Actions oder GitLab CI
- ☁️ Deployment in der Cloud (z. B. Okteto, Heroku oder Kubernetes)
- 📊 Healthchecks & Logging

---

## 🧪 Test-Zugang

Benutzer für ersten Login:

```
Username: admin
Passwort: admin
```

---

## 📂 Projektstruktur

```
mybookstore-microservices/
├── auth-service/         # Login & Registrierung
├── book-service/         # Buchmanagement
├── frontend/             # Angular-App
├── docker-compose.yml    # Startet alle Services
├── init-db.sql           # Initialdaten für PostgreSQL
└── README.md
```

---

## 🧑‍💻 Autor

> Erstellt von **Thanh** – Senior Java Fullstack Entwickler  
> Kontakt bei Fragen: [E-Mail / LinkedIn / GitHub-Profil einfügen]

---

## 📝 Lizenz

MIT License – frei zur Verwendung & Erweiterung
