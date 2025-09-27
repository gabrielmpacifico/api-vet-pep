package br.com.pep.vet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pep.vet.entity.Plano;
import br.com.pep.vet.service.planoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/plano")
public class planoController {
    
    @Autowired
    private planoService planoservice;

    @PostMapping
    List<Plano> adicionar(@Valid @RequestBody Plano plano){
        return planoservice.adicionar(plano);
    }

    @GetMapping("/ativos")
    List<Plano> listarPlanosAtivos(){
        return planoservice.listarPlanosAtivos();
    }

    @GetMapping
    List<Plano> listarPlanos(){
        return planoservice.listarPlanos();
    }

    @PutMapping
    List<Plano> atualizar(@Valid @RequestBody Plano plano){
        return planoservice.atualizar(plano);
    }
}
