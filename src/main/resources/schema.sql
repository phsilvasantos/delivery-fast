
CREATE TABLE INGREDIENTE(
    id bigint not null,
    descricao varchar(255) not null,
    valor number(19,2) not null,
    primary key(id)
);

CREATE TABLE LANCHE(
    id bigint not null,
    descricao varchar(255) not null,
    tipo_lanche varchar(255),
    valor number(19,2),
    valor_com_desconto number(19,2),
    primary key(id)
);
CREATE SEQUENCE LANCHE_SEQ;
