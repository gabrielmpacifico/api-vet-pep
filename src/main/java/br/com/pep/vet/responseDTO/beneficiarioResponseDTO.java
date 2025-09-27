package br.com.pep.vet.responseDTO;

import br.com.pep.vet.entity.Beneficiario;
import br.com.pep.vet.entity.Pet;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class beneficiarioResponseDTO {

    private Long idBeneficiario;
    private String nome;
    private String email;
    private String celular;
    private String cpf;
    private String tipoPessoa;
    private String nacionalidade;
    private LocalDateTime nascimento;
    private List<petResponseDTO> pets;

    public beneficiarioResponseDTO(Beneficiario data){
        this.idBeneficiario = data.getIdBeneficiario();
        this.nome = data.getNome();
        this.email = data.getEmail();
        this.celular = data.getCelular();
        this.cpf = data.getCpf();
        this.tipoPessoa = data.getTipoPessoa();
        this.nacionalidade = data.getNacionalidade();
        this.nascimento = data.getNascimento();
        this.pets = data.getPets()
                .stream()
                .map(petResponseDTO::new)
                .toList();
    }

}
