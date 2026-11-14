
package br.com.empresa.service;

import br.com.empresa.model.FuncionarioCLT;
import br.com.empresa.model.FuncionarioPJ;
import br.com.empresa.model.FuncionarioComissionado;
import br.com.empresa.repository.FuncionarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FolhaPagamentoServiceTest {

    private FuncionarioService funcionarioService;
    private FolhaPagamentoService folhaService;

    @BeforeEach
    void configurar() {
        FuncionarioRepository repository = new FuncionarioRepository();

        funcionarioService = new FuncionarioService(repository);
        folhaService = new FolhaPagamentoService(funcionarioService);
    }

    @Test
    void deveCalcularTotalDaFolha() {

        funcionarioService.cadastrar(
                new FuncionarioCLT(1, "Bruno", "52998224725", 3000, 500)
        );

        funcionarioService.cadastrar(
                new FuncionarioPJ(2, "Carlos", "11144477735", 1500, 3)
        );

        funcionarioService.cadastrar(
                new FuncionarioComissionado(3, "Maria", "12345678909", 2000, 10000, 5)
        );

        double total = folhaService.calcularTotalFolha();

        assertEquals(10500, total, 0.01);
    }

    @Test
    void deveCalcularTotalDeTributos() {

        funcionarioService.cadastrar(
                new FuncionarioCLT(1, "Bruno", "52998224725", 3000, 500)
        );

        funcionarioService.cadastrar(
                new FuncionarioPJ(2, "Carlos", "11144477735", 1500, 3)
        );

        funcionarioService.cadastrar(
                new FuncionarioComissionado(3, "Maria", "12345678909", 2000, 10000, 5)
        );

        double total = folhaService.calcularTotalTributos();

        assertEquals(820, total, 0.01);
    }

    @Test
    void deveGerarRelatorioComFuncionarios() {

        funcionarioService.cadastrar(
                new FuncionarioCLT(1, "Bruno", "52998224725", 3000, 500)
        );

        String relatorio = folhaService.gerarRelatorio();

        assertTrue(relatorio.contains("Bruno"));
        assertTrue(relatorio.contains("Total da folha"));
        assertTrue(relatorio.contains("3500,00"));
    }
}
