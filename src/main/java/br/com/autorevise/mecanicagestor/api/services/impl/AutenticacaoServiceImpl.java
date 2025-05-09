package br.com.autorevise.mecanicagestor.api.services.impl;

import br.com.msoficinas.api.entidades.Usuario;
import br.com.msoficinas.api.services.AutenticacaoService;
import br.com.msoficinas.api.services.JwtService;
import br.com.msoficinas.api.services.UsuarioService;
import br.com.msoficinas.api.web.request.LoginRequest;
import br.com.msoficinas.api.web.response.EstabelecimentoResponse;
import br.com.msoficinas.api.web.response.UsuarioComVariosEstabelecimentos;
import br.com.msoficinas.api.web.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Override
    public UsuarioResponse autenticar(LoginRequest request) throws Exception {
        var authenticated = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.cpf().replace(".","").replace("-",""), request.senha()));
        Usuario usuario = usuarioService.buscarUsuarioPeloCpf(authenticated.getName());
        String token = jwtService.gerarToken((UserDetails) authenticated.getPrincipal());
        List<EstabelecimentoResponse> estabelecimentos =  usuario.getEstabelecimentos() //convertendo lista de estabelecimentos
                .stream().map(estabelecimento -> new EstabelecimentoResponse(estabelecimento.getId(), estabelecimento.getLogo(),
                        estabelecimento.getCpfOuCnpj(),
                        estabelecimento.getNomeFantasia(),
                        estabelecimento.getPlano().getDescricao())).toList();
        return new UsuarioResponse(token, usuario);
    }

    @Override
    public boolean validarToken(String token) {
        return jwtService.validarToken(token.substring(7)).isEmpty();
    }

    @Override
    public void alterarSenha() {}

    @Override
    public void recuperarSenha() {}
}
