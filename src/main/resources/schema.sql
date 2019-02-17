--DROP TABLE INGREDIENTE;
--DROP TABLE CARDAPIO;
--DROP TABLE PEDIDO;
--DROP TABLE LANCHE;
--
--DROP SEQUENCE PEDIDO_SEQ;
--DROP SEQUENCE LANCHE_SEQ;
--DROP SEQUENCE PEDIDO_SEQ;

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

CREATE TABLE LANCHE(
    id bigint not null,
    descricao varchar(255) not null,
    tipo_lanche varchar(255),
    primary key(id)
);

CREATE SEQUENCE LANCHE_SEQ;

CREATE TABLE PEDIDOS_LANCHES(
    id_pedido bigint not null,
    id_lanche bigint not null,
    CONSTRAINT FK_PEDIDOS_LANCHES FOREIGN KEY(id_pedido) REFERENCES PEDIDOS(id),
    CONSTRAINT FK_LANCHES_PEDIDO FOREIGN KEY(id_lanche) REFERENCES LANCHE(id),
);


CREATE TABLE PEDIDOS(
    id bigint not null,
    cliente varchar(80) not null,
    endereco varchar(255) ,
    dt_Pedido DATE,
    valor number(19,2),
    status varchar(80) not null,
    primary key(id)
);

CREATE SEQUENCE PEDIDOS_SEQ;