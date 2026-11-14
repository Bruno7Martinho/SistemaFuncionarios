
package br.com.empresa.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioPolimorfismoTest {

    @Test
    void deveCalcularRemuneracaoDeDiferentesTiposDeFuncionario() {

        Funcionario clt = new FuncionarioCLT(
                1, "Bruno", "52998224725", 3000, 500
        );

        Funcionario pj = new FuncionarioPJ(
                2, "Carlos", "11144477735", 1500, 3
        );

        Funcionario comissionado = new FuncionarioComissionado(
                3, "Maria", "12345678909", 2000, 10000, 5
        );

        assertEquals(3500, clt.calcularRemuneracao());
        assertEquals(4500, pj.calcularRemuneracao());
        assertEquals(2500, comissionado.calcularRemuneracao());
    }
}
