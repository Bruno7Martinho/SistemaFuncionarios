
package br.com.empresa.model;

public class FuncionarioCLT extends Funcionario {

    private double beneficios;

    public FuncionarioCLT(int codigo, String nome, String cpf,
                          double salarioBase, double beneficios) {

        super(codigo, nome, cpf, salarioBase);

        if (beneficios < 0 || !Double.isFinite(beneficios)) {
            throw new IllegalArgumentException("Benefícios inválidos.");
        }

        this.beneficios = beneficios;
    }

    public double getBeneficios() {
        return beneficios;
    }

    public void setBeneficios(double beneficios) {

        if (beneficios < 0 || !Double.isFinite(beneficios)) {
            throw new IllegalArgumentException("Benefícios inválidos.");
        }

        this.beneficios = beneficios;
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + beneficios;
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.10;
    }
}
