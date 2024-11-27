-- noinspection SqlNoDataSourceInspectionForFile

INSERT INTO PRODUTO (ID, NOME, DESCRICAO, VALOR, CATEGORIA) VALUES
                                                                ('5c4c83cb-f1e8-4182-8601-281323f00111', 'Hex Burger', 'Pão e Hambuguer no formato hexagonal', 15.00, 'LANCHE'),
                                                                ('2b68f2cc-75c5-4ed7-a712-0a6a6443127b', 'Hex Burger Duplo', 'Pão e dois Hambugueres no formato hexagonal', 27.00, 'LANCHE'),
                                                                ('41980234-5f97-4443-9c2e-9afa3600bcfb', 'Hex Burger Para Remover', 'Pão e dois Hambugueres no formato hexagonal', 27.00, 'LANCHE'),
                                                                ('56d4e6b1-b1bd-44e6-9818-e459f230e3e4', 'Hex Burger Para Editar', 'Pão e dois Hambugueres no formato hexagonal', 27.00, 'LANCHE'),
                                                                ('e5adfabe-d80c-42ce-8d72-054be337693b', 'Hex Burger Para Editar 2', 'Pão e dois Hambugueres no formato hexagonal', 27.00, 'LANCHE'),
                                                                ('92ffdcbf-f74c-4b05-93c5-be96ef1d9326', 'Hex Chicken', 'Pão e Hambuguer de frango no formato hexagonal', 18.00, 'LANCHE'),
                                                                ('84ee2567-fe65-455b-bf23-9543d4e86be1', 'Hex Cola', 'Refrigerante sabor cola', 6.00, 'BEBIDA'),
                                                                ('a1fe5443-3bc7-4cb0-8ffe-a1dc0bf8768c', 'Água', 'Água', 4.00, 'BEBIDA'),
                                                                ('f55f733e-aeb3-4955-87d7-b521895cae80', 'Hex Fries', 'Batatas fritas crocantes', 8.00, 'ACOMPANHAMENTO'),
                                                                ('8c722a6a-57d6-4ea1-9d98-79e78835837b', 'Hex Nuggets', 'Nuggets no formato hexagonal', 12.00, 'ACOMPANHAMENTO'),
                                                                ('7cbc859a-a702-4f64-9f81-2136c338338c', 'Hex Gelatto', 'Sorvete de creme 200ml', 15.00, 'SOBREMESA'),
                                                                ('f4cc8aa2-2670-4e12-b3ed-ec281fa50411', 'Hex Donut', 'Donut no formato hexagonal', 8.00, 'SOBREMESA');

-- Pedido Recebido
INSERT INTO PRODUTO_PEDIDO (ID, NOME, DESCRICAO, VALOR, CATEGORIA) VALUES
                                                                       ('69d8ffc2-e77c-45e2-8245-3a86664e1f5d', 'Hex Burger', 'Pão e Hambuguer no formato hexagonal', 15.00, 'LANCHE'),
                                                                       ('1c365852-b801-4081-965f-c4a27c92377c', 'Hex Cola', 'Refrigerante sabor cola', 6.00, 'BEBIDA'),
                                                                       ('236c13a3-b3f2-4114-96f1-75c9a9d72ea3', 'Hex Fries', 'Batatas fritas crocantes', 8.00, 'ACOMPANHAMENTO'),
                                                                       ('faf7413f-5a19-4b1c-a751-84254ab20722', 'Hex Gelatto', 'Sorvete de creme 200ml', 15.00, 'SOBREMESA');

INSERT INTO COMBO (ID, VALOR_TOTAL) VALUES
    ('2b8be9d3-2f01-47c7-ba47-8a621869a4ec', 44.00);

INSERT INTO combo_produtos_pedido (ecombo_id, produtos_pedido_id) VALUES
                                                                      ('2b8be9d3-2f01-47c7-ba47-8a621869a4ec', '69d8ffc2-e77c-45e2-8245-3a86664e1f5d'),
                                                                      ('2b8be9d3-2f01-47c7-ba47-8a621869a4ec', '1c365852-b801-4081-965f-c4a27c92377c'),
                                                                      ('2b8be9d3-2f01-47c7-ba47-8a621869a4ec', '236c13a3-b3f2-4114-96f1-75c9a9d72ea3'),
                                                                      ('2b8be9d3-2f01-47c7-ba47-8a621869a4ec', 'faf7413f-5a19-4b1c-a751-84254ab20722');

INSERT INTO PEDIDO (ID, CODIGO, ID_EXTERNO_PAGAMENTO, QR_CODE, STATUS, STATUS_PAGAMENTO, CPF_CLIENTE, VALOR_TOTAL, DATA_PEDIDO) VALUES
    ('9c3e7112-ac5c-413a-9e24-22688bf60976', '1', null, null, 'RECEBIDO', 'AGUARDANDO', '12345678900', 44.00, '2024-10-30 12:00:00');

INSERT INTO pedido_combos (epedido_id, combos_id) VALUES
    ('9c3e7112-ac5c-413a-9e24-22688bf60976', '2b8be9d3-2f01-47c7-ba47-8a621869a4ec');

-- Pedido Em Preparacao
INSERT INTO PRODUTO_PEDIDO (ID, NOME, DESCRICAO, VALOR, CATEGORIA) VALUES
    ('6714e8b4-7d25-4731-974f-8a9723ea74a1', 'Hex Donut', 'Donut no formato hexagonal', 8.00, 'SOBREMESA');

INSERT INTO COMBO (ID, VALOR_TOTAL) VALUES ('16a17800-9c94-455a-b805-7bc54be75d99', 8.00);

INSERT INTO combo_produtos_pedido (ecombo_id, produtos_pedido_id) VALUES
    ('16a17800-9c94-455a-b805-7bc54be75d99', '6714e8b4-7d25-4731-974f-8a9723ea74a1');

INSERT INTO PEDIDO (ID, CODIGO, ID_EXTERNO_PAGAMENTO, QR_CODE, STATUS, STATUS_PAGAMENTO, CPF_CLIENTE, VALOR_TOTAL, DATA_PEDIDO) VALUES
    ('1537484e-33d9-415e-b095-82dbf1f74294', '2', '4797f322-9f28-4259-a746-a37c70c0b48e', 'kdjnfoafhaifjsuiyhjnyghhjukim', 'EM_PREPARACAO', 'APROVADO', '12345678900', 8.00, '2024-10-30 12:45:00');

INSERT INTO pedido_combos (epedido_id, combos_id) VALUES
    ('1537484e-33d9-415e-b095-82dbf1f74294', '16a17800-9c94-455a-b805-7bc54be75d99');

-- Pedido Pronto
INSERT INTO PRODUTO_PEDIDO (ID, NOME, DESCRICAO, VALOR, CATEGORIA) VALUES
    ('62bd475c-1aeb-4722-b642-78d75a2eede3', 'Hex Fries', 'Batatas fritas crocantes', 8.00, 'ACOMPANHAMENTO');

INSERT INTO COMBO (ID, VALOR_TOTAL) VALUES ('6ee05a7f-063c-4659-ac1f-cfb61e4b9c9b', 8.00);

INSERT INTO combo_produtos_pedido (ecombo_id, produtos_pedido_id) VALUES
    ('6ee05a7f-063c-4659-ac1f-cfb61e4b9c9b', '62bd475c-1aeb-4722-b642-78d75a2eede3');

INSERT INTO PEDIDO (ID, CODIGO, ID_EXTERNO_PAGAMENTO, QR_CODE, STATUS, STATUS_PAGAMENTO, CPF_CLIENTE, VALOR_TOTAL, DATA_PEDIDO) VALUES
    ('94fc6faa-b0c4-4209-95f2-099a5216e279', '3', '395a5d01-1870-4ef4-85dc-82d784e605db', 'oioujshdbofiuwbseoiuyb', 'PRONTO', 'APROVADO', '12345678900', 8.00, '2024-10-30 12:56:00');

INSERT INTO pedido_combos (epedido_id, combos_id) VALUES
    ('94fc6faa-b0c4-4209-95f2-099a5216e279', '6ee05a7f-063c-4659-ac1f-cfb61e4b9c9b');

-- Pedido Finalizado
INSERT INTO PRODUTO_PEDIDO (ID, NOME, DESCRICAO, VALOR, CATEGORIA) VALUES
    ('e1dfafec-1e95-477c-8a6f-fc61d5aff5fc', 'Hex Burger Duplo', 'Pão e dois Hambugueres no formato hexagonal', 27.00, 'LANCHE');

INSERT INTO COMBO (ID, VALOR_TOTAL) VALUES ('12821c47-91bf-475f-a80b-3ff0ffebb49a', 27.00);

INSERT INTO combo_produtos_pedido (ecombo_id, produtos_pedido_id) VALUES
    ('12821c47-91bf-475f-a80b-3ff0ffebb49a', 'e1dfafec-1e95-477c-8a6f-fc61d5aff5fc');

INSERT INTO PEDIDO (ID, CODIGO, ID_EXTERNO_PAGAMENTO, QR_CODE, STATUS, STATUS_PAGAMENTO, CPF_CLIENTE, VALOR_TOTAL, DATA_PEDIDO) VALUES
    ('8549f024-979b-445b-9192-03725b79f67a', '4', '38fa21a8-135d-4091-a617-e5d3046487e1', 'wetsdfswecvxwefsdc', 'FINALIZADO', 'APROVADO', '45678912305', 27.00, '2024-10-30 13:43:00');

INSERT INTO pedido_combos (epedido_id, combos_id) VALUES
    ('8549f024-979b-445b-9192-03725b79f67a', '12821c47-91bf-475f-a80b-3ff0ffebb49a');

-- Pedido Cancelado
INSERT INTO PRODUTO_PEDIDO (ID, NOME, DESCRICAO, VALOR, CATEGORIA) VALUES
    ('b178aab5-a428-4700-a873-55555b96a5ac', 'Hex Chicken', 'Pão e Hambuguer de frango no formato hexagonal', 18.00, 'LANCHE');

INSERT INTO COMBO (ID, VALOR_TOTAL) VALUES ('4d907897-9498-4361-88f3-2b3a8fb98cf2', 18.00);

INSERT INTO combo_produtos_pedido (ecombo_id, produtos_pedido_id) VALUES
    ('4d907897-9498-4361-88f3-2b3a8fb98cf2', 'b178aab5-a428-4700-a873-55555b96a5ac');

INSERT INTO PEDIDO (ID, CODIGO, ID_EXTERNO_PAGAMENTO, QR_CODE, STATUS, STATUS_PAGAMENTO, CPF_CLIENTE, VALOR_TOTAL, DATA_PEDIDO) VALUES
    ('2914cce4-86c8-4593-91bb-0fc1fb5c244b', '5', '4a3f5789-0374-4c20-a5c7-be58f676578e', 'wetsdfswecvxwefsdc', 'CANCELADO', 'RECUSADO', '45678912305', 18.00, '2024-10-30 13:48:00');

INSERT INTO pedido_combos (epedido_id, combos_id) VALUES
    ('2914cce4-86c8-4593-91bb-0fc1fb5c244b', '4d907897-9498-4361-88f3-2b3a8fb98cf2');

-- Pedido Em Preparacao (para ter seu status de pagamento atualizado)
INSERT INTO PRODUTO_PEDIDO (ID, NOME, DESCRICAO, VALOR, CATEGORIA) VALUES
    ('2eb5a5dd-89db-4dc2-aac0-eeed670ac005', 'Hex Chicken', 'Pão e Hambuguer de frango no formato hexagonal', 18.00, 'LANCHE');

INSERT INTO COMBO (ID, VALOR_TOTAL) VALUES ('bf907f56-cb9c-4395-98c2-44eeeb0f4925', 18.00);

INSERT INTO combo_produtos_pedido (ecombo_id, produtos_pedido_id) VALUES
    ('bf907f56-cb9c-4395-98c2-44eeeb0f4925', '2eb5a5dd-89db-4dc2-aac0-eeed670ac005');

INSERT INTO PEDIDO (ID, CODIGO, ID_EXTERNO_PAGAMENTO, QR_CODE, STATUS, STATUS_PAGAMENTO, CPF_CLIENTE, VALOR_TOTAL, DATA_PEDIDO) VALUES
    ('bcb91b64-f69b-48c8-9f87-576053def915', '5', '7421d4bf-9aa2-4aac-b503-0a9bd8f1dd2d', 'aaaaaaaaaa', 'EM_PREPARACAO', 'AGUARDANDO', '45678912305', 18.00, '2024-10-30 13:48:00');

INSERT INTO pedido_combos (epedido_id, combos_id) VALUES
    ('bcb91b64-f69b-48c8-9f87-576053def915', 'bf907f56-cb9c-4395-98c2-44eeeb0f4925');

-- Pedido Em Preparacao (para ter seu status de pedido atualizado)
INSERT INTO PRODUTO_PEDIDO (ID, NOME, DESCRICAO, VALOR, CATEGORIA) VALUES
    ('e6186f79-69b5-422b-bee0-d827cb34c5a4', 'Hex Chicken', 'Pão e Hambuguer de frango no formato hexagonal', 18.00, 'LANCHE');

INSERT INTO COMBO (ID, VALOR_TOTAL) VALUES ('ab7d2ca1-2429-469f-8b1d-781320399e71', 18.00);

INSERT INTO combo_produtos_pedido (ecombo_id, produtos_pedido_id) VALUES
    ('ab7d2ca1-2429-469f-8b1d-781320399e71', 'e6186f79-69b5-422b-bee0-d827cb34c5a4');

INSERT INTO PEDIDO (ID, CODIGO, ID_EXTERNO_PAGAMENTO, QR_CODE, STATUS, STATUS_PAGAMENTO, CPF_CLIENTE, VALOR_TOTAL, DATA_PEDIDO) VALUES
    ('3115c8c0-40cf-4df2-a9fd-023b227d935d', '5', '09729d18-a1ba-426f-81fc-0749946c0165', 'aaaaaaaaaa', 'EM_PREPARACAO', 'AGUARDANDO', '45678912305', 18.00, '2024-10-30 13:48:00');

INSERT INTO pedido_combos (epedido_id, combos_id) VALUES
    ('3115c8c0-40cf-4df2-a9fd-023b227d935d', 'ab7d2ca1-2429-469f-8b1d-781320399e71');
