package br.com.pep.vet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pep.vet.entity.Plano;
import br.com.pep.vet.repository.planoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class planoService {
    
    @Autowired
    planoRepository planorepository;

    public List<Plano> listarPlanosAtivos(){
            return planorepository.findByAtivoOrderByDescricaoAsc(true);
    }

    public List<Plano> listarPlanos(){
            return planorepository.findAllByOrderByIdPlanoDesc();
    }

    public List<Plano> adicionar(Plano plano){
        planorepository.save(plano);
        return listarPlanos();
    }

    public List<Plano> atualizar(Plano plano){
        //Quando tiver usuário de sistema vincular o plano ao usuário e trocar repository parafindByIdPlanoAnd_campo_do_usuario
        planorepository.findById(plano.getIdPlano()).orElseThrow(() -> new EntityNotFoundException("Plano a ser atualizado não existe"));
        planorepository.save(plano);
        return listarPlanos();
    }

}
