package br.com.autorevise.mecanicagestor.api.services;

import br.com.msoficinas.api.entidades.Usuario;
import br.com.msoficinas.api.web.request.LoginRequest;
import br.com.msoficinas.api.web.response.UsuarioComVariosEstabelecimentos;
import br.com.msoficinas.api.web.response.UsuarioResponse;

public interface AutenticacaoService {

    UsuarioResponse autenticar(LoginRequest request) throws Exception;

    boolean validarToken(String token);

    void alterarSenha();

    void recuperarSenha();
}
