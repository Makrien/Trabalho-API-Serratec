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

