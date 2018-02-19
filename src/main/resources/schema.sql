DROP TABLE INGREDIENTE;
DROP TABLE CARDAPIO;
DROP TABLE PEDIDO;

DROP SEQUENCE PEDIDO_SEQ;

CREATE TABLE INGREDIENTE(
    id bigint not null,
    descricao varchar(255) not null,
    valor number(19,2),
    primary key(id)
);

CREATE TABLE CARDAPIO(
    id bigint not null,
    tipo_lanche varchar(255) not null,
    descricao varchar(255) not null,
    primary key(id)
);

CREATE TABLE PEDIDO(
    id bigint not null,
    id_cliente bigint not null,
    valor number(19,2),
    dt_Pedido DATE,
    primary key(id)
);

CREATE SEQUENCE PEDIDO_SEQ;