package br.com.pep.vet.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import br.com.pep.vet.responseDTO.beneficiarioResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.pep.vet.entity.Beneficiario;
import br.com.pep.vet.exception.CpfException;
import br.com.pep.vet.repository.beneficiarioRepository;

@ExtendWith(MockitoExtension.class)
public class beneficiarioServiceTest {
    
    @Mock
    private beneficiarioRepository beneficiariorepository;

    @InjectMocks
    private beneficiarioService beneficiarioservice;

    @Test
    @DisplayName("Deve retornar sucesso com o cpf válido")
    void listarByCpfCaso1(){
        String cpf = "12345678900";
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setCpf(cpf);

        when(beneficiariorepository.findByCpf(cpf)).thenReturn(Optional.of(beneficiario));

        beneficiarioResponseDTO resultado = beneficiarioservice.listarByCpf(cpf);

        assertThat(resultado).isNotNull();
        assertThat(resultado.getCpf()).isEqualTo(cpf);

        verify(beneficiariorepository, times(1)).findByCpf(cpf);
    }

    @Test
    @DisplayName("Sucesso quando o CPF enviado não contém 11 dígitos")
    void listarByCpfCaso2(){
        String cpf = "123456";
        
        assertThatThrownBy(() -> beneficiarioservice.listarByCpf(cpf))
        .isInstanceOf(CpfException.class)
        .hasMessageContaining("Cpf fora do padrão");
    }

}
