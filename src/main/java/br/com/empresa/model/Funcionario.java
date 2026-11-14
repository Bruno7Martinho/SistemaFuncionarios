package br.com.empresa.model;

public abstract class Funcionario implements Identificavel, Tributavel {

    // 1. Atributos privados
    private final int codigo;
    private String nome;
    private final String cpf;
    private double salarioBase;

    // 2. Construtor
    public Funcionario(int codigo, String nome, String cpf, double salarioBase) {

        if (codigo <= 0) {
            throw new IllegalArgumentException("Código inválido.");
        }

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome obrigatório.");
        }

        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }

        if (salarioBase < 0 || !Double.isFinite(salarioBase)) {
            throw new IllegalArgumentException("Salário inválido.");
        }

        this.codigo = codigo;
        this.nome = nome.trim();
        this.cpf = cpf;
        this.salarioBase = salarioBase;
    }

    // 3. Getters
    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    // 4. Setters com validação
    public void setNome(String nome) {

        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome obrigatório.");
        }

        this.nome = nome.trim();
    }

    public void setSalarioBase(double salarioBase) {

        if (salarioBase < 0 || !Double.isFinite(salarioBase)) {
            throw new IllegalArgumentException("Salário inválido.");
        }

        this.salarioBase = salarioBase;
    }

    // 5. Método abstrato
    public abstract double calcularRemuneracao();

    //implementando  a interface Identificavel
    @Override
    public String obterIdentificador() {
        return getCpf();
    }

    // Método abstrato para cálculo de tributos
    @Override
    public abstract double calcularTributos();
}
