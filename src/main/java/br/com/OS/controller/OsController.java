package br.com.OS.controller;

import br.com.OS.model.OS;
import br.com.OS.model.User;
import br.com.OS.repository.OsRepository;
import br.com.OS.util.FileUploadUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Date;

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
            RedirectAttributes redirectAttributes,
            @RequestParam("foto") MultipartFile multipartFile, @AuthenticationPrincipal User user) throws IOException {

        // Verifica se há erros de validação
        if(result.hasErrors()){
            return "os/form-inserir";
        }

        String extensao = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

        os.setData(new Date());
        os.setFuncionario(user.getFirstName());

        osRepository.save(os);

        String fileName = os.getId() + "." + extensao;

        os.setImagem(fileName);

        osRepository.save(os);

        String uploadPasta =  "src/main/resources/static/assets/img/fotos-os";

        FileUploadUtil.saveFile(uploadPasta, fileName, multipartFile);



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