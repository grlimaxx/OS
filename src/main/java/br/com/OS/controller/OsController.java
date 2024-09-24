package br.com.OS.controller;

import br.com.OS.model.OS;
import br.com.OS.repository.OsRepository;
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
@RequestMapping("/os")
public class OsController {

    @Autowired
    private OsRepository osRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("OS", osRepository.findAll());
        return "os/index";
    }

    @GetMapping("/form-inserir")
    public String formInserir(Model model){
        model.addAttribute("OS", new OS());
        return "os/form-inserir";
    }

    @GetMapping("/alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id,Model model){
        OS os = osRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionário inválido: " + id));
        model.addAttribute("OS", os);
        return "os/form-alterar";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid OS os,
            BindingResult result,
            RedirectAttributes redirectAttributes){

        // Verifica se há erros de validação
        if(result.hasErrors()){
            return "os/form-inserir";
        }

        osRepository.save(os);
        redirectAttributes.addFlashAttribute("mensagem", "OS salvo com sucesso!");
        return "redirect:/os";
    }



    // Método para excluir o jogador
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        OS os = osRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ordem de serviço inválida: " + id));
        osRepository.delete(os);
        redirectAttributes.addFlashAttribute("mensagem", "Ordem de serviço excluída com sucesso!");
        return "redirect:/os";
    }


}