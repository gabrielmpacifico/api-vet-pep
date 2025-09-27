package br.com.pep.vet.service;

import br.com.pep.vet.entity.Beneficiario;
import br.com.pep.vet.entity.Pet;
import br.com.pep.vet.repository.beneficiarioRepository;
import br.com.pep.vet.repository.petRepository;
import br.com.pep.vet.repository.planoRepository;
import br.com.pep.vet.requestDTO.petRequestDTO;
import br.com.pep.vet.responseDTO.petResponseDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class petService {

    @Autowired
    private petRepository petrepository;

    @Autowired
    private planoRepository planorepository;

    @Autowired
    private beneficiarioRepository beneficiariorepository;

    public petResponseDTO cadastrar(petRequestDTO data){
        validarPlano(data.getPlano(), "Plano enviado não existe ou não está ativo");
        Beneficiario benef = beneficiariorepository.findById(data.getIdBeneficiario()).orElseThrow(() -> new EntityNotFoundException("Tutor do pet não foi encontrado"));

        Pet pet = new Pet();
        pet.setBeneficiario(benef);
        pet.setNome(data.getNome());
        pet.setRaça(data.getRaça());
        pet.setEspecie(data.getEspecie());
        pet.setNascimento(data.getNascimento());
        pet.setPelagem(data.getPelagem());
        pet.setSexo(data.getSexo());
        pet.setVivo(data.isVivo());
        pet.setPlano(data.getPlano());

        return new petResponseDTO(petrepository.save(pet));
    }

    public petResponseDTO atualizar(petRequestDTO data){
        validarPlano(data.getPlano(), "Plano não existe ou não está mais ativo");
        Pet pet = petrepository.findById(data.getIdPet()).orElseThrow(() -> new EntityNotFoundException("O pet que está sendo atualizado não existe"));
        pet.setIdPet(data.getIdPet());
        pet.setNome(data.getNome());
        pet.setRaça(data.getRaça());
        pet.setEspecie(data.getEspecie());
        pet.setNascimento(data.getNascimento());
        pet.setPelagem(data.getPelagem());
        pet.setSexo(data.getSexo());
        pet.setVivo(data.isVivo());
        pet.setPlano(data.getPlano());

        return new petResponseDTO(petrepository.save(pet));
        //Validar se existe permissão para atualizar esse registro
        //Trocar para findByIdAnd_campo que guarda o usuario
    }

    public petResponseDTO listarById(Long id){
        Pet pet = petrepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));
        return new petResponseDTO(pet);
    }

    public List<petResponseDTO> listarByTutor(Long idTutor){
        return petrepository.findAllByBeneficiario_IdBeneficiario(idTutor)
                .stream()
                .map(petResponseDTO::new)
                .toList();
    }

    public void validarPlano(Integer plano, String erro){
        if(plano != null){
            planorepository.findByIdPlanoAndAtivo(Long.valueOf(plano), true)
                    .orElseThrow(() -> new EntityNotFoundException(erro));
        }
    }

}
