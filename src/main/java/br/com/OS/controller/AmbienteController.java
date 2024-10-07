package br.com.OS.controller;

import br.com.OS.model.Ambiente;
import br.com.OS.model.Funcionario;
import br.com.OS.repository.AmbienteRepository;
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
@RequestMapping("/ambientes")
public class AmbienteController {

    @Autowired
    private AmbienteRepository ambienteRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("ambientes", ambienteRepository.findAll());
        //return "ambientes/index";
        return "ambientes/index";
    }

    @GetMapping("/form-inserir")
    public String formInserir(Model model){
        model.addAttribute("ambiente", new Ambiente());
        return "ambientes/form-inserir";
    }

    @GetMapping("/alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id,Model model){
        Ambiente ambiente = ambienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ambiente inválido: " + id));
        model.addAttribute("ambiente", ambiente);
        return "ambientes/form-alterar";
    }

    @PostMapping("/salvar")
    public String salvar(
            @Valid Ambiente ambiente,
            BindingResult result,
            RedirectAttributes redirectAttributes){

        // Verifica se há erros de validação
        if(result.hasErrors()){
            return "ambiente/form-inserir";
        }

        ambienteRepository.save(ambiente);


        redirectAttributes.addFlashAttribute("mensagem", "Ambiente salvo com sucesso!");
        return "redirect:/ambientes";
    }

    // Método para excluir o jogador
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Ambiente ambiente = ambienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ambiente inválido: " + id));
        ambienteRepository.delete(ambiente);
        redirectAttributes.addFlashAttribute("mensagem", "Ambiente excluído com sucesso!");
        return "redirect:/ambientes";
    }
}