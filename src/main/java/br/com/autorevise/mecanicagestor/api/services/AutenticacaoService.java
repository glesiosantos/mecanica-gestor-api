package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.web.request.LoginRequest;
import br.com.autorevise.mecanicagestor.api.web.response.UsuarioResponse;

public interface AutenticacaoService {

    UsuarioResponse autenticar(LoginRequest request) throws Exception;

    boolean validarToken(String token);

    void alterarSenha();

    void recuperarSenha();
}
