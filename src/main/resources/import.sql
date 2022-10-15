INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (1, 'PagamentoEmRealCriado', 'Pagamento', '3b95f860-24ea-4bf8-bf19-5515ef41fbfb',
 '{"idEntidade":"3b95f860-24ea-4bf8-bf19-5515ef41fbfb","ocorridoEm":"2022-10-14T15:46:40.0704877","data":"14/10/2002","valor":50.0,"nomeFornecedor":"André","nomeBeneficiario":"Juliana"}');

INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (2, 'PagamentoEmRealCriado', 'Pagamento', '77676b0b-576c-408d-b1f9-5a4a07e85f38',
'{"idEntidade":"77676b0b-576c-408d-b1f9-5a4a07e85f38","ocorridoEm":"2022-10-14T17:30:06.0374037","data":"17/10/2022","valor":450.0,"nomeFornecedor":"Carlos","nomeBeneficiario":"Caruzo"}');

INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (3, 'PagamentoEmRealCriado', 'Pagamento', '644e2344-5b7c-4120-a4c4-c3a2d251b62b',
'{"idEntidade":"644e2344-5b7c-4120-a4c4-c3a2d251b62b","ocorridoEm":"2022-10-14T17:30:28.49669","data":"17/11/2022","valor":399.0,"nomeFornecedor":"Cleopatra","nomeBeneficiario":"Julio"}');

INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (4, 'PagamentoEmRealCriado', 'Pagamento', '1778d5e0-447b-4559-ac35-715fe195d64e',
'{"idEntidade":"1778d5e0-447b-4559-ac35-715fe195d64e","ocorridoEm":"2022-10-14T17:30:31.9186442","data":"17/11/2022","valor":399.0,"nomeFornecedor":"Cleopatra","nomeBeneficiario":"Julio"}');

INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (5, 'ItemPagoAdicionado', 'Pagamento', '1778d5e0-447b-4559-ac35-715fe195d64e',
'{"idEntidade":"1778d5e0-447b-4559-ac35-715fe195d64e","ocorridoEm":"2022-10-14T17:30:31.9186442","nome":"Descrição","valor":399.0,"quantidade":3}');

INSERT INTO EventoArmazenado (id, tipoEvento, tipoEntidade, idEntidade, dadosEvento)
VALUES (6, 'ItemPagoAdicionado', 'Pagamento', '1778d5e0-447b-4559-ac35-715fe195d64e',
'{"idEntidade":"1778d5e0-447b-4559-ac35-715fe195d64e","ocorridoEm":"2022-10-14T17:30:31.9186442","nome":"Carpacio","valor":599.0,"quantidade":10}');

UPDATE hibernate_sequence SET next_val=15;