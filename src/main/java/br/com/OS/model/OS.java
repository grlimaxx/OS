package br.com.OS.model;

import br.com.OS.enums.EnumPrioridade;
import br.com.OS.enums.EnumStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique=true)
    private Long id;
    private String funcionario;
    private String descricao;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date data;

    private String ambiente;
    private String imagem;
    private EnumStatus status;
    private EnumPrioridade prioridade;

}

