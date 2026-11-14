
package br.com.empresa.service;

import br.com.empresa.model.Funcionario;
import br.com.empresa.model.FuncionarioCLT;
import br.com.empresa.repository.FuncionarioRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import br.com.empresa.exception.FuncionarioJaCadastradoException;

import static org.junit.jupiter.api.Assertions.*;


class FuncionarioServiceTest {

    private FuncionarioService service;

    @BeforeEach
    void configurar() {
        FuncionarioRepository repository = new FuncionarioRepository();
        service = new FuncionarioService(repository);
    }

    @Test
    void deveCadastrarFuncionario() {
        FuncionarioCLT funcionario = new FuncionarioCLT(
                1, "Bruno", "52998224725", 3000, 500
        );

        service.cadastrar(funcionario);

        assertEquals(1, service.listarFuncionarios().size());
    }

    @Test
    void naoDeveCadastrarCpfDuplicado() {
        FuncionarioCLT primeiro = new FuncionarioCLT(
                1, "Bruno", "52998224725", 3000, 500
        );

        FuncionarioCLT segundo = new FuncionarioCLT(
                2, "Maria", "52998224725", 2500, 300
        );

        service.cadastrar(primeiro);

        assertThrows(
                FuncionarioJaCadastradoException.class,
                () -> service.cadastrar(segundo)
        );
    }

    @Test
    void deveBuscarFuncionarioPorNomeExato() {
        service.cadastrar(new FuncionarioCLT(
                1, "Bruno Martinho", "52998224725", 3000, 500
        ));

        List<Funcionario> resultado =
                service.buscarFuncionario("Bruno Martinho", true);

        assertEquals(1, resultado.size());
    }

    @Test
    void deveBuscarFuncionarioPorParteDoNome() {
        service.cadastrar(new FuncionarioCLT(
                1, "Bruno Martinho", "52998224725", 3000, 500
        ));

        List<Funcionario> resultado =
                service.buscarFuncionario("Bruno", false);

        assertEquals(1, resultado.size());
    }

    @Test
    void deveExcluirFuncionario() {
        service.cadastrar(new FuncionarioCLT(
                1, "Bruno", "52998224725", 3000, 500
        ));

        service.excluir(1);

        assertTrue(service.listarFuncionarios().isEmpty());
    }
}
