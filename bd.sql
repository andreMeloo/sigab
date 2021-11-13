DROP TABLE Diario;
DROP TABLE Turma;
DROP TABLE Disciplina;
DROP TABLE Aluno;
DROP TABLE Professor;
DROP TABLE Endereco;
DROP TABLE Usuario;

CREATE TABLE Usuario (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(128) NOT NULL,
	nivel VARCHAR(32) NOT NULL,
	username VARCHAR(128) NOT NULL,
	senha VARCHAR(128) NOT NULL
);
 
INSERT INTO Usuario (nome, nivel, username, senha)
VALUES
	('Paulo', 'ALUNO', 'paulin106', '123456'),
	('Pedro', 'ALUNO', 'ehopedrin', 'abcdef'),
	('Lucas', 'ALUNO', 'lukinha32', '015jut'),
    ('João', 'PROFESSOR', 'jao123', '000000'),
	('Cesar', 'PROFESSOR', 'cesarminoti', '145asb'),
	('Irineu', 'PROFESSOR', 'irineu', 'vcnsabenemeu');

CREATE TABLE Endereco (
	id SERIAL PRIMARY KEY,
	cidade VARCHAR(128),
	uf VARCHAR(2),
	endereco VARCHAR(128)
);
 
INSERT INTO Endereco (cidade, uf, endereco)
VALUES
	('Mossoró', 'RN', 'Rua das Véa, 123'),
	('Jucurutu', 'RN', 'Rua Acolá, 965'),
	('Fortaleza', 'CE', 'Rua Tal, 33'),
    ('João Pessoa', 'PB', 'Rua do Amigo, 37'),
	('São Paulo', 'SP', 'Rua Cebolinha, 954'),
	('Macau', 'RN', 'Rua Beco, 66');

CREATE TABLE Aluno (
    id BIGINT PRIMARY KEY,
	matricula VARCHAR(10) NOT NULL,
	endereco_id BIGINT REFERENCES Endereco(id) NOT NULL,
	FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);
 
INSERT INTO Aluno (id, matricula, endereco_id)
VALUES
	(1, '2020010207', 1),
	(2, '2020010208', 2),
	(3, '2020010209', 3);



CREATE TABLE Professor (
	id BIGINT PRIMARY KEY,
	cpf VARCHAR(11) NOT NULL,
	endereco_id BIGINT REFERENCES Endereco(id) NOT NULL,
	FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);
 
INSERT INTO Professor (id, cpf, endereco_id)
VALUES
	(4, '12345678912', 4),
	(5, '34592839242', 5),
	(6, '92384743829', 6);

CREATE TABLE Disciplina (
    id SERIAL PRIMARY KEY,
	codigo BIGINT NOT NULL,
	nome VARCHAR(128) NOT NULL
);
 
INSERT INTO Disciplina (codigo, nome)
VALUES
    (123, 'Matematica'),
    (456, 'Filosofia'),
    (789, 'Historia');


CREATE TABLE Turma (
	id SERIAL PRIMARY KEY,
	disciplina_id BIGINT REFERENCES Disciplina(id) ON UPDATE CASCADE NOT NULL,
	professor_id INT REFERENCES Professor(id) ON UPDATE CASCADE NOT NULL,
	codigo VARCHAR(3) NOT NULL,
	horario VARCHAR(5) NOT NULL,
	local VARCHAR(128) NOT NULL,
	aberta BOOLEAN DEFAULT true,
	created_at DATE DEFAULT now()
);
 
INSERT INTO Turma (disciplina_id, professor_id, codigo, horario, local, aberta)
VALUES
	(1, 4, 'T01', '24M12', 'remoto', true),
	(2, 5, 'T02', '35T45', 'lab01', true),
	(3, 6, 'T03', '12N35', 'remoto', true);

CREATE TABLE Diario (
	nota1 NUMERIC(3,1),
	nota2 NUMERIC(3,1),
	nota3 NUMERIC(3,1),
	quarta_prova NUMERIC(3,1),
	frequencia NUMERIC(3,0),
	aluno_id BIGINT REFERENCES Aluno(id) ON DELETE CASCADE NOT NULL,
	turma_id BIGINT REFERENCES Turma(id) ON DELETE CASCADE NOT NULL,
    created_at DATE DEFAULT now(),
	PRIMARY KEY(aluno_id, turma_id)
);
 
INSERT INTO Diario (aluno_id, turma_id, nota1, nota2, nota3, quarta_prova, frequencia)
VALUES
	(1, 1, 9.5, 6.7, 5.3, NULL, 79),
	(2, 1, 4.8, 8.4, 10.0, NULL, 100),
	(3, 2, 3.6, 2.5, 4.0, 9.8, 95);

	SELECT * FROM aluno;
	SELECT * FROM diario;
	SELECT * FROM disciplina;
	SELECT * FROM endereco;
	SELECT * FROM professor
	SELECT * FROM turma;
	SELECT * FROM usuario;
