package br.com.pep.vet.controller;

import br.com.pep.vet.entity.Pet;
import br.com.pep.vet.requestDTO.petRequestDTO;
import br.com.pep.vet.responseDTO.petResponseDTO;
import br.com.pep.vet.service.petService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pet")
public class petController {

    @Autowired
    private petService petservice;

    @GetMapping("{id}")
    ResponseEntity<petResponseDTO> listarById(@PathVariable("id") Long id){
        petResponseDTO response = petservice.listarById(id);
        return ResponseEntity.ok().body(response)
;    }

    @PostMapping("/listar")
    ResponseEntity<List<petResponseDTO>> listarByTutor(@Valid @RequestBody Long idTutor){
        List<petResponseDTO> response = petservice.listarByTutor(idTutor);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    ResponseEntity<petResponseDTO> cadastrar(@Valid @RequestBody petRequestDTO dados){
        petResponseDTO response = petservice.cadastrar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping
    ResponseEntity<petResponseDTO> atualizar(@Valid @RequestBody petRequestDTO dados){
        petResponseDTO response = petservice.atualizar(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
