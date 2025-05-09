package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.autorevise.mecanicagestor.api.enuns.CategoriaEspecialidade;
import br.com.autorevise.mecanicagestor.api.services.EspecialidadeService;
import br.com.autorevise.mecanicagestor.api.web.response.EspecialidadeCategoriaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("v1/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping
    public ResponseEntity<?> carregarEspecialidades() {
        return ResponseEntity.ok(especialidadeService.carregarEspecialidades());
    }

    @GetMapping("/{idEspecialidade}")
    public ResponseEntity<?> carregarEspecialidadePorId(@PathVariable() String idEspecialidade) throws Exception {
        var especialidade = especialidadeService.carregarEspecialidadePorId(idEspecialidade);
        return ResponseEntity.ok(especialidade);
    }

    @GetMapping("/categorias")
    public ResponseEntity<?> carregarCategoriaDasEspecialidades() {
        var categorias = Arrays.stream(CategoriaEspecialidade.values())
                .map(c -> new EspecialidadeCategoriaResponse(c.getTitulo())).toList();
        return ResponseEntity.ok(categorias);
    }
}
