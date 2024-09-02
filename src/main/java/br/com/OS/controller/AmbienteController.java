package br.com.OS.controller;

import br.com.OS.model.Ambiente;
import br.com.OS.model.EnderecoPessoa;
import br.com.OS.model.Funcionario;
import br.com.OS.repository.AmbienteRepository;
import br.com.OS.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ambientes")
public class AmbienteController {

    @Autowired
    private AmbienteRepository ambienteRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("ambientes", ambienteRepository.findAll());
        return "ambientes/index";
    }

    @GetMapping("/form-inserir")
    public String formInserir(Model model){
        model.addAttribute("ambiente", new Ambiente());
        return "ambientes/form-inserir";
    }
}