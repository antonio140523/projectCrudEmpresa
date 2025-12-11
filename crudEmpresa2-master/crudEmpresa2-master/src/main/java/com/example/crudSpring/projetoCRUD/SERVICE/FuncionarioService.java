package com.example.crudSpring.projetoCRUD.SERVICE;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crudSpring.projetoCRUD.ENTITY.Funcionario;
import com.example.crudSpring.projetoCRUD.REPOSITORY.FuncionarioRepostory;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepostory ligacaoFuncionarioRepostory;

    public List<Funcionario> listarTodosFuncionarios(){
        return ligacaoFuncionarioRepostory.findAll();
    }
    public Funcionario cadastFuncionario(Funcionario dadosFuncionario){
        return ligacaoFuncionarioRepostory.save(dadosFuncionario);
    }
    public Optional<Funcionario> buscarFuncionarioPorId(Long id) {
        return ligacaoFuncionarioRepostory.findById(id);
    }
    public void deletarFuncionario(Long id) {
        ligacaoFuncionarioRepostory.deleteById(id);
    }
    @Transactional
    public Funcionario atualizarFuncionario(Long id, Funcionario dadosAtualizados) {
        Funcionario objtFuncionario = buscarFuncionarioPorId(id).orElseThrow(() ->
                                            new IllegalArgumentException("Funcionario nao encontrado"));
        objtFuncionario.setNome(dadosAtualizados.getNome());
        objtFuncionario.setSalario(dadosAtualizados.getSalario());
        objtFuncionario.setCargo(dadosAtualizados.getCargo());
        objtFuncionario.setIdentificadorEmpresa(dadosAtualizados.getIdentificadorEmpresa());

        return ligacaoFuncionarioRepostory.save(objtFuncionario);

    }

    public List<Funcionario> buscarFuncionarioNome(String nome_funcionario){
        return ligacaoFuncionarioRepostory.findByNomeContainingIgnoreCase(nome_funcionario);

    }

}

        
    