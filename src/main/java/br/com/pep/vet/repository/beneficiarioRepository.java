package br.com.pep.vet.repository;

import java.util.List;
import java.util.Optional;

import br.com.pep.vet.responseDTO.beneficiarioResumoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pep.vet.entity.Beneficiario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface beneficiarioRepository extends JpaRepository<Beneficiario, Long> {

    @Query("SELECT new br.com.pep.vet.responseDTO.beneficiarioResumoDTO(b.idBeneficiario, b.nome, b.cpf)   " +
            "FROM Beneficiario b                                                                    " +
            "WHERE UPPER(b.nome) LIKE UPPER(CONCAT('%', :nome, '%'))                                " +
            "ORDER BY b.nome ASC                                                                    ")
    List<beneficiarioResumoDTO> buscarListaDeNomes(@Param("nome") String nome);

    Optional<Beneficiario> findByCpf(String cpf);

    Optional<Beneficiario> findById(Long id);

}
