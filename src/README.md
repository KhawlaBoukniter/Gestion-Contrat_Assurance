# Projet de Gestion des Sinistres et Contrats

## Description
Ce projet est une application Java console permettant de gérer les clients, contrats et sinistres.  
Il offre des fonctionnalités CRUD sur les sinistres et contrats, ainsi que des calculs et filtres avancés grâce à la programmation Java moderne (Streams, Optional, Java Time API).

L'application est développée **sans gestionnaire de dépendances** (pas de Maven ou Gradle).

---

## Fonctionnalités

### Gestion des clients
- Ajouter un client
- Supprimer un client
- Rechercher un client par ID

### Gestion des contrats
- Ajouter un contrat
- Supprimer un contrat
- Lister tous les contrats
- Rechercher un contrat par ID
- Récupérer l'ID du client associé à un contrat

### Gestion des sinistres
- Ajouter un sinistre avec choix de contrat existant
- Supprimer un sinistre
- Rechercher un sinistre par ID
- Afficher les sinistres par contrat
- Afficher les sinistres par client
- Afficher les sinistres avant une date donnée
- Afficher les sinistres dont le coût est supérieur à un montant donné
- Afficher les sinistres triés par coût décroissant
- Calculer le coût total des sinistres d’un client

---

## Technologies utilisées
- JDBC pour la communication avec MySQL
- MySQL pour la base de données
- Streams et Optional pour le traitement des collections
- UUID pour la génération d'identifiants (ID tronqués avant le premier `-`)

---

## Structure du projet
````
src/
├── DAO/
│ ├── ClientDAO.java
│ ├── ConseillerDAO.java
│ ├── ContratDAO.java
│ ├── SinistreDAO.java
│ └── Database.java
├── enums/
│ ├── TypeContrat.java
│ └── TypeSinistre.java
├── models/
│ ├── Client.java
│ ├── Conseiller.java
│ ├── Contrat.java
│ ├── Person.java
│ └── Sinistre.java
├── services/
│ ├── ClientService.java
│ ├── ConseillerService.java
│ ├── ContratService.java
│ └── SinistreService.java
├── views/
│ ├── ClientView.java
│ ├── ConseillerView.java
│ ├── Contrat.java
│ ├── MainMenu.java
│ └── SinistreView.java
└── Main.java
````


---

## Base de données

### Table `clients`
- `id` VARCHAR(36) PRIMARY KEY
- `nom` VARCHAR(255)
- `prenom` VARCHAR(255)
- `email` VARCHAR(255)

### Table `contrats`
- `id` VARCHAR(36) PRIMARY KEY
- `type_contrat` VARCHAR(255)
- `date_debut` DATETIME
- `date_fin` DATETIME
- `client_id` VARCHAR(36) FOREIGN KEY REFERENCES clients(id)

### Table `sinistres`
- `id` VARCHAR(36) PRIMARY KEY
- `type_sinistre` VARCHAR(255)
- `date` DATETIME
- `cout` DOUBLE
- `description` TEXT
- `contrat_id` VARCHAR(36) FOREIGN KEY REFERENCES contrats(id)

---

## Installation et lancement

1. **Cloner le projet**
```bash
git clone https://github.com/KhawlaBoukniter/Gestion-Contrat_Assurance
````

2. **Configurer la base de données**

Modifier Database.java avec vos paramètres MySQL (url, utilisateur, mot de passe).

3. **Compiler les fichiers Java**
```bash
javac -d bin src/**/*.java
````

4. **Lancer l’application**
````bash
java -cp bin Main
````

## Remarques

* Tous les sinistres sont associés à un contrat existant.

* Les IDs sont générés automatiquement et tronqués avant le premier -.

* Les entrées de date doivent respecter le format yyyy-MM-ddTHH:mm.

* Les fonctionnalités utilisent les Streams API pour filtrer et trier les collections.