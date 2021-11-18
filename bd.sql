DROP VIEW boletim;
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
	('Aluno1', 'ALUNO', 'Aluno1', '123456'),
	('Aluno2', 'ALUNO', 'Aluno2', '123456'),
	('Aluno3', 'ALUNO', 'Aluno3', '123456'),
	('Aluno4', 'ALUNO', 'Aluno4', '123456'),
	('Aluno5', 'ALUNO', 'Aluno5', '123456'),
	('Aluno6', 'ALUNO', 'Aluno6', '123456'),
    ('João', 'PROFESSOR', 'jao123', '000000'),
	('Cesar', 'PROFESSOR', 'cesarminoti', '145asb'),
	('Irineu', 'PROFESSOR', 'irineu', 'vcnsabenemeu'),
    ('Professor1', 'PROFESSOR', 'Professor1', '123456'),
	('Professor2', 'PROFESSOR', 'Professor2', '123456'),
	('Professor3', 'PROFESSOR', 'Professor3', '123456'),
    ('Professor4', 'PROFESSOR', 'Professor4', '123456'),
	('Professor5', 'PROFESSOR', 'Professor5', '123456'),
	('Professor6', 'PROFESSOR', 'Professor6', '123456'),
	('admin', 'ADMIN', 'admin', '159357');

CREATE TABLE Endereco (
	id SERIAL PRIMARY KEY,
	cidade VARCHAR(128) NOT NULL,
	uf VARCHAR(2) NOT NULL,
	endereco VARCHAR(128) NOT NULL
);
 
INSERT INTO Endereco (cidade, uf, endereco)
VALUES
	('Mossoró', 'RN', 'Rua das Véa, 123'),
	('Jucurutu', 'RN', 'Rua Acolá, 965'),
	('Fortaleza', 'CE', 'Rua Tal, 33'),
    ('João Pessoa', 'PB', 'Rua do Amigo, 37'),
	('São Paulo', 'SP', 'Rua Cebolinha, 954'),
	('Macau', 'RN', 'Rua Beco, 66'),
	('Cidade1', 'RN', 'Rua das Véa, 123'),
	('Cidade2', 'RN', 'Rua Acolá, 965'),
	('Cidade3', 'CE', 'Rua Tal, 33'),
    ('Cidade4', 'PB', 'Rua do Amigo, 37'),
	('Cidade5', 'SP', 'Rua Cebolinha, 954'),
	('Cidade6', 'RN', 'Rua Beco, 66'),
	('Cidade7', 'RN', 'Rua das Véa, 123'),
	('Cidade8', 'RN', 'Rua Acolá, 965'),
	('Cidade9', 'CE', 'Rua Tal, 33'),
    ('Cidade10', 'PB', 'Rua do Amigo, 37'),
	('Cidade11', 'SP', 'Rua Cebolinha, 954'),
	('Cidade12', 'RN', 'Rua Beco, 66');

CREATE TABLE Aluno (
    id BIGINT PRIMARY KEY,
	matricula VARCHAR(10) NOT NULL,
	endereco_id BIGINT REFERENCES Endereco(id) NOT NULL ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);
 
INSERT INTO Aluno (id, matricula, endereco_id)
VALUES
	(1, '2020010207', 1),
	(2, '2020010208', 2),
	(3, '2020010209', 3),
	(4, '2020010207', 4),
	(5, '2020010208', 5),
	(6, '2020010209', 6),
	(7, '2020010207', 7),
	(8, '2020010208', 8),
	(9, '2020010209', 9);



CREATE TABLE Professor (
	id BIGINT PRIMARY KEY,
	cpf VARCHAR(11) NOT NULL,
	endereco_id BIGINT REFERENCES Endereco(id) NOT NULL,
	FOREIGN KEY (id) REFERENCES Usuario(id) ON DELETE CASCADE ON UPDATE CASCADE
);
 
INSERT INTO Professor (id, cpf, endereco_id)
VALUES
	(10, '12345678912', 10),
	(11, '34592839242', 11),
	(12, '92384743829', 12),
	(13, '12345678912', 13),
	(14, '34592839242', 14),
	(15, '92384743829', 15),
	(16, '12345678912', 16),
	(17, '34592839242', 17),
	(18, '92384743829', 18);

CREATE TABLE Disciplina (
    id SERIAL PRIMARY KEY,
	codigo VARCHAR(128) NOT NULL,
	nome VARCHAR(128) NOT NULL
);
 
INSERT INTO Disciplina (codigo, nome)
VALUES
    ('123', 'Matematica'),
    ('456', 'Filosofia'),
    ('789', 'Historia'),
	('123', 'Disciplina1'),
    ('456', 'Disciplina2'),
    ('789', 'Disciplina3'),
	('123', 'Disciplina4'),
    ('456', 'Disciplina5'),
    ('789', 'Disciplina6');


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
	(1, 10, 'T01', '24M12', 'remoto', true),
	(2, 11, 'T02', '35T45', 'lab01', true),
	(3, 12, 'T03', '12N35', 'remoto', true),
	(4, 13, 'T04', '24M12', 'remoto', true),
	(5, 14, 'T05', '35T45', 'lab01', true),
	(6, 15, 'T06', '12N35', 'remoto', false),
	(7, 16, 'T07', '24M12', 'remoto', false),
	(8, 17, 'T08', '35T45', 'lab01', false),
	(9, 18, 'T09', '12N35', 'remoto', false);

CREATE TABLE Diario (
	nota1 NUMERIC(3,1),
	nota2 NUMERIC(3,1),
	nota3 NUMERIC(3,1),
	quarta_prova NUMERIC(3,1),
	media NUMERIC(3,1),
	frequencia NUMERIC(3,0),
	aluno_id BIGINT REFERENCES Aluno(id) ON DELETE CASCADE NOT NULL,
	turma_id BIGINT REFERENCES Turma(id) ON DELETE CASCADE NOT NULL,
    created_at DATE DEFAULT now(),
	PRIMARY KEY(aluno_id, turma_id)
);

ALTER TABLE Diario ADD CONSTRAINT check_nota1 CHECK (nota1>=0 AND nota1<=10);
ALTER TABLE Diario ADD CONSTRAINT check_nota2 CHECK (nota2>=0 AND nota2<=10);
ALTER TABLE Diario ADD CONSTRAINT check_nota3 CHECK (nota3>=0 AND nota3<=10);
ALTER TABLE Diario ADD CONSTRAINT check_q_prova CHECK (quarta_prova>=0 AND quarta_prova<=10);
 
INSERT INTO Diario (aluno_id, turma_id, nota1, nota2, nota3, quarta_prova, frequencia)
VALUES
	(1, 1, 9.5, 6.7, 5.3, NULL, 79),
	(2, 1, 4.8, 8.4, 10.0, NULL, 100),
	(3, 2, 3.6, 2.5, 4.0, 9.8, 95);

CREATE VIEW boletim AS
SELECT aluno_id, turma_id, nota1, nota2, nota3, quarta_prova, media
FROM Diario;

CREATE OR REPLACE FUNCTION public.update_media()
RETURNS trigger AS $media_trigger$
BEGIN
	IF (NEW <> OLD AND NEW.quarta_prova IS NULL) THEN
		UPDATE Diario SET media = (COALESCE(nota1, 0) + COALESCE(nota2, 0) + COALESCE(nota3, 0)) / 3 WHERE aluno_id = NEW.aluno_id;
		RETURN NEW;
	ELSIF (NEW <> OLD AND NEW.quarta_prova IS NOT NULL) THEN
		UPDATE Diario SET media = ((((COALESCE(nota1, 0) + COALESCE(nota2, 0) + COALESCE(nota3, 0)) / 3) * 6) + NEW.quarta_prova * 4) / 10 WHERE aluno_id = NEW.aluno_id;
		RETURN NEW;
	END IF;
	RETURN NULL;
END;
$media_trigger$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER media_calc_tg AFTER UPDATE ON Diario FOR EACH ROW EXECUTE PROCEDURE
update_media();

INSERT INTO Diario (aluno_id, turma_id)
VALUES
	(1, 2),
	(1, 3),
	(1, 4),
	(1, 5),
	(1, 6),
	(1, 7),
	(1, 8),
	(1, 9);

UPDATE Diario SET nota1=10, nota2=10, nota3=10 WHERE aluno_id=1;