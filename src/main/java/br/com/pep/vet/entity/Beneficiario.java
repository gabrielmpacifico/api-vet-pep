package br.com.pep.vet.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "beneficiario", uniqueConstraints = @UniqueConstraint(columnNames = {"cpf"}))
public class Beneficiario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficiario")
    private long idBeneficiario;

    @Column(nullable = false)
    private String nome;
    
    @Column(name = "nome_social")
    private String nomeSocial;

    @Email(message = "Deve conter um email válido")
    private String email;

    @Column(length = 11, nullable = false)
    private String celular;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = ("tipo_pessoa"), nullable = false, length = 1)
    private String tipoPessoa;
    
    @Column(nullable = false, length = 50)
    private String nacionalidade;
    
    @Column(nullable = false)
    private LocalDateTime nascimento;

    @OneToMany(mappedBy = "beneficiario")
    List<Pet> pets;

    @PrePersist
    protected void prePersist(){
        this.dataCadastro = LocalDateTime.now();
    }

    public Beneficiario(Beneficiario data){
        this.nome = data.nome;
        this.cpf = data.cpf;
        this.email = data.email;
        this.tipoPessoa = data.tipoPessoa;
        this.nacionalidade = data.nacionalidade;
        this.nascimento = data.nascimento;
        this.celular = data.celular;
    }

 
}