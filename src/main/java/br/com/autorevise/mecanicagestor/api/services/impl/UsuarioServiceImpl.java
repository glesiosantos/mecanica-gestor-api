package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.msoficinas.api.entidades.Usuario;
import br.com.msoficinas.api.enuns.Perfil;
import br.com.msoficinas.api.repositories.UsuarioRepository;
import br.com.msoficinas.api.services.EstabelecimentoService;
import br.com.msoficinas.api.services.UsuarioService;
import br.com.msoficinas.api.services.exceptions.ObjetoNaoEncontradoException;
import br.com.msoficinas.api.web.mappers.UsuarioMapper;
import br.com.msoficinas.api.web.request.UsuarioRequest;
import br.com.msoficinas.api.web.request.UsuarioUpdateSenhaRequest;
import br.com.msoficinas.api.web.response.ColaboradorEstabelecimentoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario addUsuarioDoEstabelecimento(UsuarioRequest request) throws Exception {
        Usuario usuario = null;
        Optional<Usuario> optional = usuarioRepository.findUsuarioByCpf(request.cpf());

        var senhaUsuario = request.cpf().replace(".","")
                .replace("-","").substring(0,6);

        if (optional.isEmpty()) {
            var estabelecimento = estabelecimentoService.buscarEstabelecimentoPeloId(request.idEstabelecimento());
            usuario = usuarioMapper.converterRequestParaModelo(request);
            usuario.setEstabelecimentos(Set.of(estabelecimento));
            usuario.setAtivo(true);
            usuario.setAvatar("default.png");
            usuario.setSenha(passwordEncoder.encode(senhaUsuario));
            estabelecimento.setUsuarios(Set.of(usuario));
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public void atualizarUsuario(UsuarioRequest request) throws Exception {
        var usuario = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Nenhum usuário encontrado com este ID", request.idUsuario())));

        if(request.cpf() != null) {
            usuario.setCpf(request.cpf());
        }

        if(request.nomeCompleto() != null) {
            usuario.setNomeCompleto(request.nomeCompleto());
        }

        if(request.perfil() != null) {
            usuario.setPerfil(Perfil.valueOf(request.perfil()));
        }

        usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    @Override
    public Usuario buscarUsuarioPeloCpf(String cpf) throws Exception {
        return usuarioRepository.findByCpfComEstabelecimentos(cpf)
                .orElseThrow(() -> new ObjetoNaoEncontradoException(String.format("Nenhum usuário encontrado com este CPF %s", cpf)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<ColaboradorEstabelecimentoResponse> carregarUsuarioPeloEstabelecimento(String idEstabelecimento) {
        var colaboradores = usuarioRepository.findUsuariosByEstabelecimentoId(idEstabelecimento);
        return colaboradores.stream().map(usuario -> usuarioMapper.converterUsuarioEmColaborador(usuario)).toList();
    }

    @Override
    public void removerUsuario(String idUsuario, String idEstabelecimento) throws Exception {
        var usuario = usuarioRepository
                .findByIdAndEstabelecimentosId(idUsuario,idEstabelecimento)
                .orElseThrow(() -> new ObjetoNaoEncontradoException("Nenhum usuário encontrado com este ID"));
        usuarioRepository.delete(usuario);
    }

    @Override
    public void atualizarSenhaUsuario(UsuarioUpdateSenhaRequest request) throws Exception {
        var usuario = this.buscarUsuarioPeloCpf(request.cpf());
        usuario.setSenha(passwordEncoder.encode(request.nova()));
        usuarioRepository.save(usuario);
    }
}
