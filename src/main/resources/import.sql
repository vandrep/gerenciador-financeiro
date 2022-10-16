INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (1, 'TransacaoCriada', 'Transacao', '3b95f860-24ea-4bf8-bf19-5515ef41fbfb',
 '{"idEntidade":"3b95f860-24ea-4bf8-bf19-5515ef41fbfb","ocorridoEm":"2022-10-14T15:46:40.0704877","valor":50.0,"nomeFornecedor":"André","nomeBeneficiario":"Juliana"}');

INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (2, 'TransacaoCriada', 'Transacao', '77676b0b-576c-408d-b1f9-5a4a07e85f38',
'{"idEntidade":"77676b0b-576c-408d-b1f9-5a4a07e85f38","ocorridoEm":"2022-10-14T17:30:06.0374037","valor":450.0,"nomeFornecedor":"Carlos","nomeBeneficiario":"Caruzo"}');

INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (3, 'TransacaoCriada', 'Transacao', '644e2344-5b7c-4120-a4c4-c3a2d251b62b',
'{"idEntidade":"644e2344-5b7c-4120-a4c4-c3a2d251b62b","ocorridoEm":"2022-10-14T17:30:28.49669","valor":399.0,"nomeFornecedor":"Cleopatra","nomeBeneficiario":"Julio"}');

INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (4, 'TransacaoCriada', 'Transacao', '1778d5e0-447b-4559-ac35-715fe195d64e',
'{"idEntidade":"1778d5e0-447b-4559-ac35-715fe195d64e","ocorridoEm":"2022-10-14T17:30:31.9186442","valor":399.0,"nomeFornecedor":"Cleopatra","nomeBeneficiario":"Julio"}');

INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (5, 'ItemPagoAdicionado', 'Transacao', '1778d5e0-447b-4559-ac35-715fe195d64e',
'{"idEntidade":"1778d5e0-447b-4559-ac35-715fe195d64e","ocorridoEm":"2022-10-14T17:30:31.9186442","descricao":"Descrição","quantidade":3,"unidadeMedida":"UNIDADE","valorUnidade":399.0}');

INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (6, 'ItemPagoAdicionado', 'Transacao', '1778d5e0-447b-4559-ac35-715fe195d64e',
'{"idEntidade":"1778d5e0-447b-4559-ac35-715fe195d64e","ocorridoEm":"2022-10-14T17:30:31.9186442","descricao":"Carpacio","quantidade":10,"unidadeMedida":"UNIDADE","valorUnidade":599.0}');

UPDATE hibernate_sequence SET next_val=15;