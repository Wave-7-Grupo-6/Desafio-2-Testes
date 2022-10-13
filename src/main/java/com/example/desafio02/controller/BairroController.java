package com.example.desafio02.controller;

import com.example.desafio02.dto.BairroDTO;
import com.example.desafio02.model.Bairro;
import com.example.desafio02.service.IBairro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bairros")
public class BairroController {
    @Autowired
    private IBairro bairroService;

    @PostMapping("/salvar")
    public ResponseEntity<List<BairroDTO>> salvarBairros(@RequestBody List<Bairro> bairros){
        return new ResponseEntity<>(bairroService.salvarBairro(bairros), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BairroDTO>> getTodos(){
        return new ResponseEntity<>(bairroService.getTodos(), HttpStatus.OK);
    }
}
