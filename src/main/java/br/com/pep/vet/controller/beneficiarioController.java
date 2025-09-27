package br.com.pep.vet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pep.vet.entity.Beneficiario;
import br.com.pep.vet.service.beneficiarioService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/beneficiario")
public class beneficiarioController {
    
    private beneficiarioService beneficiarioservice;

    public beneficiarioController(beneficiarioService beneficiarioservice) {
        this.beneficiarioservice = beneficiarioservice;
    }

    @PostMapping
    Beneficiario adicionar(@Valid @RequestBody Beneficiario beneficiario){
        return beneficiarioservice.adicionar(beneficiario);    
    }

    @GetMapping("{cpf}")
    Optional<Beneficiario> listarByCpf(@PathVariable("cpf") String cpf){
        return beneficiarioservice.listarByCpf(cpf);
    }

    @GetMapping
    List<Beneficiario> listarByNome(@RequestParam(required=true) String nome){
        return beneficiarioservice.listarByNome(nome);
    }

    @PutMapping
    Beneficiario atualizar(@RequestBody Beneficiario beneficiario) {
        return beneficiarioservice.atualizar(beneficiario);
    }

    @DeleteMapping("{id}")
    ResponseEntity<Void> deletar(@PathVariable("id") Long id){
        beneficiarioservice.remover(id);
        return ResponseEntity.ok().build();
    }

}

