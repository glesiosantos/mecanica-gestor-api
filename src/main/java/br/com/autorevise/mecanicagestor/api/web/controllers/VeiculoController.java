package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.autorevise.mecanicagestor.api.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/{placa}")
    public ResponseEntity<?> buscarVeiculoPelaPlaca(@PathVariable("placa") String placa) {
        var veiculos = veiculoService.buscarVeiculoPelaPlaca(placa);
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping
    public ResponseEntity<?> carregarListaVeiculos() {
        var veiculos = veiculoService.carregarVeiculosRegistrados();
        return ResponseEntity.ok(veiculos);
    }
}
