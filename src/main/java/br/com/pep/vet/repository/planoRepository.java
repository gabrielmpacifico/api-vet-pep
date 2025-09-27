package br.com.pep.vet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pep.vet.entity.Plano;

public interface planoRepository extends JpaRepository<Plano, Long> {
    
    List<Plano> findByAtivoOrderByDescricaoAsc(boolean ativo);
    
    List<Plano> findAllByOrderByIdPlanoDesc();

    Optional<Plano> findByIdPlano(Long id);

    Optional<Plano> findByIdPlanoAndAtivo(Long id, boolean ativo);

}