
package br.com.empresa.model;

public class FuncionarioPJ extends Funcionario {

    private double valorPorProjeto;
    private int quantidadeProjetos;

    public FuncionarioPJ(
            int codigo,
            String nome,
            String cpf,
            double valorPorProjeto,
            int quantidadeProjetos) {

        super(codigo, nome, cpf, 0);

        if (valorPorProjeto < 0 || !Double.isFinite(valorPorProjeto)) {
            throw new IllegalArgumentException("Valor por projeto inválido.");
        }

        if (quantidadeProjetos < 0) {
            throw new IllegalArgumentException("Quantidade de projetos inválida.");
        }

        this.valorPorProjeto = valorPorProjeto;
        this.quantidadeProjetos = quantidadeProjetos;
    }

    public double getValorPorProjeto() {
        return valorPorProjeto;
    }

    public int getQuantidadeProjetos() {
        return quantidadeProjetos;
    }

    public void setValorPorProjeto(double valorPorProjeto) {
        if (valorPorProjeto < 0 || !Double.isFinite(valorPorProjeto)) {
            throw new IllegalArgumentException("Valor por projeto inválido.");
        }

        this.valorPorProjeto = valorPorProjeto;
    }

    public void setQuantidadeProjetos(int quantidadeProjetos) {
        if (quantidadeProjetos < 0) {
            throw new IllegalArgumentException("Quantidade de projetos inválida.");
        }

        this.quantidadeProjetos = quantidadeProjetos;
    }

    @Override
    public double calcularRemuneracao() {
        return valorPorProjeto * quantidadeProjetos;
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.06;
    }
}
