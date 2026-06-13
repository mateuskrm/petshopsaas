CREATE DATABASE IF NOT EXISTS petshop_saas DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE petshop_saas;

CREATE TABLE IF NOT EXISTS pet_shop (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  cnpj VARCHAR(20),
  endereco VARCHAR(200),
  telefone VARCHAR(30)
);

CREATE TABLE IF NOT EXISTS usuario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  email VARCHAR(120),
  senha VARCHAR(120),
  telefone VARCHAR(30),
  tipo_usuario ENUM('ADMIN','CLIENTE','FUNCIONARIO') NOT NULL
);

CREATE TABLE IF NOT EXISTS cliente (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  email VARCHAR(120),
  senha VARCHAR(120),
  telefone VARCHAR(30),
  cpf VARCHAR(20),
  endereco VARCHAR(200),
  pet_shop_id INT,
  CONSTRAINT fk_cliente_petshop FOREIGN KEY (pet_shop_id) REFERENCES pet_shop(id)
);

CREATE TABLE IF NOT EXISTS funcionario (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  email VARCHAR(120),
  senha VARCHAR(120),
  telefone VARCHAR(30),
  cargo VARCHAR(80),
  especialidade VARCHAR(120),
  pet_shop_id INT,
  CONSTRAINT fk_funcionario_petshop FOREIGN KEY (pet_shop_id) REFERENCES pet_shop(id)
);

CREATE TABLE IF NOT EXISTS pet (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  especie ENUM('CACHORRO','GATO','OUTRO') NOT NULL,
  raca VARCHAR(80),
  idade INT,
  peso DECIMAL(10,2),
  observacoes VARCHAR(255),
  cliente_id INT NOT NULL,
  CONSTRAINT fk_pet_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE IF NOT EXISTS plano (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  descricao VARCHAR(255),
  valor_mensal DECIMAL(10,2) NOT NULL,
  quantidade_banhos INT NOT NULL,
  quantidade_tosas INT NOT NULL,
  ativo BOOLEAN DEFAULT TRUE,
  pet_shop_id INT,
  CONSTRAINT fk_plano_petshop FOREIGN KEY (pet_shop_id) REFERENCES pet_shop(id)
);

CREATE TABLE IF NOT EXISTS assinatura (
  id INT AUTO_INCREMENT PRIMARY KEY,
  cliente_id INT NOT NULL,
  plano_id INT NOT NULL,
  data_inicio DATE NOT NULL,
  data_fim DATE NOT NULL,
  status ENUM('ATIVA','CANCELADA','SUSPENSA','EXPIRADA') NOT NULL,
  banhos_restantes INT NOT NULL,
  tosas_restantes INT NOT NULL,
  CONSTRAINT fk_assinatura_cliente FOREIGN KEY (cliente_id) REFERENCES cliente(id),
  CONSTRAINT fk_assinatura_plano FOREIGN KEY (plano_id) REFERENCES plano(id)
);

CREATE TABLE IF NOT EXISTS servico (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(120) NOT NULL,
  tipo_servico ENUM('BANHO','TOSA','BANHO_E_TOSA') NOT NULL,
  valor_avulso DECIMAL(10,2) NOT NULL,
  duracao_minutos INT NOT NULL
);

CREATE TABLE IF NOT EXISTS agendamento (
  id INT AUTO_INCREMENT PRIMARY KEY,
  pet_id INT NOT NULL,
  funcionario_id INT NOT NULL,
  servico_id INT NOT NULL,
  assinatura_id INT NULL,
  data_hora DATETIME NOT NULL,
  status ENUM('AGENDADO','CONCLUIDO','CANCELADO') NOT NULL,
  observacoes VARCHAR(255),
  CONSTRAINT fk_agendamento_pet FOREIGN KEY (pet_id) REFERENCES pet(id),
  CONSTRAINT fk_agendamento_funcionario FOREIGN KEY (funcionario_id) REFERENCES funcionario(id),
  CONSTRAINT fk_agendamento_servico FOREIGN KEY (servico_id) REFERENCES servico(id),
  CONSTRAINT fk_agendamento_assinatura FOREIGN KEY (assinatura_id) REFERENCES assinatura(id)
);

CREATE TABLE IF NOT EXISTS pagamento (
  id INT AUTO_INCREMENT PRIMARY KEY,
  assinatura_id INT NOT NULL,
  data_pagamento DATE NULL,
  data_vencimento DATE NOT NULL,
  valor DECIMAL(10,2) NOT NULL,
  status ENUM('PENDENTE','PAGO','ATRASADO','CANCELADO') NOT NULL,
  forma_pagamento VARCHAR(60),
  CONSTRAINT fk_pagamento_assinatura FOREIGN KEY (assinatura_id) REFERENCES assinatura(id)
);

INSERT INTO pet_shop (nome, cnpj, endereco, telefone) VALUES ('PetShop Modelo', '00.000.000/0001-00', 'Rua Exemplo, 123', '(47) 99999-9999');
INSERT INTO servico (nome, tipo_servico, valor_avulso, duracao_minutos) VALUES
('Banho', 'BANHO', 50.00, 60),
('Tosa', 'TOSA', 70.00, 90),
('Banho e Tosa', 'BANHO_E_TOSA', 110.00, 120);
