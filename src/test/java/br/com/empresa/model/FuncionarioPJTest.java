
package br.com.empresa.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FuncionarioPJTest {

    @Test
    void deveCalcularRemuneracaoCorretamente() {

        // Arrange
        FuncionarioPJ funcionario = new FuncionarioPJ(
                2,
                "Bruno",
                "52998224725",
                1500.00,
                3
        );

        // Act
        double resultado = funcionario.calcularRemuneracao();

        // Assert
        assertEquals(4500.00, resultado, 0.001);
    }

    @Test
    void naoDeveAceitarValorPorProjetoNegativo() {

        assertThrows(IllegalArgumentException.class, () -> {
            new FuncionarioPJ(
                    2,
                    "Bruno",
                    "52998224725",
                    -1500.00,
                    3
            );
        });
    }

    @Test
    void deveCalcularTributosCorretamente() {

        FuncionarioPJ funcionario = new FuncionarioPJ(
                2,
                "Bruno",
                "52998224725",
                1500.00,
                3
        );

        double resultado = funcionario.calcularTributos();

        assertEquals(270.00, resultado, 0.001);
    }
}
