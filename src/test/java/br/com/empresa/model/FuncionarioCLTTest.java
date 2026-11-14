
package br.com.empresa.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FuncionarioCLTTest {

    @Test
    void deveCalcularRemuneracaoCorretamente() {

        // Arrange - Preparar os dados
        FuncionarioCLT funcionario = new FuncionarioCLT(
                1,
                "Bruno",
                "52998224725",
                3000.00,
                500.00
        );

        // Act - Executar o método
        double resultado = funcionario.calcularRemuneracao();

        // Assert - Verificar o resultado
        assertEquals(3500.00, resultado, 0.001);
    }

    @Test
    void deveRetornarCpfComoIdentificador() {

        FuncionarioCLT funcionario = new FuncionarioCLT(
                1,
                "Bruno",
                "52998224725",
                3000.00,
                500.00
        );

        assertEquals("52998224725", funcionario.obterIdentificador());
    }

    @Test
    void deveCalcularTributosCorretamente() {

        FuncionarioCLT funcionario = new FuncionarioCLT(
                1,
                "Bruno",
                "52998224725",
                3000.00,
                500.00
        );

        double resultado = funcionario.calcularTributos();

        assertEquals(350.00, resultado, 0.001);
    }
}
