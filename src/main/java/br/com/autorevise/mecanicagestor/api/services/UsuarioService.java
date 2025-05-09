package br.com.autorevise.mecanicagestor.api.services;

import br.com.msoficinas.api.entidades.Usuario;
import br.com.msoficinas.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.msoficinas.api.web.request.UsuarioRequest;
import br.com.msoficinas.api.web.request.UsuarioUpdateSenhaRequest;
import br.com.msoficinas.api.web.response.ColaboradorEstabelecimentoResponse;

import java.util.List;

public interface UsuarioService {

    Usuario addUsuarioDoEstabelecimento(UsuarioRequest request) throws Exception;

    void atualizarUsuario(UsuarioRequest request) throws Exception;

    Usuario buscarUsuarioPeloCpf(String cpf) throws Exception;

    List<ColaboradorEstabelecimentoResponse> carregarUsuarioPeloEstabelecimento(String idEstabelecimento);

    void removerUsuario(String idUsuario, String idEstabelecimento) throws Exception;

    void atualizarSenhaUsuario(UsuarioUpdateSenhaRequest request) throws Exception;
}
