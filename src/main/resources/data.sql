INSERT INTO restaurante (id, cep, complemento, nome) VALUES
(1L, '0000001', 'Complemento Endereco Restaurante 1', 'La Mole'),
(2L, '0000002', 'Complemento Endereco Restaurante 2', 'Burguês');

INSERT INTO cliente (id, cep, complemento, nome) VALUES
(1L, '0000001', 'Complemento Endereco Cliente 1', 'Cliente 1'),
(2L, '22073-010', 'Sergio Camargo 50', 'Ana Carolina');

INSERT INTO produto (id, disponivel, nome, valor_unitario, restaurante_id) VALUES
(1L, true, 'Arroz', 5.0, 1L),
(2L, true, 'Pudim', 6.0, 1L),
(3L, true, 'Banana', 7.0, 2L);

INSERT INTO sacola (id, forma_pagamento, fechada, valor_total, cliente_id) VALUES
(1L, 3, false, 0.0, 1L);