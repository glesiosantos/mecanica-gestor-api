package br.com.autorevise.mecanicagestor.api.security;

import br.com.autorevise.mecanicagestor.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByCpfComEstabelecimentos(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Nenhum usuário encontrado com este CPF %s", username)));
    }
}
