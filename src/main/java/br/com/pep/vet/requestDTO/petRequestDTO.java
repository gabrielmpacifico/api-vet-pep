package br.com.pep.vet.requestDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class petRequestDTO {

    private Long idPet;
    private Long idBeneficiario;
    private String nome;
    private String raça;
    private String especie;
    private LocalDateTime nascimento;
    private String pelagem;
    private String sexo;
    private boolean vivo;
    private Integer plano;

}
