/* 
 * O Banco de dados esta sendo criado atraves do Spring JPA
 * Esse arquivo e apenas um registro em SQL da estrutura
*/

CREATE DATABASE jpa_aula;

use jpa_aula;

CREATE TABLE livro(
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(80) NOT NULL,
    autor VARCHAR(80) NOT NULL,
    disponivel BOOLEAN NOT NULL,
    isbn VARCHAR(13) NOT NULL,
    PRIMARY KEY(id)
);
