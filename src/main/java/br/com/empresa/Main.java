
package br.com.empresa;

import br.com.empresa.repository.FuncionarioRepository;
import br.com.empresa.service.FuncionarioService;
import br.com.empresa.view.FuncionarioView;

public class Main {

    public static void main(String[] args) {

        // Cria o repositório
        FuncionarioRepository repository = new FuncionarioRepository();

        // Cria o serviço
        FuncionarioService funcionarioService =
                new FuncionarioService(repository);

        // Cria a interface
        FuncionarioView view =
                new FuncionarioView(funcionarioService);

        // Inicia o sistema
        view.iniciar();
    }
}
