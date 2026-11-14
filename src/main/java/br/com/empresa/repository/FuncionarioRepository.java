
package br.com.empresa.repository;

import br.com.empresa.model.Funcionario;
import br.com.empresa.exception.FuncionarioJaCadastradoException;
import br.com.empresa.exception.FuncionarioNaoEncontradoException;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepository {

    private final Map<Integer, Funcionario> funcionarios =
            new HashMap<>();

    // Cadastrar funcionário
    public void salvar(Funcionario funcionario) {

        if (funcionarios.containsKey(funcionario.getCodigo())) {
            throw new FuncionarioJaCadastradoException(
                    "Já existe um funcionário com esse código."
            );
        }

        funcionarios.put(funcionario.getCodigo(), funcionario);
    }

    // Buscar funcionário pelo código
    public Funcionario buscarPorCodigo(int codigo) {

        Funcionario funcionario = funcionarios.get(codigo);

        if (funcionario == null) {
            throw new FuncionarioNaoEncontradoException(
                    "Funcionário não encontrado."
            );
        }

        return funcionario;
    }

    // Buscar funcionário pelo CPF
    public Funcionario buscarPorCpf(String cpf) {

        for (Funcionario funcionario : funcionarios.values()) {

            if (funcionario.getCpf().equals(cpf)) {
                return funcionario;
            }
        }

        throw new FuncionarioNaoEncontradoException(
                "Funcionário não encontrado."
        );
    }

    // Listar todos os funcionários
    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionarios.values());
    }

    // Excluir funcionário
    public void excluir(int codigo) {

        if (!funcionarios.containsKey(codigo)) {
            throw new FuncionarioNaoEncontradoException(
                    "Funcionário não encontrado."
            );
        }

        funcionarios.remove(codigo);
    }
}
