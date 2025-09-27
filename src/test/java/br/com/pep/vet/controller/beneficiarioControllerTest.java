package br.com.pep.vet.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import br.com.pep.vet.repository.beneficiarioRepository;

@SpringBootTest
@AutoConfigureMockMvc
class beneficiarioControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private beneficiarioRepository beneficiariorepository;

    @BeforeEach
    void setup(){
        beneficiariorepository.deleteAll();
    }
   
}
