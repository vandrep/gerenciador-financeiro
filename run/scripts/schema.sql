GRANT SELECT, RELOAD, SHOW DATABASES, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'vandrep'@'%';

CREATE TABLE EventoFluxo (
  id BIGINT AUTO_INCREMENT NOT NULL,
   identificadorFluxo char(36) NOT NULL,
   versaoFluxo INT NOT NULL,
   dado BLOB NOT NULL,
   metadado BLOB NULL,
   timestamp datetime NULL,
   tipoAgregado VARCHAR(255) NOT NULL,
   tipoEvento VARCHAR(255) NOT NULL,
   CONSTRAINT pk_eventofluxo PRIMARY KEY (id)
);

ALTER TABLE EventoFluxo ADD CONSTRAINT idEVersaoUnico UNIQUE (identificadorFluxo, versaoFluxo);