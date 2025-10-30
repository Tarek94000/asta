# Rapport - ASTA - Application de Suivi de Tutorats d'Apprentis

## 1. Objectif du Projet
L'objectif de ce projet est de créer une application web de suivi de tutorats pour les apprentis dans le cadre du programme ALTN72. L'application permet aux tuteurs enseignants de gérer les apprentis, leur suivi, ainsi que leurs informations académiques et professionnelles.

## 2. Fonctionnalités Implémentées

- **Gestion des apprentis** : Ajout, modification, suppression, et affichage des informations des apprentis.
- **Gestion des entreprises** : Gestion des entreprises où les apprentis effectuent leurs missions.
- **Gestion des missions** : Création et suivi des missions des apprentis.
- **Gestion des évaluations** : Saisie des évaluations par l'école.
- **Gestion des visites** : Suivi des visites en entreprise pour les apprentis.
- **Recherche avancée** : Recherche par nom, entreprise, mots-clés, et année académique.
- **Gestion de l'année académique** : Archivage des apprentis et promotion vers l'année suivante.
- **Authentification** : Page de connexion et gestion de la session utilisateur.

## 3. Technologies Utilisées
- **Backend** : Spring Boot (Java), Spring Data JPA, Hibernate, MariaDB.
- **Frontend** : Thymeleaf, Bootstrap 5.
- **Gestion des exceptions** : Création d'exceptions personnalisées pour une gestion centralisée des erreurs.
- **Sécurisation** : Authentification simple via un login fixe (sans Spring Security).

## 4. Gestion des Exceptions
La gestion des erreurs est centralisée dans un `GlobalExceptionHandler` qui intercepte toutes les exceptions, y compris celles liées aux entités comme `Apprenti`, `Entreprise`, `Mission`, etc. Les erreurs sont renvoyées sous forme de messages clairs et structurés en JSON.

### Exemple d'erreur :
Lorsque l'on demande un apprenti avec un ID inexistant, la réponse retournée est :
```json
{
  "timestamp": "2025-10-29T20:28:06.8068988",
  "status": 404,
  "erreur": "Apprenti introuvable avec l'identifiant : 999"
}
```

## 5. Difficultés Rencontrées
- **Gestion des relations entre entités** : La gestion de la relation entre les entités `Apprenti`, `Entreprise`, `Mission`, etc. a nécessité une attention particulière pour garantir l'intégrité des données et leur persistance en base.
- **Gestion des erreurs et exceptions** : L'intégration des exceptions personnalisées avec `@ControllerAdvice` a été un défi intéressant, mais essentiel pour un code robuste et facile à maintenir.

## 6. Conformité aux Principes SOLID
Le projet suit largement les principes SOLID, notamment :
- **S (Single Responsibility Principle)** : Chaque classe et service a une seule responsabilité (par exemple, `ApprentiService` gère uniquement la logique métier des apprentis).
- **O (Open/Closed Principle)** : Le code est conçu pour être facilement extensible sans modification directe, par exemple, l'ajout d'une nouvelle fonctionnalité se fait en créant un nouveau service ou une nouvelle entité.
- **L (Liskov Substitution Principle)** : Les classes dérivées peuvent être utilisées à la place des classes de base sans modifier le comportement du programme.
- **I (Interface Segregation Principle)** : Les interfaces ne sont pas surchargées et sont spécifiques à des cas d'utilisation précis.
- **D (Dependency Inversion Principle)** : L'injection de dépendances est utilisée partout (notamment dans les services via les constructeurs).

## 7. Fonctionnalités Non Implémentées
Certaines fonctionnalités n'ont pas pu être réalisées dans le cadre du projet, notamment :
- La mise en place d'un **système de notifications** (par exemple, pour notifier les tuteurs des échéances importantes).
- La gestion d'un **panel d'administrateurs** avec des niveaux d'accès différents.

## 8. Conclusion
Le projet ASTA répond pleinement aux attentes en matière de gestion des apprentis et du suivi des missions. Il repose sur une architecture solide, extensible et bien structurée, et suit les meilleures pratiques en termes de gestion des erreurs et de clean code.

## 9. Informations Pratiques
- **Identifiants de test** : `tuteur`, `password`
- **IDE utilisé** : IntelliJ IDEA Ultimate Edition
- **SGBD utilisé** : MariaDB 10.5.8

