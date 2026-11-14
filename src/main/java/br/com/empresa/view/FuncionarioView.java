
package br.com.empresa.view;

import br.com.empresa.model.*;
import br.com.empresa.service.FuncionarioService;
import br.com.empresa.service.FolhaPagamentoService;

import java.util.List;
import java.util.Scanner;

public class FuncionarioView {

    private final Scanner scanner = new Scanner(System.in);
    private final FuncionarioService funcionarioService;
    private final FolhaPagamentoService folhaService;

    public FuncionarioView(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
        this.folhaService = new FolhaPagamentoService(funcionarioService);
    }

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE FUNCIONÁRIOS =====");
            System.out.println("1 - Cadastrar funcionário");
            System.out.println("2 - Listar funcionários");
            System.out.println("3 - Buscar funcionário");
            System.out.println("4 - Excluir funcionário");
            System.out.println("5 - Consultar folha de pagamento");
            System.out.println("6 - Exibir relatório");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = lerInteiro();

            try {
                switch (opcao) {
                    case 1 -> cadastrarFuncionario();
                    case 2 -> listarFuncionarios();
                    case 3 -> buscarFuncionario();
                    case 4 -> excluirFuncionario();
                    case 5 -> consultarFolha();
                    case 6 -> exibirRelatorio();
                    case 0 -> System.out.println("Sistema encerrado!");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } while (opcao != 0);
    }

    private void cadastrarFuncionario() {
        System.out.println("\n=== CADASTRO DE FUNCIONÁRIO ===");

        System.out.print("Código: ");
        int codigo = lerInteiro();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF (somente números): ");
        String cpf = scanner.nextLine();

        System.out.println("Tipo de funcionário:");
        System.out.println("1 - CLT");
        System.out.println("2 - PJ");
        System.out.println("3 - Comissionado");
        System.out.print("Escolha: ");

        int tipo = lerInteiro();

        Funcionario funcionario;

        switch (tipo) {
            case 1 -> {
                System.out.print("Salário base: ");
                double salario = lerDouble();

                System.out.print("Benefícios: ");
                double beneficios = lerDouble();

                funcionario = new FuncionarioCLT(
                        codigo, nome, cpf, salario, beneficios
                );
            }

            case 2 -> {
                System.out.print("Valor por projeto: ");
                double valor = lerDouble();

                System.out.print("Quantidade de projetos: ");
                int quantidade = lerInteiro();

                funcionario = new FuncionarioPJ(
                        codigo, nome, cpf, valor, quantidade
                );
            }

            case 3 -> {
                System.out.print("Salário base: ");
                double salario = lerDouble();

                System.out.print("Total de vendas: ");
                double vendas = lerDouble();

                System.out.print("Percentual de comissão: ");
                double percentual = lerDouble();

                funcionario = new FuncionarioComissionado(
                        codigo, nome, cpf, salario, vendas, percentual
                );
            }

            default -> {
                System.out.println("Tipo inválido!");
                return;
            }
        }

        funcionarioService.cadastrar(funcionario);
        System.out.println("Funcionário cadastrado com sucesso!");
    }

    private void listarFuncionarios() {
        List<Funcionario> funcionarios =
                funcionarioService.listarFuncionarios();

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\n=== FUNCIONÁRIOS CADASTRADOS ===");

        for (Funcionario funcionario : funcionarios) {
            System.out.println(
                    "Código: " + funcionario.getCodigo()
                            + " | Nome: " + funcionario.getNome()
                            + " | CPF: " + funcionario.getCpf()
                            + " | Remuneração: R$ "
                            + String.format("%.2f", funcionario.calcularRemuneracao())
            );
        }
    }

    private void buscarFuncionario() {
        System.out.println("\n=== BUSCAR FUNCIONÁRIO ===");
        System.out.println("1 - Por código");
        System.out.println("2 - Por CPF");
        System.out.println("3 - Por nome");
        System.out.print("Escolha: ");

        int tipo = lerInteiro();

        switch (tipo) {
            case 1 -> {
                System.out.print("Código: ");
                Funcionario f = funcionarioService.buscarFuncionario(lerInteiro());
                exibirFuncionario(f);
            }

            case 2 -> {
                System.out.print("CPF: ");
                Funcionario f = funcionarioService.buscarFuncionario(scanner.nextLine());
                exibirFuncionario(f);
            }

            case 3 -> {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();

                List<Funcionario> encontrados =
                        funcionarioService.buscarFuncionario(nome, false);

                if (encontrados.isEmpty()) {
                    System.out.println("Nenhum funcionário encontrado.");
                } else {
                    encontrados.forEach(this::exibirFuncionario);
                }
            }

            default -> System.out.println("Opção inválida!");
        }
    }

    private void exibirFuncionario(Funcionario f) {
        System.out.println("\nCódigo: " + f.getCodigo());
        System.out.println("Nome: " + f.getNome());
        System.out.println("CPF: " + f.getCpf());
        System.out.println("Remuneração: R$ " + f.calcularRemuneracao());
        System.out.println("Tributos: R$ " + f.calcularTributos());
    }

    private void excluirFuncionario() {
        System.out.print("Informe o código do funcionário: ");
        int codigo = lerInteiro();

        funcionarioService.excluir(codigo);

        System.out.println("Funcionário excluído com sucesso!");
    }

    private void consultarFolha() {
        System.out.printf(
                "Total da folha: R$ %.2f%n",
                folhaService.calcularTotalFolha()
        );

        System.out.printf(
                "Total de tributos: R$ %.2f%n",
                folhaService.calcularTotalTributos()
        );
    }

    private void exibirRelatorio() {
        System.out.println(folhaService.gerarRelatorio());
    }

    private int lerInteiro() {
        return Integer.parseInt(scanner.nextLine());
    }

    private double lerDouble() {
        return Double.parseDouble(
                scanner.nextLine().replace(",", ".")
        );
    }
}
