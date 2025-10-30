CREATE DATABASE IF NOT EXISTS Asta
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;

USE Asta;

DROP TABLE IF EXISTS Entreprise;

CREATE TABLE Entreprise (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    raison_sociale VARCHAR(255) NOT NULL,
    adresse VARCHAR(255),
    contact_email VARCHAR(255),
    UNIQUE(raison_sociale)
);


DROP TABLE IF EXISTS Apprenti;

CREATE TABLE Apprenti (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telephone VARCHAR(15),
    entreprise_raison_sociale VARCHAR(255),
    entreprise_adresse VARCHAR(255),
    major_m2_pro VARCHAR(100),
    annee_academique VARCHAR(10),
    programme VARCHAR(100),
    FOREIGN KEY (entreprise_raison_sociale) REFERENCES Entreprise(raison_sociale)
);
