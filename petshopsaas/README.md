# PetShop SaaS Java Desktop

Projeto base **Java Project comum**, sem Maven, usando **Swing**, **JDBC** e **MySQL** para um SaaS de banho e tosa.

## O que está incluído

- Classes `model` para as entidades do UML.
- Classes `dao` com CRUD via JDBC.
- Classes `gui` em Swing para CRUD das entidades.
- Tela principal `MainFrame` com menu de navegação.
- Tela de agendamento para banho, tosa ou banho e tosa.
- Script `database.sql` para criar o banco MySQL.
- Arquivos `.project` e `.classpath` para importar como Java Project no Eclipse.

## Estrutura

```text
petshop-saas-java/
├── .project
├── .classpath
├── database.sql
├── lib/
│   └── README.txt
└── src/
    └── br/com/petshopsaas/
        ├── app/
        ├── dao/
        ├── db/
        ├── enums/
        ├── gui/
        └── model/
```

## Configuração no MySQL

1. Execute o arquivo `database.sql` no MySQL.
2. Abra `DatabaseConnection.java` e ajuste usuário/senha do MySQL:

```java
private static final String USER = "root";
private static final String PASSWORD = "root";
```

## Configuração do Connector/J

Este projeto não usa Maven. Por isso, o driver MySQL deve ser adicionado manualmente.

1. Baixe o **MySQL Connector/J** em: https://dev.mysql.com/downloads/connector/j/
2. Copie o arquivo `.jar` para a pasta `lib/`.
3. Se possível, deixe o nome assim:

```text
lib/mysql-connector-j-9.7.0.jar
```

Se usar outro nome de arquivo, ajuste o `.classpath` no Eclipse ou adicione o `.jar` manualmente em:

```text
Build Path > Configure Build Path > Libraries > Add JARs...
```

## Como importar no Eclipse

1. Vá em `File > Import...`.
2. Escolha `General > Existing Projects into Workspace`.
3. Selecione a pasta `petshop-saas-java`.
4. Confirme a importação.
5. Adicione o `.jar` do MySQL Connector/J no Build Path, caso o Eclipse ainda não reconheça.

## Como executar

Execute a classe:

```text
br.com.petshopsaas.app.Main
```

A tela principal abrirá com um menu superior:

- `Cadastros`: PetShops, Usuários, Clientes, Funcionários, Pets, Planos e Serviços.
- `Movimentos`: Assinaturas, Agendamentos e Pagamentos.
- `Sistema`: Tela inicial e sair.

## Observações

Esta é uma base acadêmica simples. As telas usam campos de ID para relacionamentos, por exemplo `Cliente ID`, `Pet ID`, `Serviço ID` e `Funcionário ID`. Para uma versão mais completa, o ideal seria trocar esses campos por `JComboBox` carregados do banco.
