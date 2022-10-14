package com.example.desafio02.controller;

import com.example.desafio02.dto.ComodoDTO;
import com.example.desafio02.model.Imovel;
import com.example.desafio02.service.IImovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
    @Autowired
    private IImovel imovelService;

    @PostMapping("/salvar")
    public ResponseEntity<Imovel> salvarImovel(@RequestBody Imovel imovel){
        return new ResponseEntity<>(imovelService.salvarImovel(imovel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Imovel>> getTodos(){
        return new ResponseEntity<>(imovelService.getTodos(), HttpStatus.OK);
    }

    @GetMapping("/pelo_id/{id}")
    public ResponseEntity<Imovel> getImovelPeloId(@PathVariable int id){
        return new ResponseEntity<>(imovelService.getImovelPeloId(id), HttpStatus.OK);
    }

    @GetMapping("/area/{id}")
    public ResponseEntity<Double> getImovelArea(@PathVariable int id){
        return new ResponseEntity<>(imovelService.getImovelArea(id), HttpStatus.OK);
    }

//    @GetMapping("/area_comodos/{id}")
//    public ResponseEntity<List<ComodoDTO>> getImovelComodosArea(@PathVariable int id){
//        return new ResponseEntity<>(imovelService.getImovelComodosArea(id), HttpStatus.OK);
//    }

    @GetMapping("/valor_imovel/{id}")
    public ResponseEntity<Double> getValorImovel(@PathVariable int id){
        return new ResponseEntity<>(imovelService.getValorImovel(id), HttpStatus.OK);
    }
}
