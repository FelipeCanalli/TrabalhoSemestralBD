CREATE DATABASE pizzaria;
GO
USE pizzaria;
GO

CREATE TABLE pessoa (
id INT IDENTITY NOT NULL,
nome VARCHAR (100) NOT NULL,
cpf char(11) NOT NULL,
tipo VARCHAR (50) NOT NULL,
logradouro VARCHAR (100) NOT NULL,
numero CHAR (6) NOT NULL,
complemento VARCHAR (20) NOT NULL,
telefone char(11) NOT NULL,
email VARCHAR (50) NULL,
primary key (id))
GO

CREATE TABLE funcionario (
id INT IDENTITY NOT NULL,
salario DECIMAL (7,2) NOT NULL,
id_pessoa INT NOT NULL
primary key (id)
foreign key (id_pessoa) references pessoa (id))

CREATE TABLE pagamento(
id INT IDENTITY NOT NULL,
id_pessoa INT NOT NULL,
valor DECIMAL (7,2) NOT NULL CHECK(valor > 0.00),
forma VARCHAR (10) NOT NULL,
primary key (id),
foreign key (id_pessoa) references pessoa (id))
GO

CREATE TABLE pizza (
id INT IDENTITY NOT NULL,
nome VARCHAR (20) NOT NULL,
ingredientes VARCHAR (200) NOT NULL,
preco decimal (7,2) NOT NULL CHECK(preco > 0.00),
tamanho CHAR (10) NOT NULL,
primary key (id)
)

CREATE TABLE pedido (
id INT IDENTITY NOT NULL,
dataCompra DATE NOT NULL,
id_pessoa INT NOT NULL,
id_pizza INT NOT NULL,
id_pagamento INT NULL,
primary key (id),
foreign key (id_pessoa) references pessoa (id),
foreign key (id_pizza) references pizza (id),
foreign key (id_pagamento) references pagamento (id))

INSERT INTO pessoa (nome, cpf, tipo, logradouro, numero, complemento, telefone, email) VALUES
('Felipe Galvão', '44477851437', 'Av', 'Paris', '45000', 'Casa 2', 11993894811, 'felipe@gmail.com'), 
('Yuri Augusto', '55585671584', 'Rua', 'Banana', '204B', 'Bloco B AP 20', 11875645211, 'yuri@gmail.com')

select * from pessoa;