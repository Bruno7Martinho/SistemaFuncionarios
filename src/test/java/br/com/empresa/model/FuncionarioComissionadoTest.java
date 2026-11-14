
package br.com.empresa.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FuncionarioComissionadoTest {

    @Test
    void deveCalcularRemuneracaoCorretamente() {

        // Arrange
        FuncionarioComissionado funcionario =
                new FuncionarioComissionado(
                        3,
                        "Bruno",
                        "52998224725",
                        2000.00,
                        10000.00,
                        5
                );

        // Act
        double resultado = funcionario.calcularRemuneracao();

        // Assert
        assertEquals(2500.00, resultado, 0.001);
    }

    @Test
    void naoDeveAceitarComissaoAcimaDe100Porcento() {

        assertThrows(IllegalArgumentException.class, () -> {
            new FuncionarioComissionado(
                    3,
                    "Bruno",
                    "52998224725",
                    2000.00,
                    10000.00,
                    150
            );
        });
    }

    @Test
    void deveCalcularTributosCorretamente() {

        FuncionarioComissionado funcionario =
                new FuncionarioComissionado(
                        3,
                        "Bruno",
                        "52998224725",
                        2000.00,
                        10000.00,
                        5
                );

        double resultado = funcionario.calcularTributos();

        assertEquals(200.00, resultado, 0.001);
    }
}
