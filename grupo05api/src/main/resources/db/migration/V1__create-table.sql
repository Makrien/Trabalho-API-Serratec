CREATE TABLE categoria (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    descricao TEXT
);

CREATE TABLE endereco (
    id SERIAL PRIMARY KEY,
    cep VARCHAR(9),
    rua VARCHAR(100),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    numero VARCHAR(10),
    complemento VARCHAR(100),
    uf VARCHAR(2)
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100),
    nome_completo VARCHAR(100),
    cpf VARCHAR(14),
    telefone VARCHAR(20),
    data_nascimento DATE,
    endereco_id INTEGER,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    descricao TEXT,
    qtd_estoque INTEGER,
    data_cadastro DATE,
    valor_unitario NUMERIC(10,2),
    imagem BYTEA,
    id_categoria INTEGER,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

CREATE TABLE pedido (
    id SERIAL PRIMARY KEY,
    data_pedido DATE,
    data_entrega DATE,
    data_envio DATE,
    status VARCHAR(50),
    valor_total NUMERIC(10,2),
    cliente_id INTEGER,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE item_pedido (
    id SERIAL PRIMARY KEY,
    quantidade INTEGER,
    preco_venda NUMERIC(10,2),
    percentual_desconto NUMERIC(5,2),
    valor_bruto NUMERIC(10,2),
    valor_liquido NUMERIC(10,2),
    pedido_id INTEGER,
    produto_id INTEGER,
    FOREIGN KEY (pedido_id) REFERENCES pedido(id),
    FOREIGN KEY (produto_id) REFERENCES produto(id)
);


INSERT INTO endereco (cep, rua, bairro, cidade, numero, complemento, uf) VALUES
('01001000', 'Avenida Paulista', 'Bela Vista', 'São Paulo', '123', 'Apto 101', 'SP'),
('30140071', 'Rua da Bahia', 'Centro', 'Belo Horizonte', '456', 'Sala 202', 'MG'),
('22290040', 'Rua Voluntários da Pátria', 'Botafogo', 'Rio de Janeiro', '789', 'Cobertura', 'RJ'),
('88010020', 'Rua Conselheiro Mafra', 'Centro', 'Florianópolis', '101', 'Loja A', 'SC'),
('69005010', 'Avenida Eduardo Ribeiro', 'Centro', 'Manaus', '202', 'Sala 15', 'AM'),
('70040900', 'Eixo Monumental', 'Asa Sul', 'Brasília', '303', 'Bloco C', 'DF'),
('40020000', 'Avenida Sete de Setembro', 'Centro', 'Salvador', '404', 'Apto 12', 'BA'),
('50010030', 'Rua do Imperador', 'Santo Antônio', 'Recife', '505', 'Casa 7', 'PE'),
('64000030', 'Avenida Frei Serafim', 'Centro', 'Teresina', '606', 'Galpão', 'PI'),
('79002900', 'Rua 14 de Julho', 'Centro', 'Campo Grande', '707', 'Sala 3', 'MS');

INSERT INTO cliente (email, nome_completo, cpf, telefone, data_nascimento, endereco_id) VALUES
('luciano.silva@example.com', 'Luciano Silva', '11122233344', '11987654321', '1990-01-15', 1),
('maria.oliveira@example.com', 'Maria Oliveira', '55566677788', '21987654321', '1985-05-20', 2),
('carlos.santos@example.com', 'Carlos Santos', '99988877766', '31987654321', '1978-10-12', 3),
('ana.pereira@example.com', 'Ana Pereira', '33322211100', '41987654321', '1995-12-05', 4),
('jose.silva@example.com', 'José Silva', '77766655544', '51987654321', '1989-08-25', 5),
('ana.santos@example.com', 'Ana Santos', '44455566677', '61987654321', '1976-03-18', 6),
('pedro.pereira@example.com', 'Pedro Pereira', '88899900011', '71987654321', '1988-07-10', 7),
('maria.oliveira@example.com', 'Maria Oliveira', '22233344455', '81987654321', '2001-11-05', 8),
('joao.silva@example.com', 'João Silva', '66677788899', '91987654321', '1995-09-12', 9),
('raquel.pereira@example.com', 'Raquel Pereira', '00011122233', '10987654321', '1980-06-08', 10);
