package br.com.pep.vet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plano")
public class Plano {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano")
    private Long idPlano;

    @Column(nullable = false, length = 150)
    private String descricao;
    
    @Column(nullable = false)
    private boolean ativo;

    @PrePersist
    protected void prePersist(){
        this.setAtivo(true);
    }

}
