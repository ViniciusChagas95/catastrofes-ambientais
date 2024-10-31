-- V1__Inicial.sql
CREATE TABLE cidade (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nome VARCHAR(255),
                        estado VARCHAR(255)
);

CREATE TABLE catastrofe (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            tipo VARCHAR(255),
                            descricao TEXT,
                            data DATETIME,
                            cidade_id BIGINT,
                            CONSTRAINT fk_cidade FOREIGN KEY (cidade_id) REFERENCES cidade(id)
);
