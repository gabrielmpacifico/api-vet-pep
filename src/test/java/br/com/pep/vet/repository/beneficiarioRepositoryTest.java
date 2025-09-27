package br.com.pep.vet.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.com.pep.vet.responseDTO.beneficiarioResponseDTO;
import br.com.pep.vet.responseDTO.beneficiarioResumoDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.pep.vet.entity.Beneficiario;
import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
public class beneficiarioRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    beneficiarioRepository beneficiariorepository;

    @Test
    @DisplayName("Deve retornar o usuário pelo nome com sucesso")
    void testFindByNomeIgnoreCaseContainingOrderByNomeAscCaso1() {
        Beneficiario data = new Beneficiario();
        data.setNome("Gabriel");
        data.setEmail("123@123.123");
        data.setCpf("99999999999");
        data.setNacionalidade("Brasileiro");
        data.setTipoPessoa("F");
        data.setCelular("81912345678");
        data.setNascimento(LocalDateTime.now());
        this.criarBeneficiario(data);

        List<beneficiarioResumoDTO> retorno = this.beneficiariorepository.buscarListaDeNomes("GAB");
        assertThat(retorno).isNotEmpty();
    }

    @Test
    @DisplayName("Não deve retornar o usuário que não existe")
    void testFindByNomeIgnoreCaseContainingOrderByNomeAscCaso2() {
        Beneficiario data = new Beneficiario();
        data.setNome("Gabriel");
        data.setEmail("123@123.123");
        data.setCpf("99999999999");
        data.setNacionalidade("Brasileiro");
        data.setTipoPessoa("F");
        data.setCelular("81912345678");
        data.setNascimento(LocalDateTime.now());
        this.criarBeneficiario(data);
 
        List<beneficiarioResumoDTO> retorno = this.beneficiariorepository.buscarListaDeNomes("Gob");
        assertThat(retorno).isEmpty();
    }

    private Beneficiario criarBeneficiario(Beneficiario data){
        Beneficiario b = new Beneficiario(data);
        this.entityManager.persist(b);
        return b;
    }

}
