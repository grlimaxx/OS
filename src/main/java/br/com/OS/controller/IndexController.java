package br.com.OS.controller;

import br.com.OS.repository.OsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private OsRepository servicoRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("qtdServicos", servicoRepository.count());
        return "index";
    }

}
