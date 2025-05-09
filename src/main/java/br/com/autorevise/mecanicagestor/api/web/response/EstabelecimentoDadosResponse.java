package br.com.autorevise.mecanicagestor.api.web.response;

import java.time.LocalDate;
import java.util.List;

public record EstabelecimentoDadosResponse(
        String idEstabelecimento,
        String logo,
        String cpfCnpj,
        String razao,
        String nomeFantasia,
        String plano,
        boolean periodoTeste,
        LocalDate dataFinalTeste,
        List<String> contatos,
        EnderecoResponse endereco
){}
