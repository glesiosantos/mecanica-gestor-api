package br.com.autorevise.mecanicagestor.api.web.controllers;

import br.com.autorevise.mecanicagestor.api.entities.Estabelecimento;
import br.com.autorevise.mecanicagestor.api.enuns.Plano;
import br.com.autorevise.mecanicagestor.api.services.AutenticacaoService;
import br.com.autorevise.mecanicagestor.api.web.request.LoginRequest;
import br.com.autorevise.mecanicagestor.api.web.request.RefreshRequest;
import br.com.autorevise.mecanicagestor.api.web.response.EstabelecimentoResponse;
import br.com.autorevise.mecanicagestor.api.web.response.PlanoResponse;
import br.com.autorevise.mecanicagestor.api.web.response.UsuarioComUmEstabelecimentoResponse;
import br.com.autorevise.mecanicagestor.api.web.response.UsuarioComVariosEstabelecimentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("v1/auth")
public class AutenticacaoController {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticar(@RequestBody LoginRequest request) throws Exception {
        var response = autenticacaoService.autenticar(request);

        if (response.usuario().getEstabelecimentos().size() == 1) {
            Set<Estabelecimento> estabelecimentos = response.usuario().getEstabelecimentos();
            EstabelecimentoResponse estabelecimento = estabelecimentos
                    .stream()
                    .map(e -> new EstabelecimentoResponse(e.getId(), e.getLogo(), e.getNomeFantasia(), e.getCpfOuCnpj(), e.getPlano().getDescricao())).findFirst().get();
            Plano plano = estabelecimentos.stream().findFirst().get().getPlano();
            return ResponseEntity.ok(
                    new UsuarioComUmEstabelecimentoResponse(response.token(),response.usuario().getNomeCompleto(), response.usuario().isUsuarioPrincipal(), response.usuario().getPerfil().getNome(), response.usuario().getCpf(),
                    new PlanoResponse(plano.name(), plano.getDescricao(), plano.getTotalUsuario(), plano.getValor()),estabelecimento));
        } else {
            Set<Estabelecimento> estabelecimentos = response.usuario().getEstabelecimentos();
            List<EstabelecimentoResponse> estabelecimentosResponse = estabelecimentos
                    .stream()
                    .map(estabelecimento -> new EstabelecimentoResponse(estabelecimento.getId(), estabelecimento.getLogo(), estabelecimento.getNomeFantasia(), estabelecimento.getCpfOuCnpj(), estabelecimento.getPlano().getDescricao())).toList();
            return ResponseEntity.ok(new UsuarioComVariosEstabelecimentos(response.token(), estabelecimentosResponse, response.usuario().getNomeCompleto(), response.usuario().getPerfil().getNome())) ;
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshRequest request) {
        var token = autenticacaoService.validarToken(request.token());
        System.out.println("**** **** **** "+token);
        return ResponseEntity.ok(token);
    }
}
