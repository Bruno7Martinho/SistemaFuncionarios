
package br.com.empresa.repository;

import br.com.empresa.model.Funcionario;
import br.com.empresa.model.FuncionarioCLT;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import br.com.empresa.exception.FuncionarioJaCadastradoException;
import br.com.empresa.exception.FuncionarioNaoEncontradoException;

class FuncionarioRepositoryTest {

    @Test
    void deveSalvarEBuscarFuncionarioPorCodigo() {
        // Arrange
        FuncionarioRepository repository = new FuncionarioRepository();

        FuncionarioCLT funcionario = new FuncionarioCLT(
                1, "Bruno", "52998224725", 3000, 500
        );

        // Act
        repository.salvar(funcionario);
        Funcionario resultado = repository.buscarPorCodigo(1);

        // Assert
        assertEquals("Bruno", resultado.getNome());
    }

    @Test
    void naoDevePermitirCodigoDuplicado() {
        FuncionarioRepository repository = new FuncionarioRepository();

        FuncionarioCLT primeiro = new FuncionarioCLT(
                1, "Bruno", "52998224725", 3000, 500
        );

        FuncionarioCLT segundo = new FuncionarioCLT(
                1, "Maria", "11144477735", 2500, 300
        );

        repository.salvar(primeiro);

        assertThrows(
                FuncionarioJaCadastradoException.class,
                () -> repository.salvar(segundo)
        );
    }

    @Test
    void deveBuscarFuncionarioPorCpf() {
        FuncionarioRepository repository = new FuncionarioRepository();

        FuncionarioCLT funcionario = new FuncionarioCLT(
                1, "Bruno", "52998224725", 3000, 500
        );

        repository.salvar(funcionario);

        Funcionario resultado = repository.buscarPorCpf("52998224725");

        assertEquals(1, resultado.getCodigo());
    }

    @Test
    void deveLancarExcecaoAoBuscarCodigoInexistente() {
        FuncionarioRepository repository = new FuncionarioRepository();

        assertThrows(
                FuncionarioNaoEncontradoException.class,
                () -> repository.buscarPorCodigo(99)
        );
    }
}
