
# Sistema de Gerenciamento de Funcionários e Folha de Pagamento

## Sobre o projeto

Sistema desenvolvido em Java para gerenciamento de funcionários e cálculo de folha de pagamento, como atividade avaliativa da disciplina de Programação Orientada a Objetos.

O projeto utiliza conceitos de Programação Orientada a Objetos, arquitetura em camadas, tratamento de exceções e testes automatizados com JUnit 5.

## Tecnologias utilizadas

- Java 17
- Maven
- JUnit 5
- IntelliJ IDEA
- Git e GitHub

## Funcionalidades

- Cadastro de funcionários CLT, PJ e comissionados.
- Listagem de funcionários.
- Busca por código, CPF e nome.
- Exclusão de funcionários.
- Cálculo individual de remuneração.
- Cálculo de tributos.
- Cálculo do total da folha de pagamento.
- Geração de relatório.
- Validação de dados e prevenção de cadastros duplicados.

## Regras de remuneração

### Funcionário CLT
Remuneração = Salário-base + Benefícios.

### Funcionário PJ
Remuneração = Valor por projeto × Quantidade de projetos.

### Funcionário Comissionado
Remuneração = Salário-base + (Vendas × Percentual de comissão / 100).

## Arquitetura

O projeto está organizado em camadas:

- **model:** entidades, abstrações e interfaces.
- **repository:** armazenamento e recuperação dos funcionários.
- **service:** regras de negócio e cálculos.
- **view:** interação com o usuário.
- **exception:** exceções personalizadas.

## Como executar

1. Clone o repositório.
2. Abra o projeto no IntelliJ IDEA.
3. Aguarde o Maven carregar as dependências.
4. Execute a classe `Main.java`.

## Como executar os testes

Pelo terminal, na pasta raiz do projeto, execute:

```bash
mvn test
```

Os testes automatizados utilizam JUnit 5.

## Testes

O projeto possui testes para:

- Cálculos de remuneração.
- Validação de dados.
- Cadastro e busca de funcionários.
- Prevenção de duplicidade.
- Exclusão.
- Exceções personalizadas.
- Polimorfismo.
- Cálculos da folha de pagamento.

## Armazenamento

Os funcionários são armazenados em memória utilizando coleções Java (`HashMap`). Os dados não são persistidos após o encerramento da aplicação.

## Autor

Desenvolvido por Bruno Model Martinho como atividade acadêmica de Programação Orientada a Objetos em Java.

