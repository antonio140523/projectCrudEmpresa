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
}
