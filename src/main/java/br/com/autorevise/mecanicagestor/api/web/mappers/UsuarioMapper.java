package br.com.autorevise.mecanicagestor.api.web.mappers;

import br.com.msoficinas.api.entidades.Usuario;
import br.com.msoficinas.api.enuns.Perfil;
import br.com.msoficinas.api.web.request.UsuarioRequest;
import br.com.msoficinas.api.web.response.ColaboradorEstabelecimentoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    @Mapping(target = "idColaborador", source = "id")
    @Mapping(target = "idEstabelecimento", expression = "java(obterIdEstabelecimento(usuario))")
    @Mapping(target = "cpf", source = "cpf")
    @Mapping(target = "nomeCompleto", source = "nomeCompleto")
    @Mapping(target = "usuarioPrincipal", source = "usuarioPrincipal")
    @Mapping(target = "perfil", expression = "java(obterPerfil(usuario))")
    @Mapping(target = "criadoEm", expression = "java(obterDataDeCadastro(usuario))")
    ColaboradorEstabelecimentoResponse converterUsuarioEmColaborador(Usuario usuario);

    @Mapping(target = "id", source = "idUsuario")
    @Mapping(target = "cpf", source = "cpf")
    @Mapping(target = "nomeCompleto", source = "nomeCompleto")
    @Mapping(target = "perfil", expression = "java(obterPerfilSelecionado(request))")
    Usuario converterRequestParaModelo(UsuarioRequest request);

    default String obterIdEstabelecimento(Usuario usuario) {
        return usuario.getEstabelecimentos().size() == 1 ? usuario.getEstabelecimentos().stream().findFirst().get().getId() : null;
    }

    default String obterPerfil(Usuario usuario) {
        return usuario.getPerfil().getNome();
    }

    default String obterDataDeCadastro(Usuario usuario) {
        return usuario.getDataCriacao().toString();
    }

    default Perfil obterPerfilSelecionado(UsuarioRequest request) {
        return Perfil.valueOf(request.perfil());
    }
}
