package br.com.autorevise.mecanicagestor.api.services;

import br.com.autorevise.mecanicagestor.api.entities.Usuario;
import br.com.autorevise.mecanicagestor.api.web.request.UsuarioRequest;
import br.com.autorevise.mecanicagestor.api.web.request.UsuarioUpdateSenhaRequest;
import br.com.autorevise.mecanicagestor.api.web.response.ColaboradorEstabelecimentoResponse;

import java.util.List;

public interface UsuarioService {

    Usuario addUsuarioDoEstabelecimento(UsuarioRequest request) throws Exception;

    void atualizarUsuario(UsuarioRequest request) throws Exception;

    Usuario buscarUsuarioPeloCpf(String cpf) throws Exception;

    List<ColaboradorEstabelecimentoResponse> carregarUsuarioPeloEstabelecimento(String idEstabelecimento);

    void removerUsuario(String idUsuario, String idEstabelecimento) throws Exception;

    void atualizarSenhaUsuario(UsuarioUpdateSenhaRequest request) throws Exception;
}
