
package br.com.empresa.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import br.com.empresa.exception.FuncionarioJaCadastradoException;
import br.com.empresa.exception.FuncionarioNaoEncontradoException;

class FuncionarioJaCadastradoExceptionTest {

    @Test
    void deveCriarExcecaoComMensagem() {
        FuncionarioJaCadastradoException exception =
                new FuncionarioJaCadastradoException(
                        "Funcionário já cadastrado."
                );

        assertEquals(
                "Funcionário já cadastrado.",
                exception.getMessage()
        );
    }
}
