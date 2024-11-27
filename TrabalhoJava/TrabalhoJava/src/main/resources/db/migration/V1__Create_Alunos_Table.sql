CREATE DATABASE IF NOT EXISTS educacional;
USE educacional;

CREATE TABLE alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    matricula VARCHAR(20) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL
);
