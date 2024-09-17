package br.com.OS.controller;

import br.com.OS.model.EnderecoPessoa;
import br.com.OS.model.Funcionario;
import br.com.OS.repository.FuncionarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/OS")
public class OsController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("OS", funcionarioRepository.findAll());
        return "OS/index";
    }

    @GetMapping("/form-inserir")
    public String formInserir(Model model){
        model.addAttribute("OS", new Funcionario());
        return "OS/form-inserir";
    }

    @GetMapping("/alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id,Model model){
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionário inválido: " + id));
        model.addAttribute("OS", funcionario);
        return "OS/form-alterar";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid Funcionario funcionario,
            BindingResult result,
            RedirectAttributes redirectAttributes){

        // Verifica se há erros de validação
        if(result.hasErrors()){
            return "OS/form-inserir";
        }

        funcionarioRepository.save(funcionario);
        redirectAttributes.addFlashAttribute("mensagem", "OS salvo com sucesso!");
        return "redirect:/OS";
    }



    // Método para excluir o jogador
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ordem de serviço inválida: " + id));
        funcionarioRepository.delete(funcionario);
        redirectAttributes.addFlashAttribute("mensagem", "Ordem de serviço excluída com sucesso!");
        return "redirect:/OS";
    }


}