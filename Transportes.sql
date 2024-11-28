CREATE DATABASE Transportes;

USE Transportes;

CREATE TABLE Trem (
    id INT PRIMARY KEY,
    quantidade_max_passageiros INT NOT NULL,
    velocidade_maxima NUMERIC(5,2),
    extensao_total NUMERIC(10,2) NOT NULL,
    empresa_dona VARCHAR(50)
);

CREATE TABLE Onibus (
    id INT PRIMARY KEY,
    quantidade_max_passageiros INT NOT NULL,
    velocidade_maxima NUMERIC(5,2),
    numero_de_pneus INT NOT NULL,
    marca_fabricante VARCHAR(50)
);

INSERT INTO Trem (id, quantidade_max_passageiros, velocidade_maxima, extensao_total, empresa_dona)
VALUES
    (1, 300, 250.50, 500.00, 'Empresa A'),
    (2, 400, 280.75, 800.25, 'Empresa B'),
    (3, 250, 220.30, 450.50, 'Empresa C'),
    (4, 350, 260.10, 600.00, 'Empresa D'),
    (5, 500, 300.00, 1000.00, 'Empresa E');

INSERT INTO Onibus (id, quantidade_max_passageiros, velocidade_maxima, numero_de_pneus, marca_fabricante)
VALUES
    (1, 50, 120.50, 6, 'Marca X'),
    (2, 45, 110.00, 6, 'Marca Y'),
    (3, 60, 130.75, 8, 'Marca Z'),
    (4, 55, 125.50, 6, 'Marca W'),
    (5, 40, 100.00, 4, 'Marca V');
