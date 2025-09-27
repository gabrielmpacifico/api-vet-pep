package br.com.pep.vet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    public Beneficiario adicionar(Beneficiario beneficiario){        
        if(beneficiario.getCpf().length() != 11)
            throw CpfException.padrao(beneficiario.getCpf());

        validarPlano(beneficiario.getPlano(), "Plano enviado não existe ou não está ativo");
        return beneficiariorepository.save(beneficiario);
    }
    
    public Optional<Beneficiario> listarByCpf(String cpf){       
        if(cpf.length() != 11)
            throw CpfException.padrao(cpf);
        
        return beneficiariorepository.findByCpf(cpf);        
    }

    public List<Beneficiario> listarByNome(String nome){       
        return beneficiariorepository.findByNomeIgnoreCaseContainingOrderByNomeAsc(nome);
    }

    public Optional<Beneficiario> listarById(Long id){
        return beneficiariorepository.findById(id);
    }
    
    public Beneficiario atualizar(Beneficiario beneficiario){
        //Validar se existe permissão para atualizar esse registro
        //Trocar para findByIdAnd_campo que guarda o usuario
        beneficiariorepository.findById(beneficiario.getIdBeneficiario()).orElseThrow(() -> new EntityNotFoundException("Usuário não existe"));
        validarPlano(beneficiario.getPlano(), "Plano não existe ou não está mais ativo");
        return beneficiariorepository.save(beneficiario);
    }
    
    public void remover(Long id){
        beneficiariorepository.deleteById(id);
    }

    public void validarPlano(Integer plano, String erro){
        if(plano != null){
            planorepository.findByIdPlanoAndAtivo(Long.valueOf(plano), "S")
            .orElseThrow(() -> new EntityNotFoundException(erro));
        }
    }
}
