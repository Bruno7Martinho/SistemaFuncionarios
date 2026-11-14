
package br.com.empresa.view;

import br.com.empresa.repository.FuncionarioRepository;
import br.com.empresa.service.FuncionarioService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioViewTest {

    private InputStream entradaOriginal;
    private PrintStream saidaOriginal;

    private ByteArrayOutputStream saidaCapturada;

    @BeforeEach
    void configurar() {
        entradaOriginal = System.in;
        saidaOriginal = System.out;

        saidaCapturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaCapturada));
    }

    @AfterEach
    void restaurar() {
        System.setIn(entradaOriginal);
        System.setOut(saidaOriginal);
    }

    private FuncionarioView criarView(String entrada) {
        System.setIn(new ByteArrayInputStream(
                entrada.getBytes(StandardCharsets.UTF_8)
        ));

        FuncionarioService service =
                new FuncionarioService(new FuncionarioRepository());

        return new FuncionarioView(service);
    }

    private String obterSaida() {
        return saidaCapturada.toString();
    }

    @Test
    void deveExibirMenuEEncerrarSistema() {
        FuncionarioView view = criarView("0\n");

        view.iniciar();

        assertTrue(obterSaida().contains(
                "SISTEMA DE FUNCIONÁRIOS"
        ));

        assertTrue(obterSaida().contains(
                "Sistema encerrado!"
        ));
    }

    @Test
    void deveExibirMensagemQuandoNaoExistemFuncionarios() {
        FuncionarioView view = criarView("2\n0\n");

        view.iniciar();

        assertTrue(obterSaida().contains(
                "Nenhum funcionário cadastrado."
        ));
    }

    @Test
    void deveCadastrarFuncionarioCLTComSucesso() {
        String entrada = """
                1
                101
                Ana Silva
                12345678901
                1
                3000
                500
                2
                0
                """;

        FuncionarioView view = criarView(entrada);

        view.iniciar();

        assertTrue(obterSaida().contains(
                "Funcionário cadastrado com sucesso!"
        ));

        assertTrue(obterSaida().contains("Ana Silva"));

        assertTrue(obterSaida().contains("3500"));
    }

    @Test
    void deveExibirMensagemQuandoOpcaoForInvalida() {
        FuncionarioView view = criarView("9\n0\n");

        view.iniciar();

        assertTrue(obterSaida().contains(
                "Opção inválida!"
        ));
    }

    @Test
    void deveExibirMensagemQuandoTipoDeFuncionarioForInvalido() {
        String entrada = """
                1
                102
                Carlos Silva
                12345678902
                9
                0
                """;

        FuncionarioView view = criarView(entrada);

        view.iniciar();

        assertTrue(obterSaida().contains(
                "Tipo inválido!"
        ));
    }
}
