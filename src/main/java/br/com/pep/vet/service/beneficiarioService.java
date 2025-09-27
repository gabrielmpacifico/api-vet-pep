package br.com.pep.vet.service;

import java.util.List;
import java.util.Optional;

import br.com.pep.vet.responseDTO.beneficiarioResponseDTO;
import br.com.pep.vet.responseDTO.beneficiarioResumoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.pep.vet.entity.Beneficiario;
import br.com.pep.vet.exception.CpfException;
import br.com.pep.vet.repository.beneficiarioRepository;
import br.com.pep.vet.repository.planoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class beneficiarioService {

    private beneficiarioRepository beneficiariorepository;

    public beneficiarioService(beneficiarioRepository beneficiariorepository){
        this.beneficiariorepository = beneficiariorepository;
    }

    @Autowired
    private planoRepository planorepository;

    public beneficiarioResponseDTO adicionar(Beneficiario beneficiario){
        if(beneficiario.getCpf().length() != 11)
            throw CpfException.padrao(beneficiario.getCpf());

        return new beneficiarioResponseDTO(beneficiariorepository.save(beneficiario));
    }
    
    public beneficiarioResponseDTO listarByCpf(String cpf){
        if(cpf.length() != 11)
            throw CpfException.padrao(cpf);
        
        Beneficiario beneficiario = beneficiariorepository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return new beneficiarioResponseDTO(beneficiario);
    }

    public List<beneficiarioResumoDTO> listarByNome(String nome){
        return beneficiariorepository.buscarListaDeNomes(nome);
    }

    public Optional<Beneficiario> listarById(Long id){
        return beneficiariorepository.findById(id);
    }
    
    public beneficiarioResponseDTO atualizar(Beneficiario beneficiario){
        //Validar se existe permissão para atualizar esse registro
        //Trocar para findByIdAnd_campo que guarda o usuario
        if(beneficiario.getCpf().length() != 11)
            throw CpfException.padrao(beneficiario.getCpf());

        beneficiariorepository.findById(beneficiario.getIdBeneficiario()).orElseThrow(() -> new EntityNotFoundException("Usuário não existe"));
        return new beneficiarioResponseDTO(beneficiariorepository.save(beneficiario));
    }
    
    public void remover(Long id){
        beneficiariorepository.deleteById(id);
    }
}
