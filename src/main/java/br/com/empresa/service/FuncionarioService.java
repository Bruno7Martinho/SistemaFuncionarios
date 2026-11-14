
package br.com.empresa.service;

import br.com.empresa.model.Funcionario;
import br.com.empresa.repository.FuncionarioRepository;
import br.com.empresa.exception.FuncionarioJaCadastradoException;

import java.util.List;


public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    // Cadastrar funcionário

    public void cadastrar(Funcionario funcionario) {

        boolean cpfJaExiste = repository.listarTodos()
                .stream()
                .anyMatch(f -> f.getCpf().equals(funcionario.getCpf()));

        if (cpfJaExiste) {
            throw new FuncionarioJaCadastradoException(
                    "Já existe um funcionário cadastrado com esse CPF."
            );
        }

        repository.salvar(funcionario);
    }


    // Buscar funcionário pelo código
    public Funcionario buscarFuncionario(int codigo) {
        return repository.buscarPorCodigo(codigo);
    }

    // Buscar funcionário pelo CPF
    public Funcionario buscarFuncionario(String cpf) {
        return repository.buscarPorCpf(cpf);
    }

    // Buscar funcionário pelo nome
    public List<Funcionario> buscarFuncionario(String nome, boolean buscaExata) {

        return repository.listarTodos()
                .stream()
                .filter(funcionario -> {
                    if (buscaExata) {
                        return funcionario.getNome().equalsIgnoreCase(nome);
                    }

                    return funcionario.getNome()
                            .toLowerCase()
                            .contains(nome.toLowerCase());
                })
                .toList();
    }

    // Listar todos os funcionários
    public List<Funcionario> listarFuncionarios() {
        return repository.listarTodos();
    }

    // Excluir funcionário
    public void excluir(int codigo) {
        repository.excluir(codigo);
    }
}
