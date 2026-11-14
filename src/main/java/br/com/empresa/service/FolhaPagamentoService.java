
package br.com.empresa.service;

import br.com.empresa.model.Funcionario;
import br.com.empresa.model.Tributavel;

import java.util.List;
import java.util.Locale;

public class FolhaPagamentoService {

    private final FuncionarioService funcionarioService;

    public FolhaPagamentoService(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    // Calcula o total da folha de pagamento
    public double calcularTotalFolha() {
        return funcionarioService.listarFuncionarios()
                .stream()
                .mapToDouble(Funcionario::calcularRemuneracao)
                .sum();
    }

    // Calcula o total de tributos
    public double calcularTotalTributos() {
        return funcionarioService.listarFuncionarios()
                .stream()
                .mapToDouble(Tributavel::calcularTributos)
                .sum();
    }

    // Gera relatório dos funcionários
    public String gerarRelatorio() {
        List<Funcionario> funcionarios =
                funcionarioService.listarFuncionarios();

        StringBuilder relatorio = new StringBuilder();

        relatorio.append("===== FOLHA DE PAGAMENTO =====\n");

        for (Funcionario funcionario : funcionarios) {
            relatorio.append("Código: ")
                    .append(funcionario.getCodigo())
                    .append(" | Nome: ")
                    .append(funcionario.getNome())
                    .append(" | Remuneração: R$ ")
                    .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", funcionario.calcularRemuneracao()))
                    .append("\n");
        }

        relatorio.append("==============================\n");
        relatorio.append("Total da folha: R$ ")
                .append(String.format(Locale.forLanguageTag("pt-BR"), "%.2f", calcularTotalFolha()));

        return relatorio.toString();
    }
}
