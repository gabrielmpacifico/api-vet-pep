package br.com.pep.vet.responseDTO;

import br.com.pep.vet.entity.Pet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class petResponseDTO {

    private Long idPet;
    private Long idBeneficiario;
    private String nome;
    private String raça;
    private LocalDateTime nascimento;
    private String pelagem;
    private String sexo;
    private boolean vivo;
    private Integer plano;
    private String especie;

    public petResponseDTO(Pet pet){
        this.idPet = pet.getIdPet();
        this.idBeneficiario = pet.getBeneficiario().getIdBeneficiario();
        this.nome = pet.getNome();
        this.raça = pet.getRaça();
        this.nascimento = pet.getNascimento();
        this.pelagem = pet.getPelagem();
        this.sexo = pet.getSexo();
        this.vivo = pet.isVivo();
        this.plano = pet.getPlano();
        this.especie = pet.getEspecie();
    }

}
