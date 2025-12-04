package com.example.crudSpring.projetoCRUD.CONTROLLER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.crudSpring.projetoCRUD.ENTITY.Funcionario;
import com.example.crudSpring.projetoCRUD.SERVICE.EmpresaService;
import com.example.crudSpring.projetoCRUD.SERVICE.FuncionarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/funcionarioCTR")

public class FuncionarioController {

    @Autowired
    private FuncionarioService ligacaoFuncionarioService;
    @Autowired
    private EmpresaService ligacaoEmpresaService;

    @GetMapping("/listarFunc")
    public String listarTodosFuncionarios(Model oModel) {
        oModel.addAttribute("funcionarios", ligacaoFuncionarioService.listarTodosFuncionarios());
        oModel.addAttribute("empresa", ligacaoEmpresaService.findAll());
        return "listarFuncionario";
    }

    @GetMapping("/deletarFuncionario/{id}")
    public String apagarFuncionario(@PathVariable("id") Long id) {

        ligacaoFuncionarioService.deletarFuncionario(id);
        return "redirect:/funcionarioCTR/listarFunc";
    }

    @GetMapping("/formFuncionario")
    public String mostrarFormCadastro(Model oModel) {
        oModel.addAttribute("funcionario", new Funcionario());
        oModel.addAttribute("empresas", ligacaoEmpresaService.findAll());
        return "cadastrarFuncionario";
    }

    @PostMapping("/salvarFuncionario")
    public String cadastrarFuncionario(
            @ModelAttribute Funcionario objFuncionario) {
        ligacaoFuncionarioService.cadastFuncionario(objFuncionario);
        return "redirect:/funcionarioCTR/listarFunc";
    }

    @GetMapping("/formAtualizar/{id}")
    public String formAtualizarFuncionario (@PathVariable ("id") Long id, Model oModel){

        Funcionario funcionarioEncontrado = ligacaoFuncionarioService.buscarFuncionarioPorId(id).orElseThrow(
            () -> new IllegalArgumentException("Funcionario não encontrado"));

        oModel.addAttribute("funcionario",  funcionarioEncontrado);
        oModel.addAttribute("empresas", ligacaoEmpresaService.findAll());
             return "editarFuncionario";


    }

    @PostMapping("atualizarFuncionario/{id}")
    public String postMethodName(@PathVariable ("id")  Long id,
    @ModelAttribute Funcionario objFuncionarioAtualizado){
    
        ligacaoFuncionarioService.atualizarFuncionario(id, objFuncionarioAtualizado);
        
        
        return "redirect:/funcionarioCTR/listarFunc";
    }

        @GetMapping("/deletarFuncionario/{id}")
        public String apagarFuncionarioString(@PathVariable("id") Long id) {
            ligacaoFuncionarioService.deletarFuncionario(id);
    return "redirect:/funcionarioCTR/listarFunc";
        }

    @GetMapping("/formBuscarNome")
  public String mostrarFormBusca(Model oModel) {
    return "buscarFuncionarioNome";
  }
     
  @GetMapping("/buscarFuncionarioPorNome")
  public String executarBuscaPorNome(@RequestParam("nome") String nome_func, Model oModel) {
    if(nome_func != null && !nome_func.isEmpty()){
        oModel.addAttribute("empresaNome", 
        ligacaoFuncionarioService.buscarFuncionarioPorNome(nome_func));
    }
      return "buscarFuncionarioPorNome";

}

}


    
    

       
    
    

