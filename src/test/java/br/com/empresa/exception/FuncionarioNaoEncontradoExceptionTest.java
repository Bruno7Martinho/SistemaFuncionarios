
package br.com.empresa.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import br.com.empresa.exception.FuncionarioJaCadastradoException;
import br.com.empresa.exception.FuncionarioNaoEncontradoException;

class FuncionarioNaoEncontradoExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {
        FuncionarioNaoEncontradoException exception =
                new FuncionarioNaoEncontradoException(
                        "Funcionário não encontrado."
                );

        assertEquals(
                "Funcionário não encontrado.",
                exception.getMessage()
        );
    }
}
