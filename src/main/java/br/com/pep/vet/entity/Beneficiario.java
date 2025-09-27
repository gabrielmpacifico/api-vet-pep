package br.com.pep.vet.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
    
    private String nome;
    
    @Column(name = "nome_social")
    private String nomeSocial;

    @Email(message = "Deve conter um email válido")
    private String email;

    @Column(length = 11)
    private String celular;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    
    @Column(nullable = true)
    private Integer plano;
    
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = ("tipo_pessoa"), nullable = false, length = 1)
    private String tipoPessoa;
    
    @Column(nullable = false)
    private String nacionalidade;
    
    @Column(nullable = false)
    private LocalDateTime nascimento;

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
    }

 
}