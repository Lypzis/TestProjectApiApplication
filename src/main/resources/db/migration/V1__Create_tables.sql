CREATE TABLE situacao_cartorio (
    id VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE atribuicao_cartorio (
    id VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    situacao BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE cartorio (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    observacao VARCHAR(250),
    situacao_id VARCHAR(20) NOT NULL,
    CONSTRAINT fk_situacao FOREIGN KEY (situacao_id) REFERENCES situacao_cartorio(id)
);

CREATE TABLE cartorio_atribuicoes (
    cartorio_id INT NOT NULL,
    atribuicao_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (cartorio_id, atribuicao_id),
    CONSTRAINT fk_cartorio FOREIGN KEY (cartorio_id) REFERENCES cartorio(id),
    CONSTRAINT fk_atribuicao FOREIGN KEY (atribuicao_id) REFERENCES atribuicao_cartorio(id)
);
