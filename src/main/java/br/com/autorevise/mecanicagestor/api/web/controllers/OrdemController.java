package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.autorevise.mecanicagestor.api.services.OrdemService;
import br.com.autorevise.mecanicagestor.api.web.request.AtualizarOrdemStatusRequest;
import br.com.autorevise.mecanicagestor.api.web.request.AtualizarStatusOficinaRequest;
import br.com.autorevise.mecanicagestor.api.web.request.OrdemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/ordens")
public class OrdemController {

    @Autowired
    private OrdemService ordemService;

    @GetMapping("/{idEstabelecimento}")
    public ResponseEntity<?> carregarOrdensEstabelecimento(@PathVariable("idEstabelecimento") String idEstabelecimento) {
        var ordens = ordemService.carregarOrdemEstabelecimento(idEstabelecimento);
        return ResponseEntity.ok(ordens);
    }

    @GetMapping("/pedido/{idEstabelecimento}")
    public ResponseEntity<?> carregarOrdensEstabelecimentoPeloStatus(@PathVariable("idEstabelecimento") String idEstabelecimento) {
        var ordens = ordemService.carregarOrdemEstabelecimento(idEstabelecimento);
        return ResponseEntity.ok(ordens);
    }

    @PutMapping("/converter/{idOrdem}")
    public ResponseEntity<?> converterOrcamentoEmPedido(@PathVariable("idOrdem") String idOrdem) throws Exception {
        ordemService.atualizarOrdemTipoProposta(idOrdem);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idOrdem}/{idEstabelecimento}")
    public ResponseEntity<?> carregarOrdensDoEstabelecimentoPeloId(@PathVariable("idOrdem") String idOrdem, @PathVariable("idEstabelecimento") String idEstabelecimento) throws Exception {
        var ordem = ordemService.carregarOrdemDoEstabelecimentoPeloSeuIdOrdem(idOrdem, idEstabelecimento);
        return ResponseEntity.ok(ordem);
    }

    @PostMapping("/status")
    public ResponseEntity<?> mudarStatusOrdemEstabelecimento(@RequestBody AtualizarOrdemStatusRequest request) throws Exception {
        ordemService.atualizarStatusOrdemEstabelecimento(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("oficina/status")
    public ResponseEntity<?> mudarStatusOrdemNaOficinaEstabelecimento(@RequestBody AtualizarStatusOficinaRequest request) throws Exception {
        ordemService.atualizarStatusOficinaDoOrdemDoEstabelecimento(request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<?> registrarOrdem(@RequestBody OrdemRequest request) throws Exception {
        var ordem = ordemService.registrarOrdemEstabelecimento(request);
        return ResponseEntity.ok(ordem);
    }
}
