package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.msoficinas.api.entidades.Usuario;
import br.com.msoficinas.api.services.UsuarioService;
import br.com.msoficinas.api.web.request.UsuarioRequest;
import br.com.msoficinas.api.web.request.UsuarioUpdateSenhaRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> addUsuarioEstabelecimento (@RequestBody @Valid UsuarioRequest request) throws Exception {
        var usuario = usuarioService.addUsuarioDoEstabelecimento(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{idEstabelecimento}")
    public ResponseEntity<?> addUsuarioEstabelecimento (@PathVariable("idEstabelecimento") String idEstabelecimento) {
        return ResponseEntity.ok(usuarioService.carregarUsuarioPeloEstabelecimento(idEstabelecimento));
    }

    @PutMapping("/editar")
    public ResponseEntity<?> editarUsuario(@RequestBody @Valid UsuarioRequest request) throws Exception {
        usuarioService.atualizarUsuario(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/remover/{idUsuario}/estabelecimento/{idEstabelecimento}")
    public ResponseEntity<?> deletarUsuario(
            @PathVariable("idUsuario") String idUsuario,
            @PathVariable("idEstabelecimento") String idEstabelecimento) throws Exception {
        usuarioService.removerUsuario(idUsuario, idEstabelecimento);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/senha/atualizar")
    public ResponseEntity<?> alterarSenha(@RequestBody @Valid UsuarioUpdateSenhaRequest request) throws Exception {
        usuarioService.atualizarSenhaUsuario(request);
        return ResponseEntity.noContent().build();
    }
}
