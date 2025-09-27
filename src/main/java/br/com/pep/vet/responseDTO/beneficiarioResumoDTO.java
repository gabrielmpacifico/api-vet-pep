package br.com.pep.vet.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class beneficiarioResumoDTO {
    private Long idBeneficiario;
    private String nome;
    private String cpf;
}
