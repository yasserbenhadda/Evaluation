🧩 Java Hibernate Projects – Evaluation

Ce dépôt regroupe trois exercices Java mettant en œuvre Hibernate ORM dans différents contextes métiers : gestion de stock, gestion de projets et gestion de l’état civil.

📋 Aperçu des Exercices

Exercice 1 : Gestion de Stock

Exercice 2 : Gestion de Projets

Exercice 3 : Gestion de l’État Civil

🛠️ Technologies Utilisées

Langage : Java 11 (Exercices 1 & 2), Java 8 (Exercice 3)

ORM : Hibernate 5.6.x

Base de données : MySQL 8.0

Build Tool : Maven

IDE Recommandé : IntelliJ IDEA

⚙️ Prérequis

Java JDK : 21

Maven : version 3.6+

MySQL : version 8.0+

Git : pour cloner le dépôt

🗄️ Configuration de la Base de Données

Identifiants MySQL communs à tous les exercices :

Utilisateur : root

Mot de passe : 123456789

Port : 3306

Bases utilisées :

gestion_stock (Ex. 1)

projet_db (Ex. 2)

etatcivil (Ex. 3)

🏪 Exercice 1 – Gestion de Stock

Objectif : Créer une application pour gérer le stock d’une boutique d’informatique.

Modèle de Domaine

Categorie, Produit, Commande, LigneCommande

Relations :

Categorie (1) ─< Produit (N)
Produit (1) ─< LigneCommande (N)
Commande (1) ─< LigneCommande (N)

Fonctionnalités

CRUD complet pour toutes les entités

Liste des produits par catégorie

Requêtes par prix ou par période

Affichage des détails de commande

Lancement
cd exercice1
mvn clean compile exec:java

🏗️ Exercice 2 – Gestion de Projets

Objectif : Suivre le temps passé et les coûts des projets d’une société de conseil.

Modèle de Domaine

Employe, Projet, Tache, EmployeTache

Relations :

Employe (1) ─< Projet (N)
Projet (1) ─< Tache (N)
Employe (1) ─< EmployeTache (N)
Tache (1) ─< EmployeTache (N)

Fonctionnalités

CRUD complet

Tâches assignées à un employé

Projets dirigés par un chef

Requêtes par prix (>1000 DH) ou par date

Lancement
cd exercice2
mvn clean compile exec:java

👥 Exercice 3 – Gestion de l’État Civil

Objectif : Développer un système de gestion des citoyens et mariages d’une province.

Modèle de Domaine

Homme, Femme, Mariage

Relations :

Homme (1) ─< Mariage (N)
Femme (1) ─< Mariage (N)

Fonctionnalités

CRUD générique (pattern DAO)

Recherche de la femme la plus âgée

Comptage d’enfants ou de mariages selon des critères

Requêtes avancées via JPQL et Named Queries

Lancement
cd exercice3
mvn clean compile exec:java -Dexec.mainClass="ma.projet.main.TestProgram"

🚀 Guide de Démarrage Rapide

Cloner le dépôt

git clone https://github.com/Satsujii/Evaluation.git
cd Evaluation


Configurer MySQL

Serveur sur localhost:3306

Identifiants root / 123456789

Les bases seront créées automatiquement.

Lancer un exercice

cd exercice1  # ou exercice2, exercice3
mvn clean compile exec:java

🧩 Concepts Clés Illustrés
Exercice	Concepts principaux
1	Annotations Hibernate, HQL, Named Queries, config programmatique
2	Config XML, relations complexes, requêtes paramétrées
3	JPA avec Hibernate, DAO générique, JPQL & requêtes natives
📚 Objectifs Pédagogiques

Ce projet permet de maîtriser :

Le mapping objet-relationnel avec Hibernate

L’utilisation de la JPA

Le design relationnel entre entités

Le pattern DAO / Service Layer

Les langages de requêtes : HQL, JPQL, SQL natif

La gestion des transactions

Les configurations Hibernate (code, XML, JPA)

👤 Auteur

yasserbenhadda
GitHub : @yasserbenhadda

## screenshots 

<img width="1919" height="1079" alt="Evaluation-exercice1" src="https://github.com/user-attachments/assets/b8cf6e7e-cc3f-4fa7-8a4b-cf84678dd189" />

<img width="1919" height="1079" alt="Evaluation-exercice2" src="https://github.com/user-attachments/assets/bc1363f3-71a4-4746-805f-2754eda68a65" />

<img width="1914" height="1079" alt="Evaluation-exercice3" src="https://github.com/user-attachments/assets/5c14d5c4-154b-427b-8138-7291c579a0e4" />



