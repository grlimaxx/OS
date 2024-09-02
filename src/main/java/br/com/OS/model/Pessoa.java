package br.com.OS.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
    name = "tipoPessoa",
    length = 1,
    discriminatorType = DiscriminatorType.STRING
)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique=true)
    private Long id;


    @NotEmpty(message = "O CPF deve ser informado")
    @CPF(message = "O CPF informado é inválido")
    private String cpf;

    @NotEmpty(message = "O nome deve ser informado")
    private String nome;

    @OneToOne(cascade = CascadeType.ALL)
    private EnderecoPessoa enderecoPessoa;

    @NotEmpty(message = "O e-mail deve ser informado")
    @Email(message = "O e-mail informado é inválido")
    private String email;
    private String foto;
    private String naturalidade;
    private String nacionalidade;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
    private String senha;
    private Boolean sistema;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pessoa_id")
    private List<Telefone> telefones = new ArrayList<Telefone>();



}
