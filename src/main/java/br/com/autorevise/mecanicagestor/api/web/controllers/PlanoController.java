package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.msoficinas.api.enuns.Plano;
import br.com.msoficinas.api.services.EstabelecimentoService;
import br.com.msoficinas.api.web.request.UpdatePlanoRequest;
import br.com.msoficinas.api.web.response.PlanoResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/v1/planos")
public class PlanoController {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @GetMapping
    public ResponseEntity<?> carregarPlanos() {
        var planos = Arrays.stream(Plano.values()).map(plano -> new PlanoResponse(plano.name(), plano.getDescricao(), plano.getTotalUsuario(), plano.getValor()));
        return ResponseEntity.ok(planos);
    }

    @PostMapping("/atualizar")
    public ResponseEntity<?> atualizarPlano(@RequestBody @Valid UpdatePlanoRequest request) throws Exception {
        System.out.println(request.toString());
        estabelecimentoService.updatePlanoEstabelecimento(request);
        return ResponseEntity.notFound().build();
    }
}
