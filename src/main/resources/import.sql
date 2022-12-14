INSERT INTO EventoFluxo (id, identificadorFluxo, versaoFluxo, dado, tipoAgregado, tipoEvento)
VALUES (1, '3b95f860-24ea-4bf8-bf19-5515ef41fbfb', 1,
 '{"ocorridoEm":"2022-10-14T15:46:40.0704877","versao":1,"idTransacao":"3b95f860-24ea-4bf8-bf19-5515ef41fbfb","comando":{"descricao":"Descrição","valor":50.0,"nomeDoPagador":"André","nomeDoRecebedor":"Juliana","idPagamento":"umIdPagamento"}}',
 "Transacao", "TransacaoCriada");

INSERT INTO EventoFluxo (id, identificadorFluxo, versaoFluxo, dado, tipoAgregado, tipoEvento)
VALUES (2, '77676b0b-576c-408d-b1f9-5a4a07e85f38', 1,
'{"ocorridoEm":"2022-10-14T17:30:06.0374037","versao":1,"idTransacao":"77676b0b-576c-408d-b1f9-5a4a07e85f38","comando":{"descricao":"Uma Descrição","valor":450.0,"nomeDoPagador":"Carlos","nomeDoRecebedor":"Caruzo","idPagamento":"umIdPagamento"}}',
 "Transacao", "TransacaoCriada");

INSERT INTO EventoFluxo (id, identificadorFluxo, versaoFluxo, dado, tipoAgregado, tipoEvento)
VALUES (3, '644e2344-5b7c-4120-a4c4-c3a2d251b62b', 1,
'{"ocorridoEm":"2022-10-14T17:30:28.49669","versao":1,"idTransacao":"644e2344-5b7c-4120-a4c4-c3a2d251b62b","comando":{"descricao":"Uma Descrição","valor":399.0,"nomeDoPagador":"Cleopatra","nomeDoRecebedor":"Marcos Antonio","idPagamento":"umIdPagamento"}}',
 "Transacao", "TransacaoCriada");

INSERT INTO EventoFluxo (id, identificadorFluxo, versaoFluxo, dado, tipoAgregado, tipoEvento)
VALUES (4, '1778d5e0-447b-4559-ac35-715fe195d64e', 1,
'{"ocorridoEm":"2022-10-14T17:30:31.9186442","versao":1,"idTransacao":"1778d5e0-447b-4559-ac35-715fe195d64e","comando":{"descricao":"Uma Descrição","valor":399.0,"nomeDoPagador":"Nero","nomeDoRecebedor":"Erich","idPagamento":"umIdPagamento"}}',
 "Transacao", "TransacaoCriada");

--INSERT INTO EventoArmazenado (id, idAgregado, versao, dado)
--VALUES (5, '1778d5e0-447b-4559-ac35-715fe195d64e', 2,
--'{"ocorridoEm":"2022-10-14T17:30:31.9186442","versao":1,"comando":{"idTransacao":"1778d5e0-447b-4559-ac35-715fe195d64e","descricao":"Descrição","quantidade":3,"tipoUnidadeMedida":"UN","valorUnidade":399.0}}');
--
--INSERT INTO EventoArmazenado (id, idAgregado, versao, dado)
--VALUES (6, '1778d5e0-447b-4559-ac35-715fe195d64e', 3,
--'{"ocorridoEm":"2022-10-14T17:30:31.9186442","versao":1,"comando":{"idTransacao":"1778d5e0-447b-4559-ac35-715fe195d64e","descricao":"Carpacio","quantidade":10,"tipoUnidadeMedida":"UN","valorUnidade":599.0}}');

UPDATE hibernate_sequence SET next_val=15;