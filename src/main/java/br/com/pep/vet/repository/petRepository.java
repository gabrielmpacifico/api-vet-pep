package br.com.pep.vet.repository;

import br.com.pep.vet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface petRepository extends JpaRepository<Pet, Long> {

    Optional<Pet> findById(Long id);

    List<Pet> findAllByBeneficiario_IdBeneficiario(Long id);

}
