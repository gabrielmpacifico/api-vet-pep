package br.com.pep.vet.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pet")
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pet")
    private Long idPet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_beneficiario", nullable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Beneficiario beneficiario;

    @Column(length = 50, nullable = false)
    private String nome;

    private String raça;

    @Column(nullable = false)
    private String especie;

    private LocalDateTime nascimento;

    private String pelagem;

    private String sexo;

    @Column(nullable = false)
    private boolean vivo;

    @Column(nullable = true)
    private Integer plano;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @PrePersist
    protected void prePersist(){
        this.dataCadastro = LocalDateTime.now();
        this.setVivo(true);
    }
}
