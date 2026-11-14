
package br.com.empresa.model;

public class FuncionarioComissionado extends Funcionario {

    private double vendas;
    private double percentualComissao;

    public FuncionarioComissionado(
            int codigo,
            String nome,
            String cpf,
            double salarioBase,
            double vendas,
            double percentualComissao) {

        super(codigo, nome, cpf, salarioBase);

        if (vendas < 0 || !Double.isFinite(vendas)) {
            throw new IllegalArgumentException("Valor de vendas inválido.");
        }

        if (percentualComissao < 0 ||
                percentualComissao > 100 ||
                !Double.isFinite(percentualComissao)) {
            throw new IllegalArgumentException("Percentual de comissão inválido.");
        }

        this.vendas = vendas;
        this.percentualComissao = percentualComissao;
    }

    public double getVendas() {
        return vendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    public void setVendas(double vendas) {
        if (vendas < 0 || !Double.isFinite(vendas)) {
            throw new IllegalArgumentException("Valor de vendas inválido.");
        }

        this.vendas = vendas;
    }

    public void setPercentualComissao(double percentualComissao) {
        if (percentualComissao < 0 ||
                percentualComissao > 100 ||
                !Double.isFinite(percentualComissao)) {
            throw new IllegalArgumentException("Percentual de comissão inválido.");
        }

        this.percentualComissao = percentualComissao;
    }

    @Override
    public double calcularRemuneracao() {
        double comissao = vendas * (percentualComissao / 100);

        return getSalarioBase() + comissao;
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.08;
    }
}
