package br.com.OS.api;

import br.com.OS.repository.OsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicosRestController {

    @Autowired
    private OsRepository servicoRepository;

    @GetMapping("/servicos-ano-corrente")
    public List<Object[]> servicosAnoCorrente(){
        return servicoRepository.imprimirServicosPorMes();
    }

}