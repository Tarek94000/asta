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

-- Insérer des entreprises
INSERT INTO Entreprise (raison_sociale, adresse, contact_email) 
VALUES 
('TechCorp', '10 rue de Paris, Paris', 'contact@techcorp.com'),
('DevSolutions', '45 avenue des Champs, Lyon', 'contact@devsolutions.com');

-- Insérer des apprentis
INSERT INTO Apprenti (nom, prenom, email, telephone, entreprise_raison_sociale, entreprise_adresse, major_m2_pro, annee_academique, programme)
VALUES
('Dupont', 'Jean', 'jean.dupont@email.com', '0123456789', 'TechCorp', '10 rue de Paris, Paris', 'Transformation Digitale', '2023-2024', 'M2 PRO'),
('Martin', 'Lucie', 'lucie.martin@email.com', '0987654321', 'DevSolutions', '45 avenue des Champs, Lyon', 'Data Science', '2023-2024', 'M2 PRO');

