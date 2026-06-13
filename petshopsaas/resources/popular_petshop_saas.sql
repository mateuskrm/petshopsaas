USE petshop_saas;

START TRANSACTION;

-- 1) Petshops
INSERT INTO pet_shop (id, nome, cnpj, endereco, telefone) VALUES
(100, 'Pet Feliz Banho e Tosa', '12.345.678/0001-90', 'Rua das Flores, 120 - Centro', '(47) 3333-1000'),
(101, 'Animal Care Petshop', '98.765.432/0001-10', 'Av. Brasil, 850 - Vila Nova', '(47) 3333-2000')
ON DUPLICATE KEY UPDATE
  nome = VALUES(nome), cnpj = VALUES(cnpj), endereco = VALUES(endereco), telefone = VALUES(telefone);

-- 2) Usuários gerais do sistema
INSERT INTO usuario (id, nome, email, senha, telefone, tipo_usuario) VALUES
(100, 'Administrador do Sistema', 'admin@petshop.com', '123456', '(47) 99999-0000', 'ADMIN'),
(101, 'Maria Oliveira', 'maria@email.com', '123456', '(47) 99999-1111', 'CLIENTE'),
(102, 'João Santos', 'joao@email.com', '123456', '(47) 99999-2222', 'CLIENTE'),
(103, 'Ana Pereira', 'ana@email.com', '123456', '(47) 99999-3333', 'CLIENTE'),
(104, 'Carlos Lima', 'carlos@petshop.com', '123456', '(47) 99999-4444', 'FUNCIONARIO'),
(105, 'Fernanda Costa', 'fernanda@petshop.com', '123456', '(47) 99999-5555', 'FUNCIONARIO'),
(106, 'Roberto Alves', 'roberto@petshop.com', '123456', '(47) 99999-6666', 'FUNCIONARIO')
ON DUPLICATE KEY UPDATE
  nome = VALUES(nome), email = VALUES(email), senha = VALUES(senha), telefone = VALUES(telefone), tipo_usuario = VALUES(tipo_usuario);

-- 3) Clientes vinculados aos petshops
INSERT INTO cliente (id, nome, email, senha, telefone, cpf, endereco, pet_shop_id) VALUES
(100, 'Maria Oliveira', 'maria@email.com', '123456', '(47) 99999-1111', '111.111.111-11', 'Rua A, 45 - Centro', 100),
(101, 'João Santos', 'joao@email.com', '123456', '(47) 99999-2222', '222.222.222-22', 'Rua B, 78 - Bairro Velha', 100),
(102, 'Ana Pereira', 'ana@email.com', '123456', '(47) 99999-3333', '333.333.333-33', 'Rua C, 90 - Garcia', 101),
(103, 'Bruno Martins', 'bruno@email.com', '123456', '(47) 99999-7777', '444.444.444-44', 'Rua D, 150 - Itoupava', 101)
ON DUPLICATE KEY UPDATE
  nome = VALUES(nome), email = VALUES(email), senha = VALUES(senha), telefone = VALUES(telefone),
  cpf = VALUES(cpf), endereco = VALUES(endereco), pet_shop_id = VALUES(pet_shop_id);

-- 4) Funcionários vinculados aos petshops
INSERT INTO funcionario (id, nome, email, senha, telefone, cargo, especialidade, pet_shop_id) VALUES
(100, 'Carlos Lima', 'carlos@petshop.com', '123456', '(47) 99999-4444', 'Tosador', 'Tosa higiênica e tosa na tesoura', 100),
(101, 'Fernanda Costa', 'fernanda@petshop.com', '123456', '(47) 99999-5555', 'Banhista', 'Banho, hidratação e secagem', 100),
(102, 'Roberto Alves', 'roberto@petshop.com', '123456', '(47) 99999-6666', 'Tosador', 'Tosa de cães pequenos', 101),
(103, 'Juliana Rocha', 'juliana@petshop.com', '123456', '(47) 99999-8888', 'Atendente', 'Atendimento e agendamentos', 101)
ON DUPLICATE KEY UPDATE
  nome = VALUES(nome), email = VALUES(email), senha = VALUES(senha), telefone = VALUES(telefone),
  cargo = VALUES(cargo), especialidade = VALUES(especialidade), pet_shop_id = VALUES(pet_shop_id);

-- 5) Pets vinculados aos clientes
INSERT INTO pet (id, nome, especie, raca, idade, peso, observacoes, cliente_id) VALUES
(100, 'Thor', 'CACHORRO', 'Golden Retriever', 4, 28.50, 'Muito dócil, gosta de água.', 100),
(101, 'Mel', 'CACHORRO', 'Shih-tzu', 3, 6.20, 'Possui sensibilidade na pele.', 100),
(102, 'Luna', 'GATO', 'Siamês', 2, 4.10, 'Não gosta de secador muito próximo.', 101),
(103, 'Bob', 'CACHORRO', 'Poodle', 7, 8.00, 'Preferência por tosa baixa.', 102),
(104, 'Nina', 'GATO', 'Sem raça definida', 1, 3.50, 'Primeiro banho no petshop.', 103),
(105, 'Apolo', 'CACHORRO', 'Bulldog Francês', 5, 12.40, 'Respiração sensível, evitar estresse.', 103)
ON DUPLICATE KEY UPDATE
  nome = VALUES(nome), especie = VALUES(especie), raca = VALUES(raca), idade = VALUES(idade),
  peso = VALUES(peso), observacoes = VALUES(observacoes), cliente_id = VALUES(cliente_id);

-- 6) Planos de assinatura vinculados aos petshops
INSERT INTO plano (id, nome, descricao, valor_mensal, quantidade_banhos, quantidade_tosas, ativo, pet_shop_id) VALUES
(100, 'Plano Banho Básico', 'Inclui 4 banhos mensais.', 119.90, 4, 0, TRUE, 100),
(101, 'Plano Completo', 'Inclui 4 banhos e 2 tosas mensais.', 189.90, 4, 2, TRUE, 100),
(102, 'Plano Premium', 'Inclui 8 banhos e 4 tosas mensais.', 299.90, 8, 4, TRUE, 100),
(103, 'Plano Gato Feliz', 'Inclui 2 banhos mensais para gatos.', 89.90, 2, 0, TRUE, 101),
(104, 'Plano Banho e Tosa Plus', 'Inclui 4 banhos e 1 tosa mensal.', 159.90, 4, 1, TRUE, 101)
ON DUPLICATE KEY UPDATE
  nome = VALUES(nome), descricao = VALUES(descricao), valor_mensal = VALUES(valor_mensal),
  quantidade_banhos = VALUES(quantidade_banhos), quantidade_tosas = VALUES(quantidade_tosas),
  ativo = VALUES(ativo), pet_shop_id = VALUES(pet_shop_id);

-- 7) Serviços avulsos
INSERT INTO servico (id, nome, tipo_servico, valor_avulso, duracao_minutos) VALUES
(100, 'Banho', 'BANHO', 50.00, 60),
(101, 'Tosa', 'TOSA', 70.00, 90),
(102, 'Banho e Tosa', 'BANHO_E_TOSA', 110.00, 120),
(103, 'Banho com Hidratação', 'BANHO', 75.00, 80),
(104, 'Tosa Higiênica', 'TOSA', 55.00, 60)
ON DUPLICATE KEY UPDATE
  nome = VALUES(nome), tipo_servico = VALUES(tipo_servico), valor_avulso = VALUES(valor_avulso),
  duracao_minutos = VALUES(duracao_minutos);

-- 8) Assinaturas vinculadas aos clientes e planos
INSERT INTO assinatura (id, cliente_id, plano_id, data_inicio, data_fim, status, banhos_restantes, tosas_restantes) VALUES
(100, 100, 101, '2026-06-01', '2026-06-30', 'ATIVA', 3, 1),
(101, 101, 103, '2026-06-01', '2026-06-30', 'ATIVA', 2, 0),
(102, 102, 104, '2026-06-05', '2026-07-04', 'ATIVA', 4, 1),
(103, 103, 102, '2026-05-01', '2026-05-31', 'EXPIRADA', 0, 0),
(104, 103, 100, '2026-06-01', '2026-06-30', 'ATIVA', 4, 0)
ON DUPLICATE KEY UPDATE
  cliente_id = VALUES(cliente_id), plano_id = VALUES(plano_id), data_inicio = VALUES(data_inicio),
  data_fim = VALUES(data_fim), status = VALUES(status), banhos_restantes = VALUES(banhos_restantes),
  tosas_restantes = VALUES(tosas_restantes);

-- 9) Pagamentos vinculados às assinaturas
INSERT INTO pagamento (id, assinatura_id, data_pagamento, data_vencimento, valor, status, forma_pagamento) VALUES
(100, 100, '2026-06-01', '2026-06-05', 189.90, 'PAGO', 'Cartão de crédito'),
(101, 101, '2026-06-02', '2026-06-05', 89.90, 'PAGO', 'Pix'),
(102, 102, NULL, '2026-06-15', 159.90, 'PENDENTE', 'Boleto'),
(103, 103, '2026-05-01', '2026-05-05', 299.90, 'PAGO', 'Cartão de crédito'),
(104, 104, NULL, '2026-06-10', 119.90, 'ATRASADO', 'Boleto')
ON DUPLICATE KEY UPDATE
  assinatura_id = VALUES(assinatura_id), data_pagamento = VALUES(data_pagamento),
  data_vencimento = VALUES(data_vencimento), valor = VALUES(valor), status = VALUES(status),
  forma_pagamento = VALUES(forma_pagamento);

-- 10) Agendamentos vinculados a pets, funcionários, serviços e, quando houver, assinaturas
INSERT INTO agendamento (id, pet_id, funcionario_id, servico_id, assinatura_id, data_hora, status, observacoes) VALUES
(100, 100, 101, 100, 100, '2026-06-13 09:00:00', 'AGENDADO', 'Banho mensal do plano.'),
(101, 101, 100, 102, 100, '2026-06-13 14:00:00', 'AGENDADO', 'Banho e tosa usando assinatura.'),
(102, 102, 102, 100, 101, '2026-06-14 10:30:00', 'AGENDADO', 'Banho para gato, atenção ao secador.'),
(103, 103, 102, 104, 102, '2026-06-10 16:00:00', 'CONCLUIDO', 'Tosa higiênica concluída.'),
(104, 104, 103, 100, 104, '2026-06-15 11:00:00', 'AGENDADO', 'Primeiro banho da Nina.'),
(105, 105, 101, 103, NULL, '2026-06-16 15:30:00', 'AGENDADO', 'Serviço avulso, sem assinatura.'),
(106, 100, 100, 101, 100, '2026-06-05 13:00:00', 'CANCELADO', 'Cliente solicitou cancelamento.')
ON DUPLICATE KEY UPDATE
  pet_id = VALUES(pet_id), funcionario_id = VALUES(funcionario_id), servico_id = VALUES(servico_id),
  assinatura_id = VALUES(assinatura_id), data_hora = VALUES(data_hora), status = VALUES(status),
  observacoes = VALUES(observacoes);

COMMIT;
