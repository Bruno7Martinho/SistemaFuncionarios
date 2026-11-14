
package br.com.empresa.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioTest {

    // Cria uma implementação concreta para testar a classe abstrata
    private Funcionario criarFuncionario(
            int codigo, String nome, String cpf, double salario) {

        return new Funcionario(codigo, nome, cpf, salario) {

            @Override
            public double calcularRemuneracao() {
                return getSalarioBase();
            }

            @Override
            public double calcularTributos() {
                return getSalarioBase() * 0.10;
            }
        };
    }

    @Test
    void deveCriarFuncionarioComDadosValidos() {
        Funcionario funcionario = criarFuncionario(
                1, "Bruno", "52998224725", 3000
        );

        assertEquals(1, funcionario.getCodigo());
        assertEquals("Bruno", funcionario.getNome());
        assertEquals("52998224725", funcionario.getCpf());
        assertEquals(3000, funcionario.getSalarioBase());
    }

    @Test
    void naoDeveAceitarCodigoInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> criarFuncionario(0, "Bruno", "52998224725", 3000)
        );
    }

    @Test
    void naoDeveAceitarNomeVazio() {
        assertThrows(
                IllegalArgumentException.class,
                () -> criarFuncionario(1, " ", "52998224725", 3000)
        );
    }

    @Test
    void naoDeveAceitarCpfInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> criarFuncionario(1, "Bruno", "12345", 3000)
        );
    }

    @Test
    void naoDeveAceitarSalarioNegativo() {
        assertThrows(
                IllegalArgumentException.class,
                () -> criarFuncionario(1, "Bruno", "52998224725", -100)
        );
    }

    @Test
    void deveAtualizarNomeESalario() {
        Funcionario funcionario = criarFuncionario(
                1, "Bruno", "52998224725", 3000
        );

        funcionario.setNome("Carlos");
        funcionario.setSalarioBase(4000);

        assertEquals("Carlos", funcionario.getNome());
        assertEquals(4000, funcionario.getSalarioBase());
    }

    @Test
    void naoDeveAceitarAlteracaoParaNomeVazio() {
        Funcionario funcionario = criarFuncionario(
                1, "Bruno", "52998224725", 3000
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> funcionario.setNome("")
        );
    }

    @Test
    void deveRetornarCpfComoIdentificador() {
        Funcionario funcionario = criarFuncionario(
                1, "Bruno", "52998224725", 3000
        );

        assertEquals("52998224725", funcionario.obterIdentificador());
    }
}
