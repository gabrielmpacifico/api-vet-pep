package br.com.pep.vet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pep.vet.entity.Beneficiario;

public interface beneficiarioRepository extends JpaRepository<Beneficiario, Long> {
    
    List<Beneficiario> findByNomeIgnoreCaseContainingOrderByNomeAsc(String nome);

    Optional<Beneficiario> findByCpf(String cpf);

}
